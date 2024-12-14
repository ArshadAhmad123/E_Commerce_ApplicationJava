package com.example.bazaarwala.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.MainActivity;
import com.example.bazaarwala.activities.NewFashion;
import com.example.bazaarwala.activities.SearchActivity;
import com.example.bazaarwala.databinding.MegaDiscountBinding;
import com.example.bazaarwala.databinding.MinarShapeBinding;
import com.example.bazaarwala.databinding.RoundShapeSampleBinding;
import com.example.bazaarwala.model.MegaDiscount;

import java.util.List;

public class RoundShapeAdapter extends  RecyclerView.Adapter<RoundShapeAdapter.SponscorViewHolder> {

    Context context;
    List<MegaDiscount> megaDiscounts;
    TextView total;

    public RoundShapeAdapter(Context context, List<MegaDiscount> megaDiscounts) {
        this.context = context;
        this.megaDiscounts = megaDiscounts;
    }

    @NonNull
    @Override
    public SponscorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SponscorViewHolder(LayoutInflater.from(context).inflate(R.layout.round_shape_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SponscorViewHolder holder, int position) {
        MegaDiscount megaDiscount = megaDiscounts.get(position);

        holder.binding.image.setImageResource(megaDiscount.getId());
        holder.binding.brandName.setText(megaDiscount.getBrandName());
        holder.binding.brandCompany.setText(megaDiscount.getBrandCompany());
        holder.binding.catagory.setText(megaDiscount.getCatagory());
        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, NewFashion.class);
                intent.putExtra("query","furniture");
                context.startActivity(intent);
            }
        });

        // Glide.with(context)
        //     .load(user.getImage())
        //   .into(holder.binding.image);


    }

    @Override
    public int getItemCount() {
        return megaDiscounts.size();
    }


    public class SponscorViewHolder extends RecyclerView.ViewHolder {
        RoundShapeSampleBinding binding;
        public SponscorViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RoundShapeSampleBinding.bind(itemView);
        }
    }
}
