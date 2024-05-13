package com.example.handmadestore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.handmadestore.bean.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String[] list;
    ArrayAdapter<String> adapter;
    ListView lv;
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        txt = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);
        new NetworkTask().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedProduct = list[i];
                txt.setText(selectedProduct);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductPage.class);
                startActivity(intent);
            }
        });
    }

    private class NetworkTask extends AsyncTask<Void, Void, List<Product>> {
        @Override
        protected List<Product> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.5.110:8080/gk/api-admin-product").build();
            List<Product> products = new ArrayList<>();
            try {
                Response resp = client.newCall(request).execute();
                String data = resp.body().string();
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String description = jsonObject.getString("description");
                    double costPrice = jsonObject.getDouble("costPrice");
                    double sellingPrice = jsonObject.getDouble("sellingPrice");
                    int quantity = jsonObject.getInt("quantity");
                    int soldout = jsonObject.getInt("soldout");
                    int categoryId = jsonObject.getInt("categoryId");
                    int discountId = jsonObject.getInt("discountId");
                    int isSale = jsonObject.getInt("isSale");
                    products.add(new Product(name, description, costPrice, sellingPrice, quantity, soldout, categoryId, isSale));
                }
            } catch (IOException | JSONException e) {
                Log.e("error", e.toString());
            }
            return products;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            list = new String[products.size()];
            int index = 0;
            for (Product p : products) {
                list[index] = p.getName();
                index++;
            }
            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);
        }
    }
}