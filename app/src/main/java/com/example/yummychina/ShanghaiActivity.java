package com.example.yummychina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShanghaiActivity extends AppCompatActivity {

    ImageView back_btn, xlb_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanghai);

        //Hooks
        back_btn = findViewById(R.id.back_pressed);
        xlb_bt = findViewById(R.id.xlb_pic);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShanghaiActivity.super.onBackPressed();
            }
        });

        xlb_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShanghaiActivity.this, ViewPostsActivity.class);
                intent.putExtra("cuisine_name", "benbang");
                intent.putExtra("dish_name", "xiaolongbao");
                startActivity(intent);
            }
        });
    }
}