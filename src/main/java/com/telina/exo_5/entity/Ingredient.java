package com.telina.exo_5.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ingredient {

    private int id;
    private String name;
    private String category;
    private int unit;

    public Ingredient() {}

    public Ingredient(int id, String name, String category, int unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unit = unit;
    }

}