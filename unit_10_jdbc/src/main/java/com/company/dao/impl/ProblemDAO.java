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
    private Connection connection;

    public ProblemDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public void create(Problem problem) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Problem(id,fromID,toID) Values(?,?,?)");
            ps.setInt(1, problem.getId());
            ps.setInt(2, problem.getFromID());
            ps.setInt(3, problem.getToID());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List read() throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Problem");
            ResultSet resultSet = ps.executeQuery();
            List<Problem> allProblems = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int from_id = resultSet.getInt("fromID");
                int to_id = resultSet.getInt("toID");
                Problem problem = new Problem();
                problem.setId(id);
                problem.setFromID(from_id);
                problem.setToID(to_id);
                allProblems.add(problem);
            }
            return allProblems;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Problem read(int id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Problem WHERE id = (?)");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int newFromID = resultSet.getInt("fromID");
                int newToID = resultSet.getInt("toID");
                Problem problem = new Problem();
                problem.setFromID(newFromID);
                problem.setToID(newToID);
                return problem;
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    @Override
    public void update(Problem problem, int id) {

    }


    @Override
    public void delete(int id) {

    }
}
