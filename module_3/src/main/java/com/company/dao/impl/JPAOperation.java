package com.company.dao.impl;

import com.company.dao.AddingOperationDAO;
import com.company.model.dto.OperationDTO;
import com.company.model.entity.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class JPAOperation implements AddingOperationDAO {
    private static final Logger logger = LoggerFactory.getLogger(JPAOperation.class);
    private final Session session;

    public JPAOperation(Session session) {
        this.session = session;
    }

    @Override
    public Account findAccountByID(Long id) {
        try {
            logger.debug("Start searching account by id");
            session.beginTransaction();
            Boolean checkExistence = session.createQuery("select (count (Account) > 0) as exist from " +
                    "Account where Account.id=:id", Boolean.class)
                    .setParameter("id", id).getSingleResult();
            if (!checkExistence) {
                logger.error("Account not found");
                session.getTransaction().rollback();
                throw new SQLException("Account with id" + id + "wasn't found");
            }
            Account account = session.createQuery(" from Account a where a.id=:id", Account.class)
                    .setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            return account;
        } catch (Exception e) {
            logger.error("Wrong action with the transaction");
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<IncomeCategory> getIncomeCategory() {
        try {
            session.beginTransaction();
            List<IncomeCategory> incomeCategories = session.createQuery("from IncomeCategory ", IncomeCategory.class).list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            logger.error("Income category doesn`t exist");
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<ExpenseCategory> getExpenseCategory() {
        try {
            session.beginTransaction();
            List<ExpenseCategory> expenseCategories = session.createQuery("from ExpenseCategory ", ExpenseCategory.class).list();
            session.getTransaction().commit();
            return expenseCategories;
        } catch (Exception e) {
            logger.error("Expense category doesn`t exist");
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void addNewOperation(OperationDTO operationDTO) throws SQLException {
        try {
            logger.debug("Start creating Operation");
            session.beginTransaction();
            session.persist(operationDTO);
            var myOperation = new Operation();
            myOperation.setDate(operationDTO.getDate());
            myOperation.setSum(operationDTO.getSum());
            myOperation.setIncomeCategory(session.get(IncomeCategory.class, operationDTO.getId_incomeCategory()));
            myOperation.setExpenseCategory(session.get(ExpenseCategory.class, operationDTO.getId_expenseCategory()));
            logger.debug("Finished creating operation");
            session.persist(myOperation);
            session.getTransaction().commit();
            logger.debug("Transaction saved");
        } catch (RuntimeException e) {
            logger.error("Creating transaction is failed");
            session.getTransaction().rollback();
            throw new SQLException("Creating transaction is failed");
        }
    }

    @Override
    public Long getIdByName(String name) throws Exception {
        try {
            logger.debug("Start searching id by the user name");
            session.beginTransaction();
            Boolean checkExistence = session.createQuery("select (count (User) > 0) as exist from User " +
                    "where name=:name", Boolean.class).setParameter("name", name)
                    .getSingleResult();
            if (!checkExistence) {
                session.getTransaction().rollback();
                logger.error("User was not found");
                throw new Exception("User with the name " + name + " was not found");
            }
            User user = session.createQuery("from User u where u.name = :name", User.class).setParameter("name", name).getSingleResult();
            session.getTransaction().commit();
            logger.debug("Id was found");
            return user.getId();
        } catch (Exception e) {
            logger.error("Searching failed");
            session.getTransaction().rollback();
            throw new Exception("Searching user was failed");
        }
    }
}
