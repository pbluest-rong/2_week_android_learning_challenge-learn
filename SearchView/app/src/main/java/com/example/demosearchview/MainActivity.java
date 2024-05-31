package com.example.demosearchview;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv_user;
    List<User> list;
    UserAdapter userAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rcv_user = findViewById(R.id.rcv_user);
        searchView = findViewById(R.id.search_user);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterText(newText);
                return true;
            }
        });

        list = getListUser();
        userAdapter = new UserAdapter(this.list);
        rcv_user.setAdapter(userAdapter);
    }

    private void filterText(String newText){
        List<User> filterList = new ArrayList<>();
        for(User u : list){
            if(u.getName().toLowerCase().contains(newText.toLowerCase())){
                filterList.add(u);
            }
        }
       if(filterList.isEmpty()){
           Toast.makeText(this, "Không có data", Toast.LENGTH_LONG).show();
       }else{
           userAdapter.setFilterList(filterList);
       }
    }

    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User("Le Ba Phung"));
        list.add(new User("Trong"));
        list.add(new User("Pblues"));
        list.add(new User("Nguyen Van A"));
        list.add(new User("Phan Thi B"));
        list.add(new User("Hello World"));
        return list;
    }
}