package com.example.handmadestore;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.handmadestore.bean.Image;
import com.example.handmadestore.bean.Product;
import com.squareup.picasso.Picasso;

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

    int[] productIdList;
    TextView txtPath;
    ImageView imgClickItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        gv = findViewById(R.id.gv);
        txtPath = findViewById(R.id.txt_path);
        imgClickItem = findViewById(R.id.img_clickItem);
        new NetworkTask().execute();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new NetworkGetImageTask(ProductPage.this, productIdList[position]).execute();
            }
        });
    }

    private class NetworkTask extends AsyncTask<Void, Void, List<Product>> {
        @Override
        protected List<Product> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://" + MainActivity.SERVER_IP + ":8080/gk/api-admin-product").build();
            List<Product> products = new ArrayList<>();
            try {
                Response resp = client.newCall(request).execute();
                String data = resp.body().string();
                JSONArray jsonArray = new JSONArray(data);
//
                productIdList = new int[jsonArray.length()];
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
                    productIdList[i] = id;
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

    private class NetworkGetImageTask extends AsyncTask<Void, Void, List<Image>> {
        private int productId;
        private Context context;

        public NetworkGetImageTask(Context context, int productId) {
            this.productId = productId;
            this.context = context;
        }

        @Override
        protected List<Image> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://" + MainActivity.SERVER_IP + ":8080/gk/api-admin-product?action=getImage&productId=" + productId).build();
            List<Image> images = new ArrayList<>();
            try {
                Response resp = client.newCall(request).execute();
                String data = resp.body().string();
                JSONArray jsonArray = new JSONArray(data);
                try {
                    resp = client.newCall(request).execute();
                    data = resp.body().string();
                    jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String path = jsonObject.getString("path");
                        images.add(new Image(id, name, path, productId));
                    }
                } catch (Exception e) {
                    Log.e("get image error", e.toString());
                }
            } catch (IOException | JSONException e) {
                Log.e("error", e.toString());
            }
            return images;
        }

        @Override
        protected void onPostExecute(List<Image> images) {
            txtPath.setText(productId + " - " + images.size() + " images");
            Glide.with(context).load("http://" + MainActivity.SERVER_IP + ":8080/gk/" + images.get(0).getPath()).into(imgClickItem);
        }
    }
}