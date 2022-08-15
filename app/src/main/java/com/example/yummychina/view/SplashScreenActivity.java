package com.example.yummychina.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;

/**
 * This is the splash screen class that appear at first time when users open the app
 * Links to the CityCategoryActivity
 *
 * Features:
 * Cool animation
 *
 * The according layout:activity_splash_screen.xml
 */
public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 4000;
    // variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //hooks
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        //set animations
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Call next screen
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);

                //Attach all the elements those you want to animate in design
                Pair[]  pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(slogan, "logo_text");

                //wrap the call in API level 21 or higher
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
                else{
                    startActivity(intent);
                }

            }
        }, SPLASH_SCREEN);

    }
}