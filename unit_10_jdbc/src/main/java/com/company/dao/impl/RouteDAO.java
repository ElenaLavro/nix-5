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
    private Connection connection;

    public RouteDAO(Connection thisConnection) {
        connection = thisConnection;
    }

    @Override
    public void create(Route route) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Route(id, fromID, toID, cost Values(?,?,?,?)");
        ps.setInt(1,route.getId());
        ps.setInt(2, route.getFromID());
        ps.setInt(3, route.getToID());
        ps.setInt(4, route.getCost());
        ps.executeBatch();
    }

    @Override
    public List<Route> read() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Route");
        ResultSet resultSet = ps.executeQuery();
        List<Route> allRoutes = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            int from_id = resultSet.getInt("fromID");
            int to_id = resultSet.getInt("toID");
            int cost = resultSet.getInt("cost");
            Route route = new Route();
            route.setId(id);
            route.setFromID(from_id);
            route.setToID(to_id);
            route.setCost(cost);
            allRoutes.add(route);
        }
        return allRoutes;
    }

    @Override
    public Route read(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Route WHERE id = (?)");
        ps.setInt(1,id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int newFromID = resultSet.getInt("fromID");
            int newToID = resultSet.getInt("toID");
            int newCost = resultSet.getInt("cost");
            Route route = new Route();
            route.setFromID(newFromID);
            route.setToID(newToID);
            route.setCost(newCost);
            return route;
        }
        return null;
    }

    @Override
    public void update(Route route, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
