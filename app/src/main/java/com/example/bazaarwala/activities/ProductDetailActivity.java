package com.example.bazaarwala.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding  = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Glide.with(getApplicationContext())
                        .load(getIntent().getStringExtra("image"))
                                .into(binding.productImage);
        binding.productDetail.setText(getIntent().getStringExtra("name"));

        //cut mark text
        String text = ""+getIntent().getDoubleExtra("price",0);
        SpannableString spannableString =  new SpannableString(text);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.price.setText(spannableString);
        //cut mark end
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = getIntent().getStringExtra("image");
        binding.addToCartBtn.setOnClickListener(v -> {
           AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                   AppDatabase.class, "room_db").allowMainThreadQueries().build();
           UserDao userDao = db.userDao();
         Integer uid = getIntent().getIntExtra("id",0);
         Boolean check  = userDao.is_exixt(uid);

         if(check==false) {
              userDao.insertRecord(new User(uid,binding.productDetail.getText().toString(),getIntent().getStringExtra("image"),(int)getIntent().getDoubleExtra("price",0),1));
              Toast.makeText(this,"Item added",Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(this,"Already exist",Toast.LENGTH_SHORT).show();
          }

    });
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
}