package com.example.fridgeinspector.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.databinding.CategoryListFragmentBinding;

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