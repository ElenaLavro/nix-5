package com.company.dao;

import com.company.model.dto.OperationDTO;

import java.sql.SQLException;

public interface OperationDAO {
    void addNewOperation(OperationDTO operationDTO) throws SQLException;
}
