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
import com.example.bazaarwala.databinding.MinarShapeBinding;
import com.example.bazaarwala.model.MegaDiscount;

import java.util.List;

public class SponscorAdapter extends  RecyclerView.Adapter<SponscorAdapter.SponscorViewHolder> {

    Context context;
    List<MegaDiscount> megaDiscounts;
    TextView total;

    public SponscorAdapter(Context context, List<MegaDiscount> megaDiscounts) {
        this.context = context;
        this.megaDiscounts = megaDiscounts;
    }

    @NonNull
    @Override
    public SponscorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SponscorViewHolder(LayoutInflater.from(context).inflate(R.layout.minar_shape, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SponscorViewHolder holder, int position) {
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


    public class SponscorViewHolder extends RecyclerView.ViewHolder {
        MinarShapeBinding binding;
        public SponscorViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MinarShapeBinding.bind(itemView);
        }
    }
}
