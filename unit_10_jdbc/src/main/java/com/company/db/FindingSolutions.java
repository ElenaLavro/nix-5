package com.company.db;

import com.company.dao.impl.LocationDAO;
import com.company.dao.impl.ProblemDAO;
import com.company.dao.impl.RouteDAO;
import com.company.dao.impl.SolutionDAO;
import com.company.entity.Location;
import com.company.entity.Problem;
import com.company.entity.Route;
import com.company.entity.Solution;
import com.company.service.ShortestPath;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindingSolutions {
    public void getData() {
        try {
            Connection connection = ConnectingToDB.getConnection();
            connection.setAutoCommit(false);

            LocationDAO locationDAO = new LocationDAO(connection);
            ProblemDAO problemDAO = new ProblemDAO(connection);
            RouteDAO routeDAO = new RouteDAO(connection);
            SolutionDAO solutionDAO = new SolutionDAO(connection);


            List<Location> allLocations = locationDAO.read();
            List<Problem> allProblems = problemDAO.read();
            List<Route> allRoutes = routeDAO.read();

            ShortestPath sp = new ShortestPath(allLocations, allRoutes);
            for (Problem allProblem : allProblems) {
                int minLengthOfPath = sp.difBetweenCities(allProblem.getFrom_id(), allProblem.getTo_id());
                Solution mySolution = new Solution(allProblem.getId(), minLengthOfPath);
                solutionDAO.update(mySolution, allProblem.getId());
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException.Please, check input data and repeat the operation");
        } catch (IOException e) {
            System.out.println("IOException.Please, check input data and repeat the operation");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}