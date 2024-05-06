package com.example.a_2_bundle_learn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB;
    Button btRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Mapping id
        edtA = findViewById(R.id.editTextNumber_a);
        edtB = findViewById(R.id.editTextNumber_b);
        btRes = findViewById(R.id.bt_result);
//        Click Event
        btRes.setOnClickListener((oc) -> {
//            Khai báo intent
            Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
//            Lấy dữ liệu
            int a = Integer.parseInt(edtA.getText().toString());
            int b = Integer.parseInt(edtB.getText().toString());
//            Đóng gói dữ liệu vào Bundle
            Bundle mybBundle = new Bundle();
//            Đưa dữ liệu ho Bundle
            mybBundle.putInt("a",a);
            mybBundle.putInt("b",b);
//           Đưa bundle vào intent
            myIntent.putExtra("mypackage", mybBundle);
//            Start
            startActivity(myIntent);
        });
    }
}
