package com.company.controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BookStoreController bookController = new BookStoreController();
    AuthorStoreController authorController = new AuthorStoreController();

    public void run() {
        boolean state = true;

        try {
            while (true) {
                menu();
                String choice = reader.readLine();
                switch (choice) {
                    case "1": {
                        bookController.createBook(reader);
                        break;
                    }

                    case "2": {
                        authorController.createAuthor(reader);
                        break;
                    }

                    case "3" : {
                        bookController.updateBook(reader);
                        break;
                    }

                    case "4" : {
                        authorController.updateAuthor(reader);
                        break;
                    }
                    case "5": {
                        bookController.readById(reader);
                        break;
                    }
                    case "6": {
                        authorController.readAuthorById(reader);
                        break;
                    }
                    case "7" : {
                        bookController.deleteBook(reader);
                        break;
                    }
                    case "8" : {
                        authorController.deleteAuthor(reader);
                        break;
                    }
                    case "9" : {
                        authorController.getBooksByAuthor(reader);
                        break;
                    }
                    case "10": {
                        bookController.readAllAuthorsOfTheBook(reader);
                        break;
                    }
                    case "11": {
                        bookController.readAllBooks();
                        break;
                    }
                    case "12": {
                        authorController.readAllAuthors();
                        break;
                    }
                    case "0": {
                        System.out.println("~ Goodbye ~");
                        System.exit(0);
                    }
                    default:
                        System.out.println("Wrong number, please repeat the choice\n");

                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void menu() {
        System.out.println("Select the task you want to complete: ");
        System.out.println("1 - Create the book");
        System.out.println("2 - Create the author");
        System.out.println("3 - Update the information about the book");
        System.out.println("4 - Update the information about the author");
        System.out.println("5 - To read the information about the book");
        System.out.println("6 - To read the information about the author");
        System.out.println("7 - Delete the book");
        System.out.println("8 - Delete the author");
        System.out.println("9 - To see all books of the author");
        System.out.println("10 - To see all authors of the book");
        System.out.println("11 - To read all books");
        System.out.println("12 - To read all authors");
        System.out.println("If you want to exit - type 0");
    }
}
