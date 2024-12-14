package com.example.bazaarwala.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaarwala.activities.ProductDetailActivity;
import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.ItemProductBinding;
import com.example.bazaarwala.model.Product;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
      Product product = products.get(position);
       Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

       holder.binding.label.setText(product.getName());
       holder.binding.price.setText("INR "+product.getPrice());


        final long period=product.getStock();
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.animation);
   
      Timer timer = new Timer();
       timer.schedule(new TimerTask() {

          @Override
           public void run() {
                   holder.binding.text.startAnimation(animation);


            }
       },period,period);
        holder.binding.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              holder.binding.heart.setImageResource(R.drawable.like);
            }
        });


        //switch to product Detail Activity

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("name",product.getName());
                intent.putExtra("image",product.getImage());
                intent.putExtra("id",product.getId());
                intent.putExtra("price",product.getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
       ItemProductBinding binding;
        public ProductViewHolder(@NonNull View itemView) {

            super(itemView);
            binding = ItemProductBinding.bind(itemView);

        }
    }
}
