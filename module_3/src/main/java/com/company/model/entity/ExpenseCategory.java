package com.company.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense_category")
    private Long id;

    @Column(name = "name_expense_category")
    private String expenseName;

    @OneToMany
    private List<Operation> operations;

    public ExpenseCategory(Long id, String expenseName) {
        this.id = id;
        this.expenseName = expenseName;
        this.operations = new ArrayList<>();
    }

    public ExpenseCategory() {
        this.operations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.setExpenseCategory(this);
    }
}
