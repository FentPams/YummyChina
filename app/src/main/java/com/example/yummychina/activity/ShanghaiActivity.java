package com.example.yummychina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;

/**
 * This class display the representative dishes, enter from CityCatergoryActivity
 * Links to dish activity(current only xiaolongbao is workable)
 *
 * Features:
 * 1)Displays the dishes(each dish is a button links to dish activity)
 * 2)Links to each dish interface(current only xiaolongbao workable)
 *
 * Expected Features:
 * 1)Complete the display of dishes
 * 2)Adds activity for each dish, make each dish a button
 * 3)Supports users to add dishes (after auditing by administrator, can be displayed and added new entry)
 *
 * The according layout:activity_shanghai.xml
 */
public class ShanghaiActivity extends AppCompatActivity {

    ImageView back_btn, xlb_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanghai);

        //hooks
        back_btn = findViewById(R.id.back_pressed);
        xlb_bt = findViewById(R.id.xlb_pic);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShanghaiActivity.super.onBackPressed();
            }
        });

        // current only xiaolongbao pic is a workable button
        // links to ViewPostsActivity interface
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