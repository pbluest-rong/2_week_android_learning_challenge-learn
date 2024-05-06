package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //    Data -> Adapter -> ListView
    String[] phones = {"IPhone", "SamSung", "Oppo", "Nokia", "Honor", "BPhone"};
    ArrayAdapter<String> stringArrayAdapter;
    ListView lv;
    TextView txtSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSelect = findViewById(R.id.txt_select);
        lv = findViewById(R.id.lv2);
        //adapter cần 1 layout(SimpleLayout có sẵn, tự thiết kế), mảng dữ liệu
        stringArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, phones);
        lv.setAdapter(stringArrayAdapter);

//        Bắt event click item trong listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                i: vị trí item
                txtSelect.setText("Vị trí "+i +" - " + phones[i]);
            }
        });

        Button btnNextActivity = findViewById(R.id.btn_nextActivity2);
        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListView2Activity.class);
                startActivity(intent);
            }
        });
    }
}