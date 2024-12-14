package com.example.bazaarwala.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bazaarwala.R;
import com.example.bazaarwala.adapters.CartAdapter;
import com.example.bazaarwala.databinding.ActivityCartBinding;
import com.example.bazaarwala.model.Product;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = MainActivity.class.getSimpleName();
             ActivityCartBinding binding;
             CartAdapter adapter;
             List<User> users;

             Button continueBtn;
             TextView total;
           int sum=0,i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = ActivityCartBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      total = findViewById(R.id.subTotal);

      continueBtn = findViewById(R.id.continueBtn);
      continueBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startPayment();
          }
      });
      getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      listForUpdation();
    }

    private void listForUpdation() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        users = userDao.getAllUsers();
        getRoomData();
    }

    public void startPayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_wC9PNtlMpN73lM");

        checkout.setImage(R.drawable.ic_launcher_background);

        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();
            options.put("name", "Fashion Store");
            options.put("description", "Shoping on your best offer!");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(sum*100));//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","6396381868");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Payment Success",Toast.LENGTH_SHORT).show();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        for (i=0;i<users.size();i++) {
            User user = users.get(i);
            userDao.insertRecord( new Order(user.getProductName(),user.getImage(),user.getPrice(),user.getQuantity(),s,"Success"));
        }

        for (i=0;i<users.size();i++) {
            userDao.delete(users.get(i));
        }
        listForUpdation();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    private void getRoomData() {
        adapter  = new CartAdapter(this,users,total);
        binding.cartList.setLayoutManager(new LinearLayoutManager(this));
        binding.cartList.setAdapter(adapter);
        for (i=0;i<users.size();i++){
            sum = sum+(users.get(i).getPrice()*users.get(i).getQuantity());
            total.setText("INR "+sum);

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
       finish();
        return super.onSupportNavigateUp();
    }
}