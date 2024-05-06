package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListView2Activity extends AppCompatActivity {
    int image[] = {R.drawable.springboot, R.drawable.go, R.drawable.nodejs, R.drawable.reactnative};
    String name[] = {"Spring boot", "Go lang", "Node JS", "React Native"};

    ArrayList<ProgrammingLanguage> mylist;
    MyArrayAdapter myArrayAdapter;
    ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        lv2 = findViewById(R.id.lv2);
        mylist = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            mylist.add(new ProgrammingLanguage(image[i], name[i]));
        }
        myArrayAdapter = new MyArrayAdapter(ListView2Activity.this, R.layout.layout_item, mylist);
        lv2.setAdapter(myArrayAdapter);//đã hiển thị
//        Xử lý click
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListView2Activity.this, Sub2Activity.class);
                intent.putExtra("name", name[i]);
                startActivity(intent);
            }
        });
    }
}