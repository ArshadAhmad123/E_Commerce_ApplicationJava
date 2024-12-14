package com.example.bazaarwala.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bazaarwala.R;

import com.example.bazaarwala.databinding.MegaDiscountBinding;
import com.example.bazaarwala.model.MegaDiscount;


import java.util.List;

public class MegaDiscountAdapter extends  RecyclerView.Adapter<MegaDiscountAdapter.MegaDiscountViewHolder> {

    Context context;
    List<MegaDiscount> megaDiscounts;
    TextView total;

    public MegaDiscountAdapter(Context context, List<MegaDiscount> megaDiscounts) {
        this.context = context;
        this.megaDiscounts = megaDiscounts;
    }

    @NonNull
    @Override
    public MegaDiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MegaDiscountViewHolder(LayoutInflater.from(context).inflate(R.layout.mega_discount, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MegaDiscountViewHolder holder, int position) {
         MegaDiscount megaDiscount = megaDiscounts.get(position);

        holder.binding.image.setImageResource(megaDiscount.getId());
        holder.binding.brandName.setText(megaDiscount.getBrandName());
        holder.binding.brandCompany.setText(megaDiscount.getBrandCompany());
        holder.binding.catagory.setText(megaDiscount.getCatagory());
        // Glide.with(context)
        //     .load(user.getImage())
        //   .into(holder.binding.image);


    }

    @Override
    public int getItemCount() {
        return megaDiscounts.size();
    }


    public class MegaDiscountViewHolder extends RecyclerView.ViewHolder {
     MegaDiscountBinding binding;
        public MegaDiscountViewHolder(@NonNull View itemView) {
            super(itemView);
         binding = MegaDiscountBinding.bind(itemView);
        }
    }
}

