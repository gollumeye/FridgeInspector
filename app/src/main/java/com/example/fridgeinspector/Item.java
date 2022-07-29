package com.example.fridgeinspector;

import java.util.Date;

public class Item {
    private String name;
    private Category category;
    private Date expirationDate;
    private int quantity;

    public Item(String name, Category category, Date expirationDate, int quantity){
        this.name=name;
        this.category=category;
        this.expirationDate=expirationDate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                '}';
    }
}
