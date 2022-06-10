package com.example.fridgeinspector;

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
    NONE ("");  //only used for initialization in CateogryListFragment

    private final String name;

    private Category(String string){
        name=string;
    }

    public String toString(){
        return this.name;
    }
}
