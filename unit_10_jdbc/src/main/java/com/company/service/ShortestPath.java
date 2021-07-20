package com.company.service;

import com.company.entity.Location;
import com.company.entity.Route;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public class ShortestPath {
    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private List<Location> vertices;
    private List<Route> edges;

    public ShortestPath(List<Location> vertices, List<Route> edges) {

        this.vertices = vertices;
        this.edges = edges;
        addVertices();
        addEdges();

    }

    private void addVertices() {
        for (Location vertex : vertices) {
            graph.addVertex(vertex.getId());
        }
    }

    private void addEdges() {
        for (Route edge : edges) {
            DefaultWeightedEdge thisEdge = graph.addEdge(edge.getFrom_id(), edge.getTo_id());
            if (thisEdge != null) {
                graph.setEdgeWeight(thisEdge, edge.getCost());
            }
        }
    }

    public int difBetweenCities(int from, int to) {
        DijkstraShortestPath<Integer, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        return (int) (shortestPath.getPath(from, to).getWeight());
    }
}
