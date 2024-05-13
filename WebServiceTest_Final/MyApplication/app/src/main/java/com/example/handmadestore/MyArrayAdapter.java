package com.example.handmadestore;

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

import com.squareup.picasso.Picasso;
import com.example.handmadestore.bean.Product;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Product> {
    Activity context;
    int layoutId;
    List<Product> mylist;

    public MyArrayAdapter(Activity context, int layoutId, List<Product> mylist) {
        super(context, layoutId, mylist);
        this.context = context;
        this.layoutId = layoutId;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        Product item = mylist.get(position);
//        ImageView imgItem = convertView.findViewById(R.id.img_item);
//        imgItem.setImageResource(item.getQuantity());
        ImageView imgItem = convertView.findViewById(R.id.img_item);
        Picasso.with(this.getContext()).load("https://phuongnamvina.com/img_data/images/nhung-y-tuong-lam-do-handmade.jpg").into(imgItem);
        TextView txtName = convertView.findViewById(R.id.txt_name);
        txtName.setText(item.getName());
        TextView txtPrice = convertView.findViewById(R.id.txt_price);
        txtPrice.setText(item.getSellingPrice() + "");
        return convertView;
    }
}
