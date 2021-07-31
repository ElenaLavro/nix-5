package com.company.model.dto;

import java.time.Instant;

public class ExportDTO {
    private Long accountID;
    private String typeOfAccount;
    private Integer balance;
    private Integer sum;
    private Instant date;
    private String userName;

    public ExportDTO(Long accountID, String typeOfAccount, Integer balance, Integer sum, Instant date, String userName) {
        this.accountID = accountID;
        this.typeOfAccount = typeOfAccount;
        this.balance = balance;
        this.sum = sum;
        this.date = date;
        this.userName = userName;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
