package com.example.a_2_bundle_learn;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    TextView tv_result;
    Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
//        Mapping id
        tv_result = findViewById(R.id.t_result);
        bt_back = findViewById(R.id.bt_back);
//        Nhận Intent
        Intent myinIntent = getIntent();
//        Lấy bundle
        Bundle myBundle = myinIntent.getBundleExtra("mypackage");
//        Lấy data
        int a = myBundle.getInt("a");
        int b = myBundle.getInt("b");
//        Giai pt
        if(a==0 && b==0){
            tv_result.setText("PT Vô Số Nghiệm");
        }else if(a==0 && b!=0){
            tv_result.setText("PT Vô Nghiệm");
        }else{
            tv_result.setText("Nghiệm PT: "+ (-1) * b/a);
        }
    }
}
