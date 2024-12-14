package com.example.bazaarwala.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.NewFashion;
import com.example.bazaarwala.databinding.BeautyCatagorySampleBinding;
import com.example.bazaarwala.databinding.ItemCatagoriesBinding;
import com.example.bazaarwala.model.Catagory;

import java.util.ArrayList;

public class BeautyAdapter extends  RecyclerView.Adapter<BeautyAdapter.CatagoryViewHolder> {

    Context context;
    ArrayList<Catagory> catagories;

    public BeautyAdapter(Context context, ArrayList<Catagory> catagories) {
        this.context = context;
        this.catagories = catagories;
    }

    @NonNull
    @Override
    public CatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatagoryViewHolder(LayoutInflater.from(context).inflate(R.layout.beauty_catagory_sample,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryViewHolder holder, int position) {
        Catagory catagory  = catagories.get(position);
        holder.binding.label.setText(catagory.getName());
       holder.binding.image.setImageResource(catagory.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, NewFashion.class);
                intent.putExtra("query","saree");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catagories.size();
    }

    public class CatagoryViewHolder extends RecyclerView.ViewHolder{
        BeautyCatagorySampleBinding binding;
        public CatagoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BeautyCatagorySampleBinding.bind(itemView);
        }
    }
}
