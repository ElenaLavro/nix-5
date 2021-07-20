package com.company.dao.impl;

import com.company.dao.BaseDao;
import com.company.entity.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDAO implements BaseDao<Problem> {
    private final Connection connection;

    public ProblemDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public List<Problem> read() throws SQLException {
        List<Problem> allProblems = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM problem")
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                allProblems.add(new Problem(resultSet.getInt("id"), resultSet.getInt("from_id"), resultSet.getInt("to_id")));
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return allProblems;
    }

    @Override
    public Problem read(int id) {
        return null;
    }
}
