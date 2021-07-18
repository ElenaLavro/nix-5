package com.company.entity;

public class Route {
    private int id;
    private int fromID;
    private int toID;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
