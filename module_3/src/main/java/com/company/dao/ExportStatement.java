package com.company.dao;

import com.company.model.dto.ExportDTO;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public interface ExportStatement extends AccountDAO, UserDAO {
    List<ExportDTO> getOperationByPeriod(Long id, Instant fromDate, Instant toDate) throws SQLException;

}

