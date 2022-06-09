package com.example.fridgeinspector.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.databinding.CategoryListFragmentBinding;

import java.util.ArrayList;
import java.util.Date;

public class CategoryListFragment extends Fragment {

    private CategoryListFragmentBinding binding;

    public Category category = Category.NONE;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoryListViewModel categoryListViewModel =
                new ViewModelProvider(this).get(CategoryListViewModel.class);

        binding = CategoryListFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setTitle();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.CategoryListRecyclerView.setLayoutManager(linearLayoutManager);

        //just some sample data, categories not filtered yet
        ArrayList<Item> sampleData = new ArrayList<>();
        Item item1 = new Item("name1", Category.FRUIT, new Date());
        Item item2 = new Item("name2", Category.FRUIT, new Date());
        Item item3 = new Item("name3", Category.FRUIT, new Date());
        sampleData.add(item1);
        sampleData.add(item2);
        sampleData.add(item3);


        RecyclerView.Adapter adapter = new CategoryRecyclerviewAdapter(getContext(), sampleData); //TODO: replace sample Data with real data
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

    public void setTitle(){
        switch(category){
            case FRUIT:
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
            default:break;
        }
    }
}