package com.example.listview;

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

public class MyArrayAdapter extends ArrayAdapter<ProgrammingLanguage> {
    Activity context;
    int layoutId;
    ArrayList<ProgrammingLanguage> mylist;

    //constructor để activity goi đến
    public MyArrayAdapter(Activity context, int layoutId, ArrayList<ProgrammingLanguage> mylist) {
        super(context, layoutId, mylist);
        this.context = context;
        this.layoutId = layoutId;
        this.mylist = mylist;
    }
//    getView để sắp xếp dữ liệu
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        tạo đế chứa Layout
        LayoutInflater inflater = context.getLayoutInflater();
//        đặt layoutId lên inflater để tạo thành 1 đối tượng riêng
        convertView = inflater.inflate(layoutId, null);
//        lấy dl trong mảng
        ProgrammingLanguage pl = mylist.get(position);
//        khai báo, tham chiếu ID và hiển thị ảnh lên ImageView
        ImageView imgPL = convertView.findViewById(R.id.img_phone);
        imgPL.setImageResource(pl.getImage());
//        Khai báo, tham chiếu Id và hiển thị lên text view
        TextView txtPL = convertView.findViewById(R.id.txt_phone);
        txtPL.setText(pl.getName());
        return convertView;
    }
}
