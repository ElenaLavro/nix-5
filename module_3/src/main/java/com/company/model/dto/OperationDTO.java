package com.company.model.dto;

import java.time.Instant;

public class OperationDTO {
    private Long id;
    private Integer sum;
    private Long id_incomeCategory;
    private Long id_expenseCategory;
    private Instant date;

    public OperationDTO(Long id, Integer sum, Long id_incomeCategory, Long id_expenseCategory, Instant date) {
        this.id = id;
        this.sum = sum;
        this.id_incomeCategory = id_incomeCategory;
        this.id_expenseCategory = id_expenseCategory;
        this.date = date;
    }

    public OperationDTO() {
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

    public Long getId_incomeCategory() {
        return id_incomeCategory;
    }

    public void setId_incomeCategory(Long id_incomeCategory) {
        this.id_incomeCategory = id_incomeCategory;
    }

    public Long getId_expenseCategory() {
        return id_expenseCategory;
    }

    public void setId_expenseCategory(Long id_expenseCategory) {
        this.id_expenseCategory = id_expenseCategory;
    }
}
