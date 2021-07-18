package com.company.dao.impl;

import com.company.dao.BookDao;
import com.company.data.Author;
import com.company.data.Book;
import com.company.db.BookStoreDB;

import java.util.List;

public class BookDaoImpl implements BookDao {
    final static BookStoreDB bookStore = BookStoreDB.getInstance();

    @Override
    public void create(Book book) {
        bookStore.createBook(book);
    }

    @Override
    public void update(Book book) {
        bookStore.updateBook(book);
    }

    @Override
    public Book read(int id) {
        return bookStore.getBookById(id);
    }

    @Override
    public List<Book> readAll() {
        return bookStore.getAllBooks();
    }

    @Override
    public void delete(int id) {
        bookStore.deleteBookById(id);
    }

    @Override
    public List<Author> getAllAuthorsOfTheBookById(int id) {
        return bookStore.getAuthorsByBook(id);
    }


}
