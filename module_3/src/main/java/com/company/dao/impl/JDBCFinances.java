package com.company.dao.impl;

import com.company.dao.ExportStatement;
import com.company.model.dto.AccountDTO;
import com.company.model.dto.ExportDTO;
import com.company.util.MyConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCFinances implements ExportStatement {
    private static final Logger logger = LoggerFactory.getLogger(JDBCFinances.class);
    private final Connection connection;

    public JDBCFinances(Connection connection) throws Exception {
        try {
            this.connection = MyConnection.getConnection();
        } catch (SQLException throwables) {
            logger.error("Connection failed");
            throw new RuntimeException("connection to database failed");
        }
    }


    @Override
    public AccountDTO findAccountByID(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "select account_id, type_of_account, balance, operation_id from account where account_id = (?)"
        )) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                logger.error("Runtime Exception");
                throw new RuntimeException();
            } else {
                while (rs.next()) {
                    Long accountID = rs.getLong("account_id");
                    String type = rs.getString("type_of_account");
                    Integer balance = rs.getInt("balance");
                    Long operation_id = rs.getLong("operation_id");
                    return new AccountDTO(accountID, type, balance, operation_id);
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            logger.error("Account not found");
        }
        return null;
    }

    @Override
    public Long getIdByName(String name) {

        return null;
    }

    @Override
    public List<ExportDTO> getOperationByPeriod(Long id, Instant fromDate, Instant toDate) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM finances.finances_control.account WHERE account_id = (?)")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                throw new RuntimeException();
            }
            rs.close();
        } catch (SQLException throwable) {
            throw new SQLException(String.format("Account with the id = %dwas not found", id));
        }

        try (PreparedStatement ps = connection.prepareStatement("SELECT account_id, balance ,  operation_date, operation_date" +
                " FROM account Join operation o on o.operation_id = account.operation_id " +
                "WHERE account_id = (?) AND operation_date between (?) and (?)")) {
            ps.setLong(1, id);
            ps.setTimestamp(2, Timestamp.from(fromDate));
            ps.setTimestamp(3, Timestamp.from(toDate));
            ResultSet rs = ps.executeQuery();

            List<ExportDTO> exportDTOList = new ArrayList<>();
            while (rs.next()) {
                Long accountID = rs.getLong("account_id");
                String type = rs.getString("type_of_account");
                Integer balance = rs.getInt("balance");
                Integer sum = rs.getInt("sum");
                Instant operationDate = rs.getTimestamp("operation_date").toInstant();
                String username = rs.getString("name");

                exportDTOList.add(new ExportDTO(accountID, type, balance, sum, operationDate, username));

            }
            rs.close();
            return exportDTOList;
        }

    }
}
