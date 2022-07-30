package com.example.fridgeinspector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.data.DataHandlingCategory;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CategoryRecyclerviewAdapter extends RecyclerView.Adapter<CategoryRecyclerviewAdapter.ViewHolder> {

    public ArrayList<Item> list_items;
    private final LayoutInflater layoutInflater;
    private View view;
    private final DataHandlingCategory dhc;

    public CategoryRecyclerviewAdapter(Context context, ArrayList<Item> list_items) {
        dhc = new DataHandlingCategory(context);
        this.layoutInflater = LayoutInflater.from(context);
        this.list_items = list_items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        view = layoutInflater.inflate(R.layout.recycler_view_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date tomorrowDate = c.getTime();

        if (Objects.equals(list_items.get(position).getExpirationDate(), new Date()) || list_items.get(position).getExpirationDate().before(new Date())) {
            TextView expDateText = view.findViewById(R.id.itemExpirationDate);
            expDateText.setTextColor(Color.RED);
        } else if (list_items.get(position).getExpirationDate().getDay() == tomorrowDate.getDay()) {
            TextView expDateText = view.findViewById(R.id.itemExpirationDate);
            expDateText.setTextColor(Color.parseColor("#FFA500"));
        } else if (list_items.get(position).getExpirationDate().after(new Date())) {
            TextView expDateText = view.findViewById(R.id.itemExpirationDate);
            expDateText.setTextColor(Color.parseColor("#48914B"));
        }

        String name = list_items.get(position).getName();
        viewHolder.name.setText(name);
        Date date = list_items.get(position).getExpirationDate();
        int quantity = list_items.get(position).getQuantity();
        viewHolder.quantity.setText(quantity + "");
        @SuppressLint("SimpleDateFormat") DateFormat formatPattern = new SimpleDateFormat("yyyy-MM-dd");
        String formatted_date = formatPattern.format(date);
        viewHolder.expiration_date.setText(formatted_date);

        ImageView imageView = view.findViewById(R.id.imageView2);
        imageView.setOnClickListener(view -> {
            dhc.removeFoodItem(name);
            Snackbar snackbar = Snackbar.make(view, "Item removed! Close window to refresh!", Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
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
