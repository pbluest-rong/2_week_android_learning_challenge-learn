package com.example.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int image[] = {R.drawable.aochongnang, R.drawable.aothunnam,
            R.drawable.quanshort, R.drawable.thatlung};
    String name[] = {"Áo chống nắng", "Áo thun nam", "Quần short", "Thắt lưng"};
    int price[] = {120000, 100000, 150000, 60000};
    GridView gv;
    ArrayList<Item> mylist;
    MyArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylist = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            mylist.add(new Item(name[i], image[i], price[i]));
        }
        myArrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_item, mylist);
        gv = findViewById(R.id.gv);
        gv.setAdapter(myArrayAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("image",image[i]);
                intent.putExtra("name",name[i]);
                intent.putExtra("price",price[i]);
                startActivity(intent);
            }
        });
    }

}