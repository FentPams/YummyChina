package com.example.yummychina.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.R;

public class PopularFoodViewHolder extends RecyclerView.ViewHolder {

    private ImageView foodImage;
    private TextView price;
    private TextView name;
    private TextView rating;
    private TextView restaurantName;

    public PopularFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        foodImage = itemView.findViewById(R.id.food_image);
        price = itemView.findViewById(R.id.price);
        name = itemView.findViewById(R.id.name);
        rating = itemView.findViewById(R.id.rating);
        restaurantName = itemView.findViewById(R.id.restorant_name);
    }


    public ImageView getFoodImage() {
        return foodImage;
    }

    public TextView getPrice() {
        return price;
    }

    public TextView getName() {
        return name;
    }

    public TextView getRating() {
        return rating;
    }

    public TextView getRestaurantName() {
        return restaurantName;
    }
}
