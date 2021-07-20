package com.company.entity;

public class Route {
    private int id;
    private int from_id;
    private int to_id;
    private int cost;


    public Route(int id, int fromID, int toID, int cost) {
        this.id = id;
        this.from_id = fromID;
        this.to_id = toID;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
