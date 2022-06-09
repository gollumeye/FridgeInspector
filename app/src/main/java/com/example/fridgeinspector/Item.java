package com.example.fridgeinspector;

import java.util.Date;

public class Item {
    String name;
    Category category;
    Date expirationDate;

    public Item(String name, Category category, Date expirationDate){
        this.name=name;
        this.category=category;
        this.expirationDate=expirationDate;
    }
}
