package com.company.entity;

import com.company.annotation.Determinant;

public class Group {
    @Determinant("Title")
    private String title;

    @Determinant("Amount")
    private int amount;

    @Determinant("Debut")
    private int yearOfDebut;

    @Determinant("Fandom")
    private String fandom;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getYearOfDebut() {
        return yearOfDebut;
    }

    public void setYearOfDebut(int yearOfDebut) {
        this.yearOfDebut = yearOfDebut;
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }
}
