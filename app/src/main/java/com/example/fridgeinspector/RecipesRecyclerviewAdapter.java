package com.example.fridgeinspector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.databinding.FragmentRecipesBinding;
import com.example.fridgeinspector.ui.recipes.RecipesFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecipesRecyclerviewAdapter extends RecyclerView.Adapter<RecipesRecyclerviewAdapter.ViewHolder> {

    private ArrayList<Receipe> list_items;
    private LayoutInflater layoutInflater;
    private View view;
    private FragmentRecipesBinding bindingRecipes;

    public RecipesRecyclerviewAdapter(Context context, ArrayList<Receipe> list_items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list_items = list_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = layoutInflater.inflate(R.layout.recycler_view_receipes_item_layout, viewGroup, false);
        view.setOnClickListener(e->{
            //TODO: open new Fragment with Recipe information
        });
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

        TextView name;;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recyclerViewRecipeName);
        }
    }
}
