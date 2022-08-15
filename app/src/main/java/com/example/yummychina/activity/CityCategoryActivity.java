package com.example.yummychina.activity;

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

/**
 * This activity is the main interface of the app which displays the city background with cuisine button
 *
 * Features:
 * 1)Displays lists of cities(backgrounds)
 * 2)Button of cuisine to jump to the cuisine interface(in this case, only Benbang Cuisine is clickable)
 * And it jumps to the ShanghaiActivity class.
 * Other cuisine button needs further implementation.
 * 3)Display profile navigation board. (logout button is the current workable button)
 *
 * The according layout file: activity_city_category.xml
 */

public class CityCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Notice: BENBANG CUISINE now is the only workable button
    TextView shanghai_btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_category);

        //hooks
        shanghai_btn = findViewById(R.id.benbang_cuisine);

        //Click BENBANG CUISINE button will leads to ShanghaiActivity class.
        shanghai_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CityCategoryActivity.this, ShanghaiActivity.class);
            startActivity(intent);
            finish();
        });


        //hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //tool bar
        setSupportActionBar(toolbar);

        //hide app name in bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //navigation bar
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

    // logout button action in navigation bar
    // Notice: logout button is the current only workable button
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