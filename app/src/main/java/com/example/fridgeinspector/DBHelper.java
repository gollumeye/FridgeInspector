package com.example.fridgeinspector;

import android.annotation.SuppressLint;
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
        super(context, "Fridgeinspector.db", null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        try {
            DB.execSQL("create table fooddetails ( name TEXT primary key, category TEXT, date TEXT, quantity INTEGER )");
            DB.execSQL("create table recipedetails ( name TEXT primary key, ingredients TEXT, description TEXT )");

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

    public Boolean updateFoodData(String name, String category, Date expirationDate, int quantity) {

        ContentValues contentValues = new ContentValues();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * from Fooddetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {

            contentValues.put("name", name);
            contentValues.put("category", category);
            contentValues.put("date", expirationDate.toString());
            contentValues.put("quantity", quantity);
            long result = db.update("Fooddetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        }
        return false;
    }

    public Cursor getFoodDataFromDB() {
        return db.rawQuery("SELECT * from Fooddetails", null);
    }
}
