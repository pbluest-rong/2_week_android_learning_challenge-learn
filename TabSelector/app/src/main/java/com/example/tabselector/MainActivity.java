package com.example.tabselector;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editA, editB;
    Button btnSum;
    TabHost myTab;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(editA.getText().toString());
                int b = Integer.parseInt(editB.getText().toString());
                int c = a + b;
                myList.add(a + " + " + b + "=" + c);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addControl() {
        editA = findViewById(R.id.edit_a);
        editB = findViewById(R.id.edit_b);
        btnSum = findViewById(R.id.btn_sum);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myList);
        lv.setAdapter(myAdapter);

        myTab = findViewById(R.id.tabhost);
        myTab.setup();

        TabHost.TabSpec spec1, spec2;
        spec1 = myTab.newTabSpec("t1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("", getResources().getDrawable(R.drawable.calculator));
        myTab.addTab(spec1);

        spec2 = myTab.newTabSpec("t2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("", getResources().getDrawable(R.drawable.history));
        myTab.addTab(spec2);

    }
}