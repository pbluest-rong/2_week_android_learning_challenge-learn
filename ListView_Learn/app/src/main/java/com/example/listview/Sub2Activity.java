package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sub2Activity extends AppCompatActivity {
    TextView txtSubPL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        txtSubPL = findViewById(R.id.txt_subPL);
        Intent intent = getIntent();
        String namePL = intent.getStringExtra("name");
        txtSubPL.setText(namePL);
    }
}