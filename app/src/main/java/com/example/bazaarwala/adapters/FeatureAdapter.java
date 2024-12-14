package com.example.bazaarwala.adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaarwala.R;
import com.example.bazaarwala.databinding.Minar1ShapeBinding;
import com.example.bazaarwala.model.MegaDiscount;

import java.util.List;

public class FeatureAdapter extends  RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    Context context;
    List<MegaDiscount> megaDiscounts;
    TextView total;

    public FeatureAdapter(Context context, List<MegaDiscount> megaDiscounts) {
        this.context = context;
        this.megaDiscounts = megaDiscounts;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeatureViewHolder(LayoutInflater.from(context).inflate(R.layout.minar1_shape, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        MegaDiscount megaDiscount = megaDiscounts.get(position);

        holder.binding.image.setImageResource(megaDiscount.getId());
        holder.binding.brandName.setText(megaDiscount.getBrandName());
        holder.binding.brandCompany.setText(megaDiscount.getBrandCompany());
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/beach.otf" );
        holder.binding.OFF.setTypeface(typeface);

        // Glide.with(context)
        //     .load(user.getImage())
        //   .into(holder.binding.image);


    }

    @Override
    public int getItemCount() {
        return megaDiscounts.size();
    }


    public class FeatureViewHolder extends RecyclerView.ViewHolder {
        Minar1ShapeBinding binding;
        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = Minar1ShapeBinding.bind(itemView);
        }
    }
}
