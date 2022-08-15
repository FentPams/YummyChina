package com.example.yummychina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.R;
import com.example.yummychina.adapter.holder.PopularFoodViewHolder;
import com.example.yummychina.model.PopularFood;

import java.util.List;

// This class bridge the model:PopularFood and view: popular_food_row_item layout
public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodViewHolder> {

    Context context;
    //Transited by ViewRestaurantActivity
    List<PopularFood> popularFoodList;
    PopularFoodViewHolder PopularFoodViewHolder;


    public PopularFoodAdapter(Context context, List<PopularFood> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public PopularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_food_row_item, parent, false);
        return new PopularFoodViewHolder(view);
    }

    // Sets data into the view:single item layout
    @Override
    public void onBindViewHolder(PopularFoodViewHolder holder, int position) {

        holder.getFoodImage().setImageResource(popularFoodList.get(position).getImageUrl());
        holder.getName().setText(popularFoodList.get(position).getName());
        holder.getPrice().setText(popularFoodList.get(position).getPrice());
        holder.getRating().setText(popularFoodList.get(position).getRating());
        holder.getRestaurantName().setText(popularFoodList.get(position).getRestaurantname());

    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


}