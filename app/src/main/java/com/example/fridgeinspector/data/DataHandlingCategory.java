package com.example.fridgeinspector.data;

import android.content.Context;
import android.database.Cursor;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.DBHelper;
import com.example.fridgeinspector.Item;

import java.util.ArrayList;
import java.util.Date;

public class DataHandlingCategory {

    private final DBHelper DB;

    public DataHandlingCategory(Context context) {
        DB = new DBHelper(context);
    }

    public void addNewFood(Item item) {
        Boolean checkinsertdata = DB.insertFoodData(item.getName(), item.getCategory().toString(), item.getExpirationDate(), item.getQuantity());

        if (checkinsertdata) {
            System.out.println("Insert correct!");
        }
    }

    public void removeFoodItem(String name) {

        DB.removeFoodItem(name);
    }

    public ArrayList<Item> getFoodData() {
        ArrayList<Item> list = new ArrayList<>();
        Cursor res = DB.getFoodDataFromDB();
        Item item;
        while (res.moveToNext()) {
            item = new Item(res.getString(1), getCategory(res.getString(2)), new Date(res.getString(3)), res.getInt(4));
            list.add(item);
        }
        return list;
    }

    public Category getCategory(String string) {
        switch (string) {
            case "Vegetables":
                return Category.VEGETABLES;
            case "Fruits":
                return Category.FRUITS;
            case "Sweets":
                return Category.SWEETS;
            case "Beverages":
                return Category.BEVERAGES;
            case "Meat and Sausages":
                return Category.MEAT_AND_SAUSAGES;
            case "Dairy Products":
                return Category.DAIRY_PRODUCTS;
            case "Frozen":
                return Category.FROZEN;
            case "Fish":
                return Category.FISH;
            case "Others":
                return Category.OTHERS;
            default:
                return null;
        }
    }

    // 1. Versuch mit JSON --> read hat geklappt, write funktioniert nicht im assets-Folder.
    // --> daher doch mit Datenbanken!

    /*public ArrayList<Item> getItemDataFromFile(Context context) {
        ArrayList<Item> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(readJsonDataFromFile(context));
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodData = jsonArray.getJSONObject(i);
                Date expirationDate = new SimpleDateFormat("dd.MM.yyyy").parse(foodData.getString("expirationDate"));
                Item item = new Item(foodData.getString("name"), getCategory(foodData.getString("category")), expirationDate, foodData.getInt("quantity"));
                data.add(item);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return data;
    }*/

    /*private String readJsonDataFromFile(Context context) {
        File file = new File(context.getFilesDir(),"foodDataJava.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null){
            stringBuilder.append(line).append("\n");
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();// This responce will have Json Format String
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responce = stringBuilder.toString();
        System.out.println(responce);
        return responce;
        String json = "";
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }*/

    /*public void writeInputDataIntoFile(Context context, String str) throws IOException {

        File file = new File(context.getFilesDir(), "foodDataJava.json");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(str);
        bufferedWriter.close();

        try {

            FileOutputStream stream = context.openFileOutput("data.txt", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void serializeClassGSON(Context context, Item item) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(item);
        writeInputDataIntoFile(context, jsonString);
    }*/
}
