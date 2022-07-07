package com.example.fridgeinspector.ui.recipes;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.Item;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.Receipe;
import com.example.fridgeinspector.databinding.FragmentRecipesBinding;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment {

    private RecipeDetailsViewModel mViewModel;

    public static RecipeDetailsFragment newInstance() {
        return new RecipeDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setDetails(ArrayList<Receipe> recipes, String name){
        for (Receipe recipe: recipes
        ) {
            if(recipe.getName().equals(name)){
                TextView nameTextView = getView().findViewById(R.id.recipeDetailsTitle);
                nameTextView.setText(name);
                TextView ingridientsTextView = getView().findViewById(R.id.recipeDetailsIngridientList);
                String ingridients = "";
                for (String ingridient: recipe.getIngridients()
                     ) {
                    ingridients = ingridients + ingridient + "\n";
                }
                ingridientsTextView.setText(ingridients);
                TextView description = getView().findViewById(R.id.recipeDetailsDescriptionText);
                description.setText(recipe.getDescription());
            }
        }
    }

}