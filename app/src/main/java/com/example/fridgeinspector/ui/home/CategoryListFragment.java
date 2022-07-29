package com.example.fridgeinspector.ui.home;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.data.DataHandlingCategory;
import com.example.fridgeinspector.databinding.CategoryListFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

    private CategoryListFragmentBinding binding;

    public Category category = Category.NONE;
    private ArrayList<Item> data;
    private DataHandlingCategory dhc;
    private CategoryRecyclerviewAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = CategoryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setTitle();
        dhc = new DataHandlingCategory(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.CategoryListRecyclerView.setLayoutManager(linearLayoutManager);

        data = dhc.getFoodData();

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
        data = dhc.getFoodData();

        for (Item item : data) {
            System.out.println(item.getCategory() + " " + category + " " + item.getCategory().equals(category));
            if (item.getCategory().equals(category)) {
                categoryData.add(item);
            }
        }

        adapter = new CategoryRecyclerviewAdapter(getContext(), categoryData);
        binding.CategoryListRecyclerView.setAdapter(adapter);
    }

    // 1. Versuch mit JSON --> read hat geklappt, write funktioniert nicht im assets-Folder
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