package com.company.model.dto;

import java.util.List;

public class IncomeCategoryDTO {
    private Long id;
    private String name;
    private List<String> operationDTOS;

    public IncomeCategoryDTO() {

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

    public List<String> getOperationDTOS() {
        return operationDTOS;
    }

    public void setOperationDTOS(List<String> operationDTOS) {
        this.operationDTOS = operationDTOS;
    }
}
