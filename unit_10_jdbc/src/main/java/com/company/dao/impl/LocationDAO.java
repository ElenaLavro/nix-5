package com.company.dao.impl;

import com.company.dao.BaseDao;
import com.company.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements BaseDao<Location> {
    private final Connection connection;

    public LocationDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public Location read(int id) {
        return null;
    }

    @Override
    public List<Location> read() throws SQLException {
        List<Location> allLocations = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM location")
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                allLocations.add(new Location(resultSet.getInt("id"), resultSet.getString("name")));
            }
            ps.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return allLocations;
    }
}
