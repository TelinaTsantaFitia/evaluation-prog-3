package com.telina.exo_5.entity;

public class Dish {

    private int id;
    private String name;

    public Dish() {}

    public Dish(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}