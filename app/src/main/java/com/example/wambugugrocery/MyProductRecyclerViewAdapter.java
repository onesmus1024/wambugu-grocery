package com.example.wambugugrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public MyProductRecyclerViewAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_single_product,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(products.get(position).getImageUrl()).into(holder.iv_product_image);
        holder.tv_product_name.setText(products.get(position).getName());
        holder.tv_product_price.setText(String.valueOf(products.get(position).getPrice())+" ksh");
        holder.tv_product_description.setText(products.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product_image;
        TextView tv_product_name;
        TextView tv_product_price;
        TextView tv_product_description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_product_image =itemView.findViewById(R.id.iv_product_image);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            tv_product_description = itemView.findViewById(R.id.tv_product_description);
        }
    }
}
