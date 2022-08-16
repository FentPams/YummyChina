package com.example.yummychina.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;

/**
 * This class is to display details of restaurants(Not completed)
 * Reason for design this interface:
 * I hope the people who love the food can support the restaurant, because I believe when we support
 * the restaurant, we are also protecting our food and food culture.
 *
 * Expected features(not completed):
 * 1)Supports users to view the restaurants story/description(read data from database)
 * 2)Supports users to locate the address of the restaurants
 * 3)Supports the bookmark feature
 *
 *
 * The according layout: activity_details.xml
 */
public class DetailsActivity extends AppCompatActivity {

    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // hook
        back_btn = findViewById(R.id.back_pressed);

        // back to last interface
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsActivity.super.onBackPressed();
            }
        });
    }
}

