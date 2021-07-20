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
    private final Connection connection;

    public SolutionDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    public void update(Solution solution, int id) throws SQLException {
        try (
                PreparedStatement ps = connection.prepareStatement("UPDATE solution SET cost = (?) WHERE problem_id = (?)")
        ) {
            ps.setInt(1, solution.getCost());
            ps.setInt(2, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        }
    }

    @Override
    public List<Solution> read() throws SQLException {
        List<Solution> allSolutions = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT solution.id , from_id, to_id from problem LEFT JOIN solution s on problem.id = s.problem_id AND s.cost IS NULL")
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                allSolutions.add(new Solution(resultSet.getInt("problem_id"), resultSet.getInt("cost")));
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return allSolutions;
    }

    @Override
    public Solution read(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM solution WHERE problem_id = (?)");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getInt("cost") != 0) {
                return new Solution(resultSet.getInt("problemID"), resultSet.getInt("cost"));
            }
        }
        resultSet.close();
        return null;
    }

}
