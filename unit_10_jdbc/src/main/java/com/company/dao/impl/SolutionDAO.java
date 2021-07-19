package com.company.dao.impl;

import com.company.dao.BaseDao;
import com.company.entity.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDAO implements BaseDao<Solution> {
    private Connection connection;

    public SolutionDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public void create(Solution solution) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Solution(problemID,cost) Values(?,?)");
        ps.setInt(1, solution.getProblemID());
        ps.setInt(2, solution.getCost());
        ps.executeBatch();
    }

    @Override
    public List<Solution> read() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Solution");
        ResultSet resultSet = ps.executeQuery();
        List<Solution> allSolutions = new ArrayList<>();
        while (resultSet.next()) {
            int problemID = resultSet.getInt("problemID");
            int cost = resultSet.getInt("cost");
            Solution solution = new Solution();
            solution.setProblemID(problemID);
            solution.setCost(cost);
            allSolutions.add(solution);
        }
        return allSolutions;
    }

    @Override
    public Solution read(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Solution WHERE id = (?)");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int newProblemId = resultSet.getInt("problemID");
            int newCost = resultSet.getInt("cost");
            Solution solution = new Solution();
            solution.setProblemID(newProblemId);
            solution.setCost(newCost);
            return solution;
        }
        return null;
    }

    @Override
    public void update(Solution solution, int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE Solution SET cost = (?) WHERE problemID = (?)");
        ps.setInt(1, solution.getCost());
        ps.setInt(2, id);
        ps.execute();
    }

    @Override
    public void delete(int id) {

    }
}
