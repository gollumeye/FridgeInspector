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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class CategoryListFragment extends Fragment {

    private CategoryListFragmentBinding binding;

    public Category category = Category.NONE;
    private ArrayList<Item> data;

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

        data = getFoodDataFromFile();

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
                setListAdapterWithCategory(category);
                break;
            case FROZEN:
                binding.categoryListTitle.setText("Frozen: ");
                setListAdapterWithCategory(category);
                break;
            case OTHERS:
                binding.categoryListTitle.setText("Others: ");
                setListAdapterWithCategory(category);
                break;
            case SWEETS:
                binding.categoryListTitle.setText("Sweets: ");
                setListAdapterWithCategory(category);
                break;
            case BEVERAGES:
                binding.categoryListTitle.setText("Beverages: ");
                setListAdapterWithCategory(category);
                break;
            case VEGETABLES:
                binding.categoryListTitle.setText("Vegetables: ");
                setListAdapterWithCategory(category);
                break;
            case DAIRY_PRODUCTS:
                binding.categoryListTitle.setText("Dairy Products: ");
                setListAdapterWithCategory(category);
                break;
            case MEAT_AND_SAUSAGES:
                binding.categoryListTitle.setText("Meat and Sausages: ");
                setListAdapterWithCategory(category);
                break;
            case FISH:
                binding.categoryListTitle.setText("Fish: ");
                setListAdapterWithCategory(category);
            default:
                break;
        }
    }

    private void setListAdapterWithCategory(Category category) {

        ArrayList<Item> categoryData = new ArrayList<>();

        for (Item item : data) {
            System.out.println(item.getCategory() + " " + category + " " + item.getCategory().equals(category));
            if (item.getCategory().equals(category)) {
                categoryData.add(item);
            }
        }

        RecyclerView.Adapter adapter = new CategoryRecyclerviewAdapter(getContext(), categoryData);
        binding.CategoryListRecyclerView.setAdapter(adapter);
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
}