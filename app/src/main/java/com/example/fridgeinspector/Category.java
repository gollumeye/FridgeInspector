package com.example.fridgeinspector;

import androidx.annotation.NonNull;

public enum Category {
    VEGETABLES ("Vegetables"),
    FRUITS ("Fruits"),
    SWEETS ("Sweets"),
    BEVERAGES ("Beverages"),
    MEAT_AND_SAUSAGES ("Meat and Sausages"),
    DAIRY_PRODUCTS ("Dairy Products"),
    FROZEN ("Frozen"),
    FISH ("Fish"),
    OTHERS ("Others"),
    NONE ("");  // Only used for initialization in CategoryListFragment

    private final String name;

    Category(String string){
        name=string;
    }

    @NonNull
    public String toString(){
        return this.name;
    }
}
