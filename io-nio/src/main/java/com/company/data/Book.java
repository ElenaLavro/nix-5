package com.company.data;

import java.util.ArrayList;
import java.util.List;

public class Book extends BaseData {
    private String titleOfBook;
    private Integer yearOfIssue;
    private List<String> authors = new ArrayList<>();

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", visible=" + visible +
                ", titleOfBook='" + titleOfBook + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                '}';
    }
}
