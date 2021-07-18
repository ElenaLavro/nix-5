package com.company.dao;

import com.company.data.Author;
import com.company.data.Book;

import java.util.List;

public interface AuthorDao extends BaseDao<Author> {
    List<Book> getAllBooksOfTheAuthorById(int id);
}
