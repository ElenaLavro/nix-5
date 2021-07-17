package com.company.task3.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheMostProfitableWayService {

    public void SearchingTheWay() {
        URL resource = TheMostProfitableWayService.class.getResource("/task3/ListOfCities.txt");
        try (
                FileReader fr = new FileReader(Paths.get(resource.toURI()).toFile());
                Scanner sc = new Scanner(fr)
        ) {
            int n = Integer.parseInt(sc.nextLine());
            ArrayList<City> cities = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                City city = new City();
                city.index = i;
                city.name = sc.nextLine();

                int numberOfNeighbors = Integer.parseInt(sc.nextLine());

                for (int j = 0; j < numberOfNeighbors; j++) {
                    String[] inputString = sc.nextLine().split(" ");
                    int numOfCity = Integer.parseInt(inputString[0]);
                    int lengthBetweenCities = Integer.parseInt(inputString[1]);
                    city.neighbors.add(new CitiesNeighbors(numOfCity, lengthBetweenCities));
                }
                cities.add(city);
            }

            int numberOfWays = Integer.parseInt(sc.nextLine());
            String[][] ways = new String[numberOfWays][2];
            for (int i = 0; i < numberOfWays; i++) {
                String[] namesOfTheWay = sc.nextLine().split(" ");
                ways[i][0] = namesOfTheWay[0];
                ways[i][1] = namesOfTheWay[1];
            }

            SimpleWeightedGraph<String, DefaultWeightedEdge> cityGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
            for (int i = 0; i < n; i++) {
                cityGraph.addVertex(cities.get(i).name);
            }

            for (int i = 0; i < n; i++) {
                for (CitiesNeighbors neighbors : cities.get(i).neighbors) {
                    DefaultWeightedEdge defaultWeightedEdge = cityGraph.addEdge(cities.get(i).name, cities.get(neighbors.index - 1).name);
                    if (defaultWeightedEdge != null) {
                        cityGraph.setEdgeWeight(defaultWeightedEdge, neighbors.cost);
                    }
                }
            }
            writingTheResult(ways, cityGraph);

        } catch (URISyntaxException | IOException e) {
            System.out.println("Please, check input data and repeat the operation");
        }
    }

    private static class City {
        private Integer index;
        private String name;
        private final ArrayList<CitiesNeighbors> neighbors = new ArrayList<>();
    }

    private static class CitiesNeighbors {
        private final Integer index;
        private final Integer cost;

        public CitiesNeighbors(Integer index, Integer cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    private void writingTheResult(String[][] ways, SimpleWeightedGraph<String, DefaultWeightedEdge> cityGraph) {
        File file = new File("D:\\Java\\nix5-offline\\module_2\\src\\main\\resources\\task3\\ListOfCitiesOutput.txt");
        try (
                BufferedWriter fw = new BufferedWriter(new FileWriter(file));
        ) {
            for (String[] way : ways) {
                String s = " ";
                DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(cityGraph);
                List<String> shortestWay = shortestPath.getPath(way[0], way[1]).getVertexList();
                double weight = shortestPath.getPath(way[0], way[1]).getWeight();
                fw.write("The shortest way from " + way[0] + " to " + way[1] + " is: ");
                fw.write("\n");


                for (int i = 0; i < shortestWay.size() - 1; i++) {
                    fw.write(shortestWay.get(i) + " ---> ");
                }
                fw.write(shortestWay.get(shortestWay.size() - 1) + "\n");
                fw.write("Length is equal " + weight + "\n");
            }
        } catch (IOException e) {
            System.out.println("Wrong output file. Please, check the data");
        }
    }
}