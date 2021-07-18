package com.company.db;

import com.company.data.Author;
import com.company.data.Book;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookStoreDB {
    private static BookStoreDB db;
    private final String fileWithAuthors = "io-nio/authors.csv";
    private final String fileWithBooks = "io=nio/books.csv";
    private int sizeAuthors = 1;
    private int sizeBooks = 1;

    private BookStoreDB() {
        setHeaderAuthors();
        setHeaderBooks();

    }

    public static BookStoreDB getInstance() {
        if (db == null) {
            db = new BookStoreDB();
        }
        return db;
    }

    public void createAuthor(Author author) {
        String[] newAuthor = new String[5];
        author.setId(this.sizeAuthors++);
        newAuthor[0] = author.getId().toString();
        newAuthor[1] = author.getFirstName();
        newAuthor[2] = author.getLastName();
        String[] allBooks = author.getBooks().toArray(new String[0]);
        for (String books : allBooks) {
            if (newAuthor[3] == null)
                newAuthor[3] = books;
            else
                newAuthor[3] += ", " + books;
        }
        author.setVisible(true);
        newAuthor[4] = author.isVisible().toString();
        try (
                CSVWriter csvWriter = new CSVWriter(new FileWriter(fileWithAuthors, true))
        ) {
            csvWriter.writeNext(newAuthor);
        } catch (IOException ioException) {
            System.out.println("Invalid data. Please check and repeat again");
        }
    }

    public void createBook(Book book) {
        String[] newBook = new String[5];
        book.setId(this.sizeBooks++);
        newBook[0] = book.getId().toString();
        newBook[1] = book.getTitleOfBook();
        newBook[2] = book.getYearOfIssue().toString();
        String[] allAuthors = book.getAuthors().toArray(new String[0]);
        for (String authors : allAuthors) {
            if (newBook[3] == null)
                newBook[3] = authors;
            else
                newBook[3] += ", " + authors;
        }
        book.setVisible(true);
        newBook[4] = book.isVisible().toString();
        try (
                CSVWriter csvWriter = new CSVWriter(new FileWriter(fileWithBooks, true))
        ) {
            csvWriter.writeNext(newBook);
        } catch (IOException ioException) {
            System.out.println("Invalid data. Please check and repeat again");
        }
    }

    public void updateAuthor(Author author) {
        List<Author> authorList = getAllAuthors();
        for (Author value : authorList) {
            if (value.isVisible()) {
                if (value.getId().equals(author.getId())) {
                    value.setFirstName(author.getFirstName());
                    value.setLastName(author.getLastName());
                    value.setBooks(author.getBooks());
                    value.setVisible(true);
                }
            }
        }
        rewriteFileAuthor(authorList);
    }

    private void rewriteFileAuthor(List<Author> authorList) {
        setHeaderAuthors();
        for(Author author: authorList) {
            String[] s = new String[5];
            s[0] = String.valueOf(author.getId());
            s[1] = author.getFirstName();
            s[2] = author.getLastName();
            s[4] = author.isVisible().toString();
            for(String books : author.getBooks()) {
                if(s[3] == null)
                    s[3] = books + " ";
                else
                    s[3] += books + " ";
            }
            try(CSVWriter csvWriter = new CSVWriter(new FileWriter(fileWithAuthors,true))) {
                csvWriter.writeNext(s);
            } catch (IOException ioException) {
                System.out.println("Invalid data. Please check and repeat again");
            }
        }

    }

    public void updateBook(Book book) {
        int id = book.getId();
        try (
                CSVReader csvReader = new CSVReader(new FileReader(fileWithBooks));
                CSVWriter csvWriter = new CSVWriter(new FileWriter(fileWithBooks, true))
        ) {
            List<String[]> allBooks = csvReader.readAll();
            if (isFieldExist(allBooks, id)) {
                System.out.println("This book don`t exist");
            } else {
                allBooks.get(id)[1] = book.getTitleOfBook();
                allBooks.get(id)[2] = book.getYearOfIssue().toString();

                for (String authors : book.getAuthors()) {
                    if (allBooks.get(id)[3].isEmpty())
                        allBooks.get(id)[3] = authors;
                    else
                        allBooks.get(id)[3] += ", " + authors;
                }
            }
            csvWriter.writeAll(allBooks);
        } catch (IOException | CsvException ioException) {
            System.out.println("Invalid data. Please check and repeat again");
        }
    }

    private boolean isFieldExist(List<String[]> field, int id) {
        return field.size() < id - 1 || field.isEmpty() || field.get(id)[4].equals("false");
    }

    public void deleteAuthorById(int id) {
//        List<Author> authors = getAllAuthors();
//        Author authorForRemoving = authors.stream().filter(author -> author.getId().equals(id)).findFirst().get();
//        authorForRemoving.setVisible(false);
//        rewriteFileAuthor(authors);
//        List<Book> allBooks = getAllBooks();
//        Book bookForRemoving;
//        if(!authorForRemoving.getBooks().isEmpty()) {
//            for(String current : authorForRemoving.getBooks()) {
//                bookForRemoving = allBooks.stream().filter(book -> book.getId().equals(current)).findFirst().get();
//            }
//        }
        delete(id, fileWithAuthors);
    }

    public void deleteBookById(int id) {
        delete(id, fileWithBooks);
    }

    private void delete(int id, String file) {
        try (
                CSVReader csvReader = new CSVReader(new FileReader(file));
                CSVWriter csvWriter = new CSVWriter(new FileWriter(file))
        ) {
            List<String[]> allData = csvReader.readAll();
            for (String[] s : allData) {

                if(Integer.parseInt(s[0]) == id) {
                    s[4] = "false";
                }
                csvWriter.writeNext(s);
            }
        }
     catch (IOException | CsvException ioException) {
            System.out.println("Invalid data. Please check and repeat again");
        }
    }


    public Author getAuthorById(int id) {
        return getAllAuthors().stream().filter(author -> author.getId().equals(id) && author.isVisible()).findFirst().orElse(null);
    }

    public Book getBookById(int id) {
        return getAllBooks().stream().filter(book -> book.getId().equals(id) && book.isVisible()).findFirst().orElse(null);
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (
                CSVReader csvReader = new CSVReader(new FileReader(fileWithAuthors))
        ) {
            List<String[]> csvData = csvReader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if (isFieldExist(csvData, i)) {
                    Author author = new Author();
                    String[] currentAuthor = csvData.get(i);
                    author.setId(Integer.parseInt(currentAuthor[0]));
                    author.setFirstName(currentAuthor[1]);
                    author.setLastName(currentAuthor[2]);
                    List<String> books = new ArrayList<>(Arrays.asList(currentAuthor[3].split(" ")));
                    author.setBooks(books);
                    author.setVisible(Boolean.parseBoolean(currentAuthor[4]));
                    authors.add(author);
                }
            }
        } catch (IOException | CsvException exception) {
            System.out.println("Invalid data. Please check and repeat again");
        }
        authors = authors.stream().filter(author -> author.isVisible().equals(true)).collect(Collectors.toList());
        return authors;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (
                CSVReader csvReader = new CSVReader(new FileReader(fileWithBooks))
        ) {
            List<String[]> csvData = csvReader.readAll();

            for (int i = 1; i < csvData.size(); i++) {
                String[] currentBook = csvData.get(i);
                if (isFieldExist(csvData, i)) {
                    Book book = new Book();
                    book.setId(Integer.parseInt(currentBook[0]));
                    book.setTitleOfBook(currentBook[1]);
                    book.setYearOfIssue(Integer.parseInt(currentBook[2]));
                    List<String> authors = new ArrayList<>(Arrays.asList(currentBook[3].split(" ")));
                    book.setAuthors(authors);
                    book.setVisible(Boolean.parseBoolean(currentBook[4]));
                    books.add(book);
                }
            }
        } catch (IOException | CsvException ioException) {
            System.out.println("Invalid data. Please check and repeat again");
        }
        books = books.stream().filter(book -> book.isVisible().equals(true)).collect(Collectors.toList());
        return books;
    }

    public List<Author> getAuthorsByBook(int id) {
        return getAllAuthors().stream().filter(data -> data.getId().equals(id)).collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(int id) {
        return getAllBooks().stream().filter(data -> data.getId().equals(id)).collect(Collectors.toList());
    }

    private void setHeaderAuthors() {
        List<String[]> csvData = new ArrayList<>();
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(fileWithAuthors))
        ) {
            String[] header = new String[]{"ID", "First name", "Last name", "Books", "Is available"};
            csvData.add(header);
            writer.writeAll(csvData);
        } catch (IOException ioException) {
            System.out.println("Invalid file");
        }
    }

    private void setHeaderBooks() {
        List<String[]> csvData = new ArrayList<>();
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(fileWithBooks))
        ) {
            String[] header = new String[]{"ID", "Title", "Year of issue", "Authors", "Is available"};
            csvData.add(header);
            writer.writeAll(csvData);
        } catch (IOException ioException) {
            System.out.println("Invalid file");
        }
    }

}
