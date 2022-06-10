package com.example.fridgeinspector.ui.home;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.MainActivity;
import com.example.fridgeinspector.databinding.CategoryListFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class CategoryListFragment extends Fragment {

    private CategoryListFragmentBinding binding;

    public Category category = Category.NONE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoryListViewModel categoryListViewModel =
                new ViewModelProvider(this).get(CategoryListViewModel.class);

        binding = CategoryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setTitle();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.CategoryListRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Item> data = getFoodDataFromFile();

        RecyclerView.Adapter adapter = new CategoryRecyclerviewAdapter(getContext(), data);
        binding.CategoryListRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.CategoryListRecyclerView.getContext(), linearLayoutManager.getOrientation());
        binding.CategoryListRecyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setTitle() {
        switch (category) {
            case FRUITS:
                binding.categoryListTitle.setText("Fruit: ");
                break;
            case FROZEN:
                binding.categoryListTitle.setText("Frozen: ");
                break;
            case OTHERS:
                binding.categoryListTitle.setText("Others: ");
                break;
            case SWEETS:
                binding.categoryListTitle.setText("Sweets: ");
                break;
            case BEVERAGES:
                binding.categoryListTitle.setText("Beverages: ");
                break;
            case VEGETABLES:
                binding.categoryListTitle.setText("Vegetables: ");
                break;
            case DAIRY_PRODUCTS:
                binding.categoryListTitle.setText("Dairy Products: ");
                break;
            case MEAT_AND_SAUSAGES:
                binding.categoryListTitle.setText("Meat and Sausages: ");
                break;
            case FISH:
                binding.categoryListTitle.setText("Fish: ");
            default:
                break;
        }
    }

    public ArrayList<Item> getFoodDataFromFile() {
        ArrayList<Item> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodData = jsonArray.getJSONObject(i);
                Item item = new Item(foodData.getString("name"), getCategory(foodData.getString("category")), new Date(), foodData.getInt("quantity"));
                data.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String jsonDataFromAsset() {
        String json = "";
        try {
            InputStream inputStream = getContext().getAssets().open("foodData.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Category getCategory(String string) {
        switch(string) {
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
}