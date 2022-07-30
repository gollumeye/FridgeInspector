package com.example.fridgeinspector.ui.recipes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.R;
import com.example.fridgeinspector.Recipe;
import com.example.fridgeinspector.data.DataHandlingRecipe;
import com.example.fridgeinspector.databinding.FragmentRecipesBinding;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private ArrayList<Recipe> recipes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DataHandlingRecipe dhr = new DataHandlingRecipe(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.receipesRecyclerView.setLayoutManager(linearLayoutManager);

        recipes = dhr.getRecipeData();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.receipesRecyclerView.getContext(), linearLayoutManager.getOrientation());
        binding.receipesRecyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.Adapter<RecipesRecyclerviewAdapter.ViewHolder> adapter = new RecipesRecyclerviewAdapter(getContext(), recipes);
        binding.receipesRecyclerView.setAdapter(adapter);

        binding.closeRecipeDetailsButton.setOnClickListener(e -> setDetailsFragmentInVisible());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setDetailsFragmentVisible(String name) {
        binding.RecipeDetailsFragmentView.bringToFront();
        binding.RecipeDetailsFragmentView.setVisibility(View.VISIBLE);

        binding.closeRecipeDetailsButton.bringToFront();
        binding.closeRecipeDetailsButton.setVisibility(View.VISIBLE);
        RecipeDetailsFragment detailsFragment = binding.RecipeDetailsFragmentView.getFragment();
        detailsFragment.setDetails(recipes, name);
    }

    public void setDetailsFragmentInVisible() {
        binding.RecipeDetailsFragmentView.setVisibility(View.GONE);
        binding.closeRecipeDetailsButton.setVisibility(View.GONE);
    }

    public class RecipesRecyclerviewAdapter extends RecyclerView.Adapter<RecipesRecyclerviewAdapter.ViewHolder> {

        private final ArrayList<Recipe> list_items;
        private final LayoutInflater layoutInflater;

        public RecipesRecyclerviewAdapter(Context context, ArrayList<Recipe> list_items) {
            this.layoutInflater = LayoutInflater.from(context);
            this.list_items = list_items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = layoutInflater.inflate(R.layout.recycler_view_receipes_item_layout, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            String name = list_items.get(position).getName();
            viewHolder.name.setText(name);
        }

        @Override
        public int getItemCount() {
            return list_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            View itemView;

            ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                itemView.setOnClickListener(e -> setDetailsFragmentVisible(name.getText().toString()));
                name = itemView.findViewById(R.id.recyclerViewRecipeName);
            }
        }
    }
}