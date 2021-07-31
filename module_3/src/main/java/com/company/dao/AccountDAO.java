package com.company.dao;

public interface AccountDAO<Entity> {
    Entity findAccountByID(Long id);
//    Integer findBalance(Long id);
}
