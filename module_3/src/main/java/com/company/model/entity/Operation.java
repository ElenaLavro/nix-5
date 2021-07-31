package com.company.model.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long id;

    @Column(nullable = false)
    private Integer sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_income")
    @Access(AccessType.PROPERTY)
    private IncomeCategory incomeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expense")
    @Access(AccessType.PROPERTY)
    private ExpenseCategory expenseCategory;
    @Column(nullable = false)
    private Instant date;

    public Operation() {
    }

    public Operation(Integer sum, IncomeCategory incomeCategory, ExpenseCategory expenseCategory, Instant date) {
        this.sum = sum;
        this.incomeCategory = incomeCategory;
        this.expenseCategory = expenseCategory;
        this.date = date;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
