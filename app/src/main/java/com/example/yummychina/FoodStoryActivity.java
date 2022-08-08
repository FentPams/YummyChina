package com.example.yummychina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodStoryActivity extends AppCompatActivity {
    TextView textview;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_story);
        textview = findViewById(R.id.origin_des);
        textview.setMovementMethod(new ScrollingMovementMethod());


        back_btn = findViewById(R.id.back_pressed);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodStoryActivity.super.onBackPressed();
            }
        });
    }

}