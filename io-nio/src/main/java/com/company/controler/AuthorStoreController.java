package com.company.controler;

import com.company.data.Author;
import com.company.service.AuthorService;
import com.company.service.impl.AuthorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class AuthorStoreController {
    final static AuthorService authorService = new AuthorServiceImpl();

    public void createAuthor(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter first name:");
            String firstName = bufferedReader.readLine();
            System.out.println("Please, enter last name");
            String lastName = bufferedReader.readLine();
            System.out.println("Please, enter book titles, separated by semicolon. Example: The Great Gatsby; To Kill a Mockingbird");
            String[] books = bufferedReader.readLine().split(";");
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setBooks(Arrays.asList(books));
            author.setVisible(true);
            authorService.createAuthor(author);
            System.out.println("Author successfully created");
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void updateAuthor(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id of author you want you want to change");
            int id = Integer.parseInt(bufferedReader.readLine());
            Author author = new Author();
            author.setId(id);
            System.out.println("Please, enter first name:");
            String firstName = bufferedReader.readLine();
            System.out.println("Please, enter last name");
            String lastName = bufferedReader.readLine();
            System.out.println("Please, enter book titles, separated by semicolon. Example: The Great Gatsby; To Kill a Mockingbird");
            String[] books = bufferedReader.readLine().split(";");
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setBooks(Arrays.asList(books));
            author.setVisible(true);
            authorService.update(author);
            System.out.println("Author successfully updated");
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void readAllAuthors() {
        System.out.println("List of all authors: ");
        System.out.println("=====================");
        authorService.readAll();
    }

    public void readAuthorById(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id of author you want you want to read");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Author with ID '" + id + "'");
            authorService.read(id);
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void deleteAuthor(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id of author you want you want to delete");
            int id = Integer.parseInt(bufferedReader.readLine());
            authorService.delete(id);
            System.out.println("Author successfully deleted");
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }

    public void getBooksByAuthor(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the id of author you want you want to read all books");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Books: ");
            authorService.getBookFromAuthor(id);
        } catch (IOException ioException) {
            System.out.println("Something goes wrong. Please, check your input data and repeat operation");
        }
    }
}
