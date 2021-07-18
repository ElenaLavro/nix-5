package com.company.controler;

import com.company.data.Book;
import com.company.service.BookService;
import com.company.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class BookStoreController {
    final static BookService bookService = new BookServiceImpl();

    public void createBook(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter book title");
            String title = bufferedReader.readLine();
            System.out.println("Please, enter the year of issue");
            Integer yearOfIssue = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please, enter authors, separated by semicolon.\nExample: Jane Austen; Meg Jay ");
            String[] authors = bufferedReader.readLine().split(";");
            Book book = new Book();
            book.setTitleOfBook(title);
            book.setYearOfIssue(yearOfIssue);
            book.setAuthors(Arrays.asList(authors));
            bookService.createBook(book);
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void updateBook(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id of book which you want to change");
            int id = Integer.parseInt(bufferedReader.readLine());
            Book book = new Book();
            System.out.println("Please, enter book title");
            String title = bufferedReader.readLine();
            System.out.println("Please, enter the year of issue");
            Integer yearOfIssue = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please, enter authors, separated by semicolon.\nExample: Jane Austen; Meg Jay ");
            String[] authors = bufferedReader.readLine().split(";");
            book.setId(id);
            book.setTitleOfBook(title);
            book.setYearOfIssue(yearOfIssue);
            book.setAuthors(Arrays.asList(authors));
            book.setVisible(true);
            bookService.update(book);
            System.out.println("Book is successfully updated");
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void readAllBooks() {
        System.out.println("List of all books: ");
        System.out.println("===================\n");
        bookService.readAll();
    }

    public void readById(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id number of book you want to read: ");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Book with ID '" + id + "'");
            bookService.read(id);
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void deleteBook(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id number of book you want to delete: ");
            int id = Integer.parseInt(bufferedReader.readLine());
            bookService.delete(id);
            System.out.println("Book is successfully deleted \n");
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void readAllAuthorsOfTheBook(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id number of book you want to see authors");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Authors: ");
            bookService.getAuthorsFromBook(id);
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }

    }

}
