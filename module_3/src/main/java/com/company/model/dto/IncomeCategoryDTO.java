package com.company.model.dto;

import java.util.List;

public class IncomeCategoryDTO {
    private Long income_id;
    private String income_name;
    private List<String> operationDTOS;

    public IncomeCategoryDTO() {

    }

    public Long getIncome_id() {
        return income_id;
    }

    public void setIncome_id(Long income_id) {
        this.income_id = income_id;
    }

    public String getIncome_name() {
        return income_name;
    }

    public void setIncome_name(String income_name) {
        this.income_name = income_name;
    }

    public List<String> getOperationDTOS() {
        return operationDTOS;
    }

    public void setOperationDTOS(List<String> operationDTOS) {
        this.operationDTOS = operationDTOS;
    }
}
