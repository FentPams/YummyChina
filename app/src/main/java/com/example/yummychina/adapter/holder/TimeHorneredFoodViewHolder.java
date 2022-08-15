package com.example.yummychina.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.R;

/**
 * This class is the holder for single timehornered food
 *
 * The according layout:time_hornered_food_row_item.xml
 */
public class TimeHorneredFoodViewHolder extends RecyclerView.ViewHolder {

    private ImageView foodImage;
    private TextView price;
    private TextView name;

    public TimeHorneredFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        foodImage = itemView.findViewById(R.id.food_image);
        price = itemView.findViewById(R.id.price);
        name = itemView.findViewById(R.id.name);

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
}
