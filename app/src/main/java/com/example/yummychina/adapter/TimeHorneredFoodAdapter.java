package com.example.yummychina.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.adapter.holder.TimeHorneredFoodViewHolder;
import com.example.yummychina.view.DetailsActivity;
import com.example.yummychina.R;
import com.example.yummychina.model.TimeHorneredFood;

import java.util.List;

/**
 * This class bridge the TimeHorneredFoodViewHold and time_hornered_food_row_item layout
 */
public class TimeHorneredFoodAdapter extends RecyclerView.Adapter<TimeHorneredFoodViewHolder> {

    Context context;
    //transitted by ViewRestaurantsActivity
    List<TimeHorneredFood> TimeHorneredFoodList;

    public TimeHorneredFoodAdapter(Context context, List<TimeHorneredFood> timeHorneredFoodList) {
        this.context = context;
        this.TimeHorneredFoodList = timeHorneredFoodList;
    }

    @NonNull
    @Override
    public TimeHorneredFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.time_hornered_food_row_item, parent, false);
        return new TimeHorneredFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeHorneredFoodViewHolder holder, int position) {

        holder.getFoodImage().setImageResource(TimeHorneredFoodList.get(position).getImageUrl());
        holder.getName().setText(TimeHorneredFoodList.get(position).getName());
        holder.getPrice().setText(TimeHorneredFoodList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return TimeHorneredFoodList.size();
    }


}