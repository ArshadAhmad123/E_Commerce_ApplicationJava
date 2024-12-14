package com.example.bazaarwala.adapters;

import static android.R.color.transparent;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.bazaarwala.R;

import com.example.bazaarwala.activities.AppDatabase;
import com.example.bazaarwala.activities.MainActivity;
import com.example.bazaarwala.activities.QuantityUpdate;
import com.example.bazaarwala.activities.User;
import com.example.bazaarwala.activities.UserDao;
import com.example.bazaarwala.databinding.ItemCartBinding;
import com.example.bazaarwala.databinding.QuantityDilogBinding;
import com.example.bazaarwala.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.CartViewHolder>{

     Context context;
     List<User> users;
     TextView total;

    public CartAdapter(Context context, List<User> users, TextView total) {
        this.context = context;
        this.users = users;
        this.total = total;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
      // Product product = products.get(position);
       User user = users.get(position);
       holder.binding.name.setText(user.getProductName());
       holder.binding.price.setText("INR "+user.getPrice());
       holder.binding.quantity.setText(""+user.getQuantity());

        Glide.with(context)
                .load(user.getImage())
                      .into(holder.binding.image);

          holder.binding.delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  AppDatabase db = Room.databaseBuilder(holder.binding.name.getContext(),
                          AppDatabase.class, "room_db").allowMainThreadQueries().build();
                  UserDao userDao = db.userDao();
                  //this is to delete the data from the room database
                  userDao.deleteById(user.uid);
                  //this is to delete the data from the list
                  users.remove(user);
                  //update the fresh list of arraylist to recyler view
                  notifyDataSetChanged();
                  updatePrice();
              }

          });
          holder.binding.plus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int qty = user.getQuantity();
                  if(qty==10){
                      Toast.makeText(context.getApplicationContext(),"This seller has a limit of 10 per customer.",Toast.LENGTH_SHORT).show();
                      return;
                  }
                  qty++;
               users.get(position).setQuantity(qty);
               notifyDataSetChanged();
               updatePrice();
              }
          });
          holder.binding.minus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 int qty = user.getQuantity();
                  if(qty==1){
                      Toast.makeText(context.getApplicationContext(),"Zero item can't be choose.",Toast.LENGTH_SHORT).show();
                      return;
                  }
                 qty--;
                 users.get(position).setQuantity(qty);
                 notifyDataSetChanged();
                 updatePrice();
              }
          });
           holder.binding.quantity.setOnClickListener(v -> {
           QuantityDilogBinding quantityDilogBinding = QuantityDilogBinding.inflate(LayoutInflater.from(context));
           AlertDialog dialog = new AlertDialog.Builder(context)
                   .setView(quantityDilogBinding.getRoot())
                   .create();
           dialog.show();

       });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{
          ItemCartBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
          binding = ItemCartBinding.bind(itemView);
        }
    }
    private void updatePrice() {
        int sum=0,i;

        for (i=0;i<users.size();i++){
            sum = sum+(users.get(i).getPrice()*users.get(i).getQuantity());
            total.setText("INR "+sum);
        }
    }
}
