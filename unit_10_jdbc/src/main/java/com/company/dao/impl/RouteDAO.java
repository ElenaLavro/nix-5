package com.company.dao.impl;

import com.company.dao.BaseDao;
import com.company.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements BaseDao<Route> {
    private final Connection connection;

    public RouteDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public Route read(int id) {
        return null;
    }

    @Override
    public List<Route> read() throws SQLException {
        List<Route> allRoutes = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM route")
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                allRoutes.add(new Route(resultSet.getInt("id"), resultSet.getInt("from_id"), resultSet.getInt("to_id"), resultSet.getInt("cost")));
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return allRoutes;
    }
}
