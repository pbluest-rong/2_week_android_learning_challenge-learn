package com.example.handmadestore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

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
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductPage extends AppCompatActivity {
    GridView gv;
    List<Product> list;
    MyArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        gv = findViewById(R.id.gv);
        new NetworkTask().execute();
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
            list = products;
            myArrayAdapter = new MyArrayAdapter(ProductPage.this, R.layout.layout_item, list);
            gv.setAdapter(myArrayAdapter);
        }
    }
}