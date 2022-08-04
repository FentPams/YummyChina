package com.example.yummychina;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewPostsActivity extends AppCompatActivity {

    ImageView back_btn, create_post_btn, recipe_btn;

    private ListView postsLitView;
    private List<String> fromWhims;
    private List<String> imageLinks;
    private List<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        postsLitView = findViewById(R.id.postsLitView);
        back_btn = findViewById(R.id.back_pressed);
        create_post_btn = findViewById(R.id.create_post);
        recipe_btn =findViewById(R.id.recipe_icon);

        fromWhims = new ArrayList<>();
        imageLinks = new ArrayList<>();
        descriptions = new ArrayList<>();
        PostAdapter postAdapter = new PostAdapter(this, fromWhims, imageLinks, descriptions);
        postsLitView.setAdapter(postAdapter);

        Bundle extras = getIntent().getExtras();
        String cuisineName = extras.get("cuisine_name").toString();
        String dishName = extras.get("dish_name").toString();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("cuisines").child(cuisineName).child(dishName);

        databaseReference.child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fromWhims.add(0, dataSnapshot.child("from").getValue(String.class));
                imageLinks.add(0, dataSnapshot.child("imageLink").getValue(String.class));
                //dataMap.put("imageLink", dataSnapshot.child("imageLink").getValue(String.class));
                descriptions.add(0, dataSnapshot.child("description").getValue(String.class));
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPostsActivity.super.onBackPressed();
            }
        });

        create_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, CreatePostActivity.class);
                intent.putExtra("cuisine_name", cuisineName);
                intent.putExtra("dish_name", dishName);
                startActivity(intent);
            }
        });

        recipe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}