package com.example.yummychina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShanghaiActivity extends AppCompatActivity {

    ImageView back_btn, xy_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanghai);

        //Hooks
        back_btn = findViewById(R.id.back_pressed);
        xy_btn = findViewById(R.id.shxy_pic);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShanghaiActivity.super.onBackPressed();
            }
        });

        xy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShanghaiActivity.this, ViewPostsActivity.class);
                intent.putExtra("cuisine_name", "benbang");
                intent.putExtra("dish_name", "smoke_fish");
                startActivity(intent);
            }
        });
    }
}