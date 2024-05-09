package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtInput;
    Button btnSave, btnClear;
    TextView txtShow;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtInput = findViewById(R.id.editTxt_input);
        btnSave = findViewById(R.id.btn_save);
        btnClear = findViewById(R.id.btn_clear);
        txtShow = findViewById(R.id.txt_show);

        SharedPreferences sharedPreferences = getSharedPreferences("myfile", MODE_PRIVATE);
        data = sharedPreferences.getString("data", "");
        txtShow.setText(data);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtTxtInput.getText().toString();
                if (!text.isEmpty()) {
                    data = text + "\n" + data;
                    txtShow.setText(data);
                    edtTxtInput.setText("");
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = "";
                txtShow.setText("");
                SharedPreferences sharedPreferences = getSharedPreferences("myfile", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("myfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data", data);
        editor.commit();
    }
}