package com.example.fridgeinspector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CategoryRecyclerviewAdapter extends RecyclerView.Adapter<CategoryRecyclerviewAdapter.ViewHolder> {

    private ArrayList<Item> list_items;
    private LayoutInflater layoutInflater;

    public CategoryRecyclerviewAdapter(Context context, ArrayList<Item> list_items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list_items = list_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String name = list_items.get(position).getName();
        viewHolder.name.setText(name);
        /*Category category = list_items.get(position).getCategory();
        viewHolder.category.setText(category.toString());*/
        Date date = list_items.get(position).getExpirationDate();
        int quantity = list_items.get(position).getQuantity();
        viewHolder.quantity.setText(quantity+"");
        DateFormat formatPattern = new SimpleDateFormat("yyyy-MM-dd");
        String formatted_date = formatPattern.format(date);

        viewHolder.expiration_date.setText(formatted_date);
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView category;
        TextView expiration_date;
        TextView quantity;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            expiration_date = itemView.findViewById(R.id.itemExpirationDate);
            quantity = itemView.findViewById(R.id.itemQuantity);
        }
    }
}
