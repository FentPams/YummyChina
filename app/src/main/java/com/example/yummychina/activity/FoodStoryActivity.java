package com.example.yummychina.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;

/**
 * This class displays the food origin story(enter from ViewPostsActivity by clicking "xlb_icon button")
 *
 * Expected features(not completed):
 * 1) Adds bookmark feature
 * 2) Supports updated story
 *
 * The according layout: activity_food_story.xml
 */
public class FoodStoryActivity extends AppCompatActivity {
    TextView textview;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_story);
        //set scrollview
        textview = findViewById(R.id.origin_des);
        textview.setMovementMethod(new ScrollingMovementMethod());

        //hook
        back_btn = findViewById(R.id.back_pressed);

        //back to view post interface
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodStoryActivity.super.onBackPressed();
            }
        });
    }

}