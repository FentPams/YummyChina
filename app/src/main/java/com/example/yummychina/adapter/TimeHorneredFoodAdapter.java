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

import com.example.yummychina.view.DetailsActivity;
import com.example.yummychina.R;
import com.example.yummychina.model.TimeHorneredFood;

import java.util.List;


public class TimeHorneredFoodAdapter extends RecyclerView.Adapter<TimeHorneredFoodAdapter.timeHorneredFoodViewHolder> {

    Context context;
    List<TimeHorneredFood> timeHorneredFoodList;



    public TimeHorneredFoodAdapter(Context context, List<TimeHorneredFood> timeHorneredFoodList) {
        this.context = context;
        this.timeHorneredFoodList = timeHorneredFoodList;
    }

    @NonNull
    @Override
    public timeHorneredFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.time_hornered_food_row_item, parent, false);
        return new timeHorneredFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull timeHorneredFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(timeHorneredFoodList.get(position).getImageUrl());
        holder.name.setText(timeHorneredFoodList.get(position).getName());
        holder.price.setText(timeHorneredFoodList.get(position).getPrice());

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
        return timeHorneredFoodList.size();
    }


    public static final class timeHorneredFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name;

        public timeHorneredFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);

        }
    }

}