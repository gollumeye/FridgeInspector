package com.example.fridgeinspector;

public class Recipe {
    private String name;
    private String ingredients;
    private String description;

    public Recipe(String name, String ingredients, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngridients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
