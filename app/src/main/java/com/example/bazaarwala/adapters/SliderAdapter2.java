package com.example.bazaarwala.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.MainActivity;
import com.example.bazaarwala.activities.NewFashion;
import com.example.bazaarwala.databinding.Slider2Binding;
import com.example.bazaarwala.databinding.SliderBinding;
import com.example.bazaarwala.model.Slider2;
import com.example.bazaarwala.model.slider;

import java.util.ArrayList;

public class SliderAdapter2 extends RecyclerView.Adapter<SliderAdapter2.Slider2ViewHolder> {
    ViewPager2 viewPager3;
    ArrayList<Slider2> slider;
    Context context;
    public SliderAdapter2(Context context,ViewPager2 viewPager3, ArrayList<Slider2> slider) {
        this.viewPager3 = viewPager3;
        this.slider = slider;
        this.context = context;
    }

    @NonNull
    @Override
    public Slider2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Slider2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Slider2ViewHolder holder, int position) {
        Slider2 sliders = slider.get(position);
        holder.binding.sliderImage1.setImageResource(sliders.getId());
        holder.binding.sliderImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.startActivity(new Intent(context, NewFashion.class));

            }
        });
        if(position==slider.size()-2){
          viewPager3.post(runnable);
        }

    }


    @Override
    public int getItemCount() {
        return slider.size();
    }

    public class Slider2ViewHolder extends RecyclerView.ViewHolder{
        Slider2Binding binding;
      public Slider2ViewHolder(@NonNull View itemView) {
          super(itemView);
          binding=Slider2Binding.bind(itemView);
      }
  }
  private Runnable runnable = new Runnable() {
      @Override
      public void run() {
          slider.addAll(slider);
          notifyDataSetChanged();
      }
  };
}
