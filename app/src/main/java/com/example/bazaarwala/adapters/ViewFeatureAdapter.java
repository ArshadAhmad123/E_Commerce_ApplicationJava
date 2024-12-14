package com.example.bazaarwala.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.MegaDiscountBinding;
import com.example.bazaarwala.databinding.SliderBinding;
import com.example.bazaarwala.databinding.ViewPagerBinding;
import com.example.bazaarwala.model.MegaDiscount;
import com.example.bazaarwala.model.slider;

import java.util.ArrayList;

public class ViewFeatureAdapter extends RecyclerView.Adapter<ViewFeatureAdapter.ViewFeatureViewHolder>{

    ViewPager2 viewPager2;
    ArrayList<MegaDiscount> megaDiscounts;

    public ViewFeatureAdapter(ViewPager2 viewPager2, ArrayList<MegaDiscount> megaDiscounts) {
        this.viewPager2 = viewPager2;
        this.megaDiscounts = megaDiscounts;
    }

    @NonNull
    @Override
    public ViewFeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewFeatureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFeatureViewHolder holder, int position) {
        MegaDiscount megaDiscount = megaDiscounts.get(position);
        holder.binding.image.setImageResource(megaDiscount.getId());
        if(position==megaDiscounts.size()-2){
        viewPager2.post(runnable);
        }
    }
    @Override
    public int getItemCount() {
        return megaDiscounts.size();
    }
    public class ViewFeatureViewHolder extends  RecyclerView.ViewHolder{
        ViewPagerBinding binding;
        public ViewFeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ViewPagerBinding.bind(itemView);
        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            megaDiscounts.addAll(megaDiscounts);
            notifyDataSetChanged();
        }
    };
}
