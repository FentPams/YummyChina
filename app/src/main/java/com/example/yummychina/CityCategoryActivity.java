package com.example.yummychina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CityCategoryActivity extends AppCompatActivity {

    ImageView portfolio_btn;
    TextView shanghai_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_category);

        //Hooks
        portfolio_btn = findViewById(R.id.profile_pressed);
        shanghai_btn = findViewById(R.id.benbang_cuisine);
        portfolio_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CityCategoryActivity.this, UserProfileActivity.class);
            startActivity(intent);
            finish();
        });

        shanghai_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CityCategoryActivity.this, ShanghaiActivity.class);
            startActivity(intent);
            finish();
        });
    }
}