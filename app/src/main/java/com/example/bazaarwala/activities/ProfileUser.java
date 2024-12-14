package com.example.bazaarwala.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.AppDatabase;
import com.example.bazaarwala.activities.Order;
import com.example.bazaarwala.activities.UserDao;
import com.example.bazaarwala.adapters.CartAdapter;
import com.example.bazaarwala.adapters.OrderAdapter;
import com.example.bazaarwala.databinding.ActivityProfileUserBinding;

import java.util.List;

public class ProfileUser extends AppCompatActivity {
    ActivityProfileUserBinding binding;
    OrderAdapter adapter;
    List<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listForOrder();
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileUser.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void listForOrder() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        orders = userDao.getAllOrder();
        getRoomData();
    }

    private void getRoomData() {
        adapter  = new OrderAdapter(this,orders);
        binding.orderRecyle.setLayoutManager(new LinearLayoutManager(this));
        binding.orderRecyle.setAdapter(adapter);
    }
}