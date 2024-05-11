package com.example.sqlite_1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.ims.RcsUceAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTxtId, editTxtName, editTxtScore;
    Button btnInsert, btnDelete, btnUpdate, btnSelect;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtId = findViewById(R.id.editTxt_id);
        editTxtName = findViewById(R.id.editTxt_name);
        editTxtScore = findViewById(R.id.editTxt_score);
        btnDelete = findViewById(R.id.btn_delete);
        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnSelect = findViewById(R.id.btn_select);
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(arrayAdapter);
        database = openOrCreateDatabase("dssv.db", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE student(id TEXT primary key, name TEXT, score INTEGER)";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table existed");
        }
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTxtId.getText().toString();
                String name = editTxtName.getText().toString();
                int score = Integer.parseInt(editTxtScore.getText().toString());
                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("name", name);
                values.put("score", score);
                String msg = "";
                if (database.insert("student", null, values) == -1) {
                    msg = "Fail to Insert Record";
                } else {
                    msg = "Insert record successfully";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                showData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTxtId.getText().toString();
                int n = database.delete("student", "id=?", new String[]{id});
                String msg;
                if (n == 0) {
                    msg = "No record to delete";
                } else {
                    msg = n + " records is delete";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                showData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTxtId.getText().toString();
                String name = editTxtName.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name", name);
                int n = database.update("student", values, "id=?", new String[]{});
                String msg = "";
                if (n == 0) {
                    msg = "No record to update";
                } else {
                    msg = n + "record is uppdated";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                showData();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
    }

    public void showData() {
        list.clear();
        Cursor cursor = database.query("student", null, null, null, null, null, null);
        cursor.moveToNext();
        String data = "";
        while (cursor.isAfterLast() == false) {
            data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getInt(2);
            cursor.moveToNext();
            list.add(data);
        }
        cursor.close();
        arrayAdapter.notifyDataSetChanged();
    }

}