package com.example.bazaarwala.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bazaarwala.R;
import com.example.bazaarwala.adapters.CatagoryProductAdapter;

import com.example.bazaarwala.databinding.ActivityNewFashionBinding;
import com.example.bazaarwala.model.Product;
import com.example.bazaarwala.model.Url;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewFashion extends AppCompatActivity {
    ArrayList<Product> products;
    CatagoryProductAdapter catagoryProductAdapter;
    ActivityNewFashionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNewFashionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fashion");
        search();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag){
            startActivity(new Intent(this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void search() {
        products = new ArrayList<>();
        String query = getIntent().getStringExtra("query");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.Search_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray("response_obj");
                    Product product;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        product = new Product(
                                jsonObject1.getString("name"),
                                Url.Main_Url+"images/" + jsonObject1.getString("image"),
                                jsonObject1.getString("status"),
                                jsonObject1.getDouble("price"),
                                jsonObject1.getDouble("discount"),
                                jsonObject1.getInt("stock"),
                                jsonObject1.getInt("id")
                        );
                        products.add(product);

                    }
                    catagoryProductAdapter = new CatagoryProductAdapter(getApplicationContext(), products);
                    GridLayoutManager layoutManager = new GridLayoutManager(NewFashion.this, 2);
                    binding.recyNewFashion.setLayoutManager(layoutManager);
                    binding.recyNewFashion.setAdapter(catagoryProductAdapter);


                } catch (Exception e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("search", query);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}