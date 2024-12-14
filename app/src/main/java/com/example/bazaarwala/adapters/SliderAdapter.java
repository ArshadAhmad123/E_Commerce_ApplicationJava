package com.example.bazaarwala.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.SliderBinding;
import com.example.bazaarwala.model.slider;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliederViewHolder>{

    ViewPager2 viewPager2;
    ArrayList<slider> sliders;

    public SliderAdapter(ViewPager2 viewPager2, ArrayList<slider> sliders) {
        this.viewPager2 = viewPager2;
        this.sliders = sliders;
    }

    @NonNull
    @Override
    public SliederViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliederViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliederViewHolder holder, int position) {

          slider slider = sliders.get(position);
          holder.binding.sliderImage.setImageResource(slider.getId());

    }

    @Override
    public int getItemCount() {

        return sliders.size();
    }

    public class SliederViewHolder extends  RecyclerView.ViewHolder{
         SliderBinding binding;
        public SliederViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SliderBinding.bind(itemView);
        }
    }
}
