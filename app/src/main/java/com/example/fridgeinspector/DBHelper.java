package com.example.fridgeinspector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, "FridgeInspectorDB.db", null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        try {
            DB.execSQL("create table fooddetails ( food_id INTEGER primary key AUTOINCREMENT, name TEXT, category TEXT, date TEXT, quantity INTEGER )");
            DB.execSQL("create table recipedetails ( recipe_id INTEGER primary key AUTOINCREMENT, name TEXT, ingredients TEXT, description TEXT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists fooddetails");
        DB.execSQL("drop Table if exists recipedetails");
    }

    public Boolean insertFoodData(String name, String category, Date expirationDate, int quantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("category", category);
        contentValues.put("date", expirationDate.toString());
        contentValues.put("quantity", quantity);
        long result = db.insert("fooddetails", null, contentValues);
        return result != -1;
    }

    public void removeFoodItem(String name) {
        db.delete("fooddetails", "name=?", new String[]{name});
    }

    public Cursor getFoodDataFromDB() {
        return db.rawQuery("SELECT * from fooddetails", null);
    }

    public Boolean insertRecipeData(String name, String ingredients, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("ingredients", ingredients);
        contentValues.put("description", description);
        long result = db.insert("recipedetails", null, contentValues);
        return result != -1;
    }

    public void removeRecipe(String name) {
        db.delete("recipedetails", "name=?", new String[]{name});
    }

    public Cursor getRecipeDataFromDB() {
        return db.rawQuery("SELECT * from recipedetails", null);
    }
}
