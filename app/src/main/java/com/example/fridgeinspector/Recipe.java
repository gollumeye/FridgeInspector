package com.example.fridgeinspector;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<String> ingridients;
    private String description;

    public Recipe(String name, ArrayList<String> ingridients, String description) {
        this.name = name;
        this.ingridients = ingridients;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngridients() {
        return ingridients;
    }

    public void setIngridients(ArrayList<String> ingridients) {
        this.ingridients = ingridients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
