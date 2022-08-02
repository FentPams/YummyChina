package com.example.yummychina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class CityCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView shanghai_btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_category);

        //Hooks
        shanghai_btn = findViewById(R.id.benbang_cuisine);
        //  portfolio_btn.setOnClickListener(view -> {
        //     Intent intent = new Intent(CityCategoryActivity.this, UserProfileActivity.class);
        //     startActivity(intent);
        //     finish();
        //  });

        shanghai_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CityCategoryActivity.this, ShanghaiActivity.class);
            startActivity(intent);
            finish();
        });


        //hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //---tool bar
        setSupportActionBar(toolbar);

        // hide app name in bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //navgation bar

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}