package com.company.entity;

public class Problem {
    private int id;
    private int from_id;
    private int to_id;


    public Problem(int id, int fromID, int toID) {
        this.id = id;
        this.from_id = fromID;
        this.to_id = toID;
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
}
