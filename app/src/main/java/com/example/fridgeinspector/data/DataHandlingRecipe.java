package com.example.fridgeinspector.data;

import android.content.Context;
import android.database.Cursor;

import com.example.fridgeinspector.DBHelper;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.Recipe;

import java.util.ArrayList;
import java.util.Date;

public class DataHandlingRecipe {

    private final DBHelper DB;

    public DataHandlingRecipe(Context context) {
        DB = new DBHelper(context);
    }

    public void addNewRecipe(Recipe recipe) {
        Boolean checkinsertdata = DB.insertRecipeData(recipe.getName(), recipe.getIngridients(), recipe.getDescription());

        if (checkinsertdata) {
            System.out.println("Insert correct!");
        }
    }

    public void removeRecipe(String name) {
        DB.removeRecipe(name);
    }

    public ArrayList<Recipe> getRecipeData() {
        ArrayList<Recipe> list = new ArrayList<>();
        Cursor res = DB.getRecipeDataFromDB();
        Recipe recipe;
        while (res.moveToNext()) {
            recipe = new Recipe(res.getString(1), res.getString(2), res.getString(3));
            list.add(recipe);
        }
        return list;
    }
}
