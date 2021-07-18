package com.company.service;

import com.company.data.Book;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    List<Book> readAll();

    Book read(int id);

    void update(Book book);

    void delete(int id);

    void getAuthorsFromBook(int id);
}
