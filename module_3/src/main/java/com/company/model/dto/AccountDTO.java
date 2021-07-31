package com.company.model.dto;

public class AccountDTO {
    private Long id;
    private String type;
    private Integer balance;
    private Long user_id;

    public AccountDTO(Long id, String type, Integer balance, Long user_id) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
