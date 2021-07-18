package com.company.dao.impl;

import com.company.dao.AuthorDao;
import com.company.data.Author;
import com.company.data.Book;
import com.company.db.BookStoreDB;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private static final BookStoreDB bookstore = BookStoreDB.getInstance();

    @Override
    public void create(Author author) {
        bookstore.createAuthor(author);
    }

    @Override
    public void update(Author author) {
        bookstore.updateAuthor(author);
    }

    @Override
    public Author read(int id) {
        return bookstore.getAuthorById(id);
    }

    @Override
    public List<Author> readAll() {
        return bookstore.getAllAuthors();
    }

    @Override
    public void delete(int id) {
        bookstore.deleteAuthorById(id);
    }

    @Override
    public List<Book> getAllBooksOfTheAuthorById(int id) {
        return bookstore.getBooksByAuthor(id);
    }
}
