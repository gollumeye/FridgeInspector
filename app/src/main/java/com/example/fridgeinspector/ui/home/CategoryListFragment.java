package com.example.fridgeinspector.ui.home;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.data.DataHandlingCategory;
import com.example.fridgeinspector.databinding.CategoryListFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CategoryListFragment extends Fragment {

    private CategoryListFragmentBinding binding;

    public Category category = Category.NONE;
    private ArrayList<Item> data;
    private DataHandlingCategory dhc;
    private CategoryRecyclerviewAdapter adapter;
    private String sortBy = "Name";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = CategoryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setTitle();
        dhc = new DataHandlingCategory(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.CategoryListRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.CategoryListRecyclerView.getContext(), linearLayoutManager.getOrientation());
        binding.CategoryListRecyclerView.addItemDecoration(dividerItemDecoration);

        Spinner spinner = (Spinner) binding.sortingSpinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
               if(parent.getSelectedItem().toString().equals("Name")) {
                   sortDataByName();
                   sortBy = "Name";
                   setListAdapterWithCategory(dhc.getCategory(binding.categoryListTitle.getText().toString()));
               } else if(parent.getSelectedItem().toString().equals("Expiration Date")) {
                   sortDataByDate();
                   sortBy = "Date";
                   setListAdapterWithCategory(dhc.getCategory(binding.categoryListTitle.getText().toString()));
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }

    private void sortDataByName() {
        Comparator<Item> compareByName = new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getName().compareTo(item2.getName());
            }
        };

        Collections.sort(data, compareByName);
    }

    private void sortDataByDate() {
        Comparator<Item> compareByDate = new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getExpirationDate().compareTo(item2.getExpirationDate());
            }
        };

        Collections.sort(data, compareByDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setTitle() {
        switch (category) {
            case FRUITS:
                binding.categoryListTitle.setText("Fruits");
                setListAdapterWithCategory(category);
                break;
            case FROZEN:
                binding.categoryListTitle.setText("Frozen");
                setListAdapterWithCategory(category);
                break;
            case OTHERS:
                binding.categoryListTitle.setText("Others");
                setListAdapterWithCategory(category);
                break;
            case SWEETS:
                binding.categoryListTitle.setText("Sweets");
                setListAdapterWithCategory(category);
                break;
            case BEVERAGES:
                binding.categoryListTitle.setText("Beverages");
                setListAdapterWithCategory(category);
                break;
            case VEGETABLES:
                binding.categoryListTitle.setText("Vegetables");
                setListAdapterWithCategory(category);
                break;
            case DAIRY_PRODUCTS:
                binding.categoryListTitle.setText("Dairy Products");
                setListAdapterWithCategory(category);
                break;
            case MEAT_AND_SAUSAGES:
                binding.categoryListTitle.setText("Meat and Sausages");
                setListAdapterWithCategory(category);
                break;
            case FISH:
                binding.categoryListTitle.setText("Fish");
                setListAdapterWithCategory(category);
            default:
                break;
        }
    }

    public void setListAdapterWithCategory(Category category) {

        ArrayList<Item> categoryData = new ArrayList<>();
        data = dhc.getFoodData();
        if(sortBy.equals("Name")) {
            sortDataByName();
        } else {
            sortDataByDate();
        }

        for (Item item : data) {
            System.out.println(item.getCategory() + " " + category + " " + item.getCategory().equals(category));
            if (item.getCategory().equals(category)) {
                categoryData.add(item);
            }
        }

        adapter = new CategoryRecyclerviewAdapter(getContext(), categoryData);
        binding.CategoryListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // 1. Versuch mit JSON --> read hat geklappt, write funktioniert nicht im assets-Folder.
    // --> daher doch mit Datenbanken!

    /*public ArrayList<Item> getItemDataFromFile() {
        ArrayList<Item> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(readJsonDataFromFile());
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

    /*private String readJsonDataFromFile() {
        String json = "";
        try {
            InputStream inputStream = getContext().getAssets().open(fileName);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void writeInputDataIntoFile(String str) {
        try {
            FileOutputStream fos = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serializeClassGSON(Item item) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(item);
        writeInputDataIntoFile(jsonString);
    }*/
}