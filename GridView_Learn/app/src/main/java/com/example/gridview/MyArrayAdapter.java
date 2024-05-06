package com.example.gridview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    int layoutId;
    ArrayList<Item> mylist;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Item> mylist) {
        super(context, layoutId,mylist);
        this.context = context;
        this.layoutId = layoutId;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        Item item = mylist.get(position);
        ImageView imgItem = convertView.findViewById(R.id.img_item);
        imgItem.setImageResource(item.getImage());
        TextView txtName = convertView.findViewById(R.id.txt_name);
        txtName.setText(item.getName());
        TextView txtPrice = convertView.findViewById(R.id.txt_price);
        txtPrice.setText(item.getPrice() + "");
        return convertView;
    }
}
