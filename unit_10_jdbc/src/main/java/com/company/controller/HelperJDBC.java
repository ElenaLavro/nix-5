package com.company.controller;

import com.company.dao.impl.LocationDAO;
import com.company.dao.impl.ProblemDAO;
import com.company.dao.impl.RouteDAO;
import com.company.dao.impl.SolutionDAO;
import com.company.db.ConnectingToDB;
import com.company.entity.Location;
import com.company.entity.Problem;
import com.company.entity.Route;
import com.company.entity.Solution;
import com.company.service.ShortestPath;

import java.sql.Connection;
import java.util.List;

public class HelperJDBC {
    public void run() {
        {
            try  {
                Connection connection = ConnectingToDB.getConnection();
                LocationDAO location = new LocationDAO(connection);
                ProblemDAO problem = new ProblemDAO(connection);
                RouteDAO route = new RouteDAO(connection);
                SolutionDAO solution = new SolutionDAO(connection);


                List<Location> allLocations = location.read();
                List<Problem> allProblems = problem.read();
                List<Route> allRoutes = route.read();

                ShortestPath sp = new ShortestPath(allLocations, allRoutes);
                for (Problem allProblem : allProblems) {
                    int minLengthOfPath = sp.difBetweenCities(allProblem.getFromID(), allProblem.getToID());
                    Solution mySolution = new Solution();
                    mySolution.setCost(minLengthOfPath);
                    solution.update(mySolution, allProblem.getId());
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
