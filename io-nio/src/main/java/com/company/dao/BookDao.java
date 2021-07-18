package com.company.dao;

import com.company.data.Author;
import com.company.data.Book;

import java.util.List;

public interface BookDao extends BaseDao<Book> {
    List<Author> getAllAuthorsOfTheBookById(int id);
}
