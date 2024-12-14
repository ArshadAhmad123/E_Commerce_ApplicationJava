package com.example.bazaarwala.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.ActivityLoginBinding;
import com.example.bazaarwala.databinding.ActivitySignUpBinding;
import com.example.bazaarwala.model.Url;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.existUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUserData();
            }
        });

    }

    private void signUserData() {

            final String user_name = binding.userName.getText().toString().trim();
            final String name = binding.name.getText().toString().trim();
            final String password = binding.password.getText().toString().trim();
            final String rePassword = binding.rePassword.getText().toString().trim();
            if(!rePassword.equals(password)){
             Toast.makeText(getApplicationContext(),"please conferm password!..",Toast.LENGTH_SHORT).show();
             return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.SignUp_Url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    binding.userName.setText("");
                    binding.name.setText("");
                    binding.password.setText("");
                    binding.rePassword.setText("");
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

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
                    map.put("user_name", user_name);
                    map.put("name", name);
                    map.put("password", password);


                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }

}