package com.company.service.impl;

import com.company.dao.BookDao;
import com.company.dao.impl.BookDaoImpl;
import com.company.data.Book;
import com.company.service.BookService;
import org.apache.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final static BookDao BOOK_DAO = new BookDaoImpl();
    private final Logger log = Logger.getLogger(AuthorServiceImpl.class);

    @Override
    public void createBook(Book book) {
        log.info("Start creating book");
        BOOK_DAO.create(book);
        log.info("End creating book");
    }

    @Override
    public List<Book> readAll() {
        log.info("Start reading all books");
        log.info("End reading all books");
        return BOOK_DAO.readAll();
    }

    @Override
    public Book read(int id) {
        log.info("Start reading by ID");
        log.info("End reading by ID");
        return BOOK_DAO.read(id);
    }

    @Override
    public void update(Book book) {
        log.info("Start updating the book");
        BOOK_DAO.update(book);
        log.info("End updating the book");
    }

    @Override
    public void delete(int id) {
        log.info("Start deleting the book");
        BOOK_DAO.delete(id);
        log.info("End deleting the book");
    }

    @Override
    public void getAuthorsFromBook(int id) {
        log.info("Start getting all authors by the book");
        BOOK_DAO.getAllAuthorsOfTheBookById(id);
        log.info("End getting all authors by the book");
    }
}
