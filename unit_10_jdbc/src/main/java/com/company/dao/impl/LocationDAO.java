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
    private Connection connection;

    public LocationDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public void create(Location location) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Locations(id,name) VALUE (?,?)");
        ps.setInt(1, location.getId());
        ps.setString(2, location.getName());
        ps.execute();
    }
    
    @Override
    public List<Location> read() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Locations");
        ResultSet resultSet = ps.executeQuery();
        List<Location> allLocations = new ArrayList<>();
        while (resultSet.next()) {
            int i = resultSet.getInt("id");
            String s = resultSet.getString("name");
            Location location = new Location();
            location.setId(i);
            location.setName(s);
            allLocations.add(location);
        }
        return allLocations;
    }

    @Override
    public Location read(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Locations WHERE id = (?)");
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
