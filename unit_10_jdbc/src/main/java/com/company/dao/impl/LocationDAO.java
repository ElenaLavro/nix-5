package com.company.dao.impl;

import com.company.dao.BaseDao;
import com.company.entity.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements BaseDao<Location> {
    private final Connection connection;
    private Statement statement;

    public LocationDAO(Connection thisConnection, Statement thisStatement) {
        connection = thisConnection;
        statement = thisStatement;
    }

    @Override
    public void create(Location location) throws SQLException {
        try (
                PreparedStatement ps = connection.prepareStatement("INSERT INTO location (id, name) VALUES (?,?) on conflict do nothing",
                        PreparedStatement.RETURN_GENERATED_KEYS
                )) {
            ps.setInt(1, location.getId());
            ps.setString(2, location.getName());
            statement = connection.createStatement();
            statement.executeBatch();
//            ps.executeBatch();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Location> read() throws SQLException {
        try
//        PreparedStatement ps = connection.prepareStatement()
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM location");
            List<Location> allLocations = new ArrayList<>();
            Location location;
            while (resultSet.next()) {
                location = new Location();
                int i = resultSet.getInt("id");
                String s = resultSet.getString("name");
                location.setId(i);
                location.setName(s);
                allLocations.add(location);
            }
            resultSet.close();
            statement.close();
            return allLocations;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Location read(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Location WHERE id = (?)");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String s = resultSet.getString("name");
            Location location = new Location();
            location.setName(s);
            return location;
        }
        return null;
    }

    @Override
    public void update(Location location, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
