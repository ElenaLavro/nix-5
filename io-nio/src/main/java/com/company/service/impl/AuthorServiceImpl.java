package com.company.service.impl;

import com.company.dao.AuthorDao;
import com.company.dao.impl.AuthorDaoImpl;
import com.company.data.Author;
import com.company.service.AuthorService;
import org.apache.log4j.Logger;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final static AuthorDao AUTHOR_DAO = new AuthorDaoImpl();
    private final Logger log =  Logger.getLogger(AuthorServiceImpl.class);

    @Override
    public void createAuthor(Author author) {
        log.info("Start creating author");
        AUTHOR_DAO.create(author);
        log.info("Successful creating author");
    }

    @Override
    public List<Author> readAll() {
        log.info("Start reading all authors");
        log.info("End reading all authors");
        return AUTHOR_DAO.readAll();
    }

    @Override
    public Author read(int id) {
        log.info("Start reading author by ID");
        log.info("End reading author by ID");
        return AUTHOR_DAO.read(id);
    }

    @Override
    public void update(Author author) {
        log.info("Start updating author");
        AUTHOR_DAO.update(author);
        log.info("End updating author");
    }

    @Override
    public void delete(int id) {
        log.info("Start deleting author by ID");
        AUTHOR_DAO.delete(id);
        log.info("End deleting author by ID");
    }

    @Override
    public void getBookFromAuthor(int id) {
        log.info("Start getting all books by author");
        AUTHOR_DAO.getAllBooksOfTheAuthorById(id);
        log.info("End getting all books by author");
    }
}
