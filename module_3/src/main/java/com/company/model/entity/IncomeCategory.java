package com.company.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "income_category")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_income_category")
    private Long id;

    @Column(name = "name_income_category")
    private String name;

    @OneToMany(mappedBy = "income_category")
    private List<Operation> operations;

    public IncomeCategory(Long id, String income_name) {
        this.id = id;
        this.name = income_name;
        this.operations = new ArrayList<>();
    }

    public IncomeCategory() {
        this.operations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.setIncomeCategory(this);
    }

}
