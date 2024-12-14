package com.example.bazaarwala.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.ProductDetailActivity;
import com.example.bazaarwala.databinding.CatagoryProductSampleBinding;
import com.example.bazaarwala.databinding.ItemProductBinding;
import com.example.bazaarwala.model.Product;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CatagoryProductAdapter extends  RecyclerView.Adapter<CatagoryProductAdapter.CartProductViewHolder>{
        Context context;
        ArrayList<Product> products;

public CatagoryProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        }


@NonNull
@Override
public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CartProductViewHolder(LayoutInflater.from(context).inflate(R.layout.catagory_product_sample,parent,false));
        }

@Override
public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
        .load(product.getImage())
        .into(holder.binding.image);
        holder.binding.label.setText(product.getName());
        holder.binding.price.setText("INR "+product.getPrice());

        //switch to product Detail Activity

        holder.binding.image.setOnClickListener(new View.OnClickListener() {
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

public class CartProductViewHolder extends RecyclerView.ViewHolder{
    CatagoryProductSampleBinding binding;
    public CartProductViewHolder(@NonNull View itemView) {

        super(itemView);
        binding = CatagoryProductSampleBinding.bind(itemView);

    }
}
}
