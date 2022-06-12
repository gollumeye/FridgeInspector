package com.example.fridgeinspector;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CategoryRecyclerviewAdapter extends RecyclerView.Adapter<CategoryRecyclerviewAdapter.ViewHolder> {

    private ArrayList<Item> list_items;
    private LayoutInflater layoutInflater;
    private View view;

    public CategoryRecyclerviewAdapter(Context context, ArrayList<Item> list_items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list_items = list_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = layoutInflater.inflate(R.layout.recycler_view_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //viewHolder.itemView.

        /*Calendar calendar = new GregorianCalendar();
        java.util.Date date2 = new java.util.Date(calendar.getTimeInMillis() - 432000000);
        System.out.println(date2);
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        System.out.println(calendar.getTime());*/
        if(list_items.get(position).getExpirationDate() == new Date() || list_items.get(position).getExpirationDate().before(new Date())) {
            view.setBackgroundColor(Color.RED);
        } /* else if(today != null) {
            view.setBackgroundColor(Color.YELLOW);
        } */ else if(list_items.get(position).getExpirationDate().after(new Date())) {
            view.setBackgroundColor(Color.GREEN);
        }

        String name = list_items.get(position).getName();
        viewHolder.name.setText(name);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;;
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
