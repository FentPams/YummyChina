package com.example.yummychina.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.yummychina.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

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
        navigationView.bringToFront();
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
        switch (item.getItemId()) {
            case R.id.nav_logout:
                logout();
                break;
        }
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}