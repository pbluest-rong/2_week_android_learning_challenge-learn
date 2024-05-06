package com.example.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    ImageView imgSubItem;
    TextView txtSubName;
    TextView txtSubPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        imgSubItem = findViewById(R.id.img_sub_item);
        txtSubName = findViewById(R.id.txt_sub_name);
        txtSubPrice = findViewById(R.id.txt_sub_price);

        Intent intent = getIntent();
        int img = intent.getIntExtra("image", 0);
        String name = intent.getStringExtra("name");
        double price = intent.getIntExtra("price", 0);
        imgSubItem.setImageResource(img);
        txtSubName.setText(name);
        txtSubPrice.setText((price + ""));
    }
}