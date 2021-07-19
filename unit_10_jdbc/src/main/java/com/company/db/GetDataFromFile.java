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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class GetDataFromFile {
    public void getData() {
        try
//                InputStream is = ConnectingToDB.class.getResourceAsStream("/inputData.txt");
//                BufferedReader fr = new BufferedReader(new InputStreamReader(is));
//                Scanner sc = new Scanner(fr)
        {
            InputStream is = ConnectingToDB.class.getResourceAsStream("/inputData.txt");
            BufferedReader fr = new BufferedReader(new InputStreamReader(is));
            Scanner sc = new Scanner(fr);

            Connection connection = ConnectingToDB.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            LocationDAO locationDAO = new LocationDAO(connection, statement);
            ProblemDAO problemDAO = new ProblemDAO(connection);
            RouteDAO routeDAO = new RouteDAO(connection);
            SolutionDAO solutionDAO = new SolutionDAO(connection);

            int n = Integer.parseInt(sc.nextLine());
            for (int i = 1; i < n + 1; i++) {
                Location location = new Location();
                location.setId(i);
                location.setName(sc.nextLine());
                locationDAO.create(location);


                int numberOfNeighbors = Integer.parseInt(sc.nextLine());
                for (int j = 1; j < numberOfNeighbors + 1; j++) {
                    String[] inputString = sc.nextLine().split(" ");
                    int numOfCity = Integer.parseInt(inputString[0]);
                    int lengthBetweenCities = Integer.parseInt(inputString[1]);
                    Route route = new Route();
                    route.setId(j);
                    route.setFromID(i);
                    route.setToID(numOfCity);
                    route.setCost(lengthBetweenCities);
                    routeDAO.create(route);
                }
            }

            int numberOfWays = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < numberOfWays; i++) {
                Problem problem = new Problem();
                String[] namesOfTheWay = sc.nextLine().split(" ");
                problem.setId(i);
                problem.setFromID(Integer.parseInt(namesOfTheWay[0]));
                problem.setToID(Integer.parseInt(namesOfTheWay[1]));
                problemDAO.create(problem);
            }

            List<Location> allLocations = locationDAO.read();
            List<Problem> allProblems = problemDAO.read();
            List<Route> allRoutes = routeDAO.read();

            ShortestPath sp = new ShortestPath(allLocations, allRoutes);
            for (Problem allProblem : allProblems) {
                int minLengthOfPath = sp.difBetweenCities(allProblem.getFromID(), allProblem.getToID());
                Solution mySolution = new Solution();
                mySolution.setCost(minLengthOfPath);
                solutionDAO.update(mySolution, allProblem.getId());
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {

            System.out.println("SQLExc.Please, check input data and repeat the operation");
        } catch (IOException e) {
            System.out.println("IOException.Please, check input data and repeat the operation");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}