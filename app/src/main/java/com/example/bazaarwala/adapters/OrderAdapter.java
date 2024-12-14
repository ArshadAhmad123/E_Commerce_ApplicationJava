package com.example.bazaarwala.adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bazaarwala.R;
import com.example.bazaarwala.activities.Order;
import com.example.bazaarwala.activities.User;
import com.example.bazaarwala.databinding.DesignBinding;
import com.example.bazaarwala.databinding.Minar1ShapeBinding;
import com.example.bazaarwala.databinding.OrderSampleBinding;
import com.example.bazaarwala.model.MegaDiscount;

import java.util.List;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.FeatureViewHolder> {

    Context context;
    List<Order> orders;


    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeatureViewHolder(LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        Order order = orders.get(position);

       // holder.binding.image.setImageResource(order.getId());
        Glide.with(context)
                .load(order.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(order.getProductName());
        holder.binding.price.setText("INR "+order.getPrice());
        holder.binding.quantity.setText(""+order.getQuantity());
        holder.binding.paymentId.setText(order.getPaymentId());
        holder.binding.status.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class FeatureViewHolder extends RecyclerView.ViewHolder {
        OrderSampleBinding binding;
        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OrderSampleBinding.bind(itemView);
        }
    }
}
