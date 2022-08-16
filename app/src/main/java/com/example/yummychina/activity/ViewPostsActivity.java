package com.example.yummychina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;
import com.example.yummychina.adapter.PostAdapter;
import com.example.yummychina.model.Post;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class displays the posts loaded from firebase in real time, enter from ShanghaiActivity by clicking "Benbang cuisine" button
 *  with three sub-entries( links to RecipeActivity, to FoodStoryActivity, to ViewRestaurantActivity)
 *  and links CreatePostActivity, PostDetailActivity
 *
 *  Features:
 *  1) See real time posts read from firebase(loading is slow, needs improvement)
 *  2) Click single post and enter in the PostDetail interface
 *  3) Click the upper right "+" button and enter in CreatePost interface
 *  4) Three buttons: enter in Recipe interface,FoodStory interface and View Restaurant interface
 *
 *  The according layout:activity_view_posts.xml
 */
public class ViewPostsActivity extends AppCompatActivity {

    ImageView back_btn, create_post_btn, recipe_btn, story_btn, restaurant_btn;

    private ListView postsLitView;
    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        //hooks
        postsLitView = findViewById(R.id.postsLitView);
        back_btn = findViewById(R.id.back_pressed);
        create_post_btn = findViewById(R.id.create_post);
        recipe_btn =findViewById(R.id.recipe_icon);
        story_btn = findViewById(R.id.xlb_icon);
        restaurant_btn = findViewById(R.id.restaurant_icon);

        posts = new ArrayList<>();
        PostAdapter postAdapter = new PostAdapter(this, posts);
        postsLitView.setAdapter(postAdapter);

        // read from firebase under the cuisines/benbang/xiaolongbao path
        Bundle extras = getIntent().getExtras();
        String cuisineName = extras.get("cuisine_name").toString();
        String dishName = extras.get("dish_name").toString();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("cuisines").child(cuisineName).child(dishName);

        // load posts infor from firebase(including user, image, description and story)
        databaseReference.child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post post = new Post();
                post.setPostId(dataSnapshot.getKey());
                post.setFromWhom(dataSnapshot.child("from").getValue(String.class));
                post.setDescription(dataSnapshot.child("description").getValue(String.class));
                post.setImageLink(dataSnapshot.child("imageLink").getValue(String.class));
                post.setStory(dataSnapshot.child("story").getValue(String.class));
                posts.add(0, post);
                postAdapter.notifyDataSetChanged();
            }
            // needs further implementation
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

        // back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPostsActivity.super.onBackPressed();
            }
        });

        //create post and save in right path(cuisines/benbang/xiaolongbao)
        create_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, CreatePostActivity.class);
                intent.putExtra("cuisine_name", cuisineName);
                intent.putExtra("dish_name", dishName);
                startActivity(intent);
            }
        });


        // recipe entry and is saved in right path(cuisines/benbang/xiaolongbao)
        recipe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, RecipeActivity.class);
                intent.putExtra("cuisine_name", cuisineName);
                intent.putExtra("dish_name", dishName);
                startActivity(intent);
            }
        });

        // story entry(further needs to be saved in firebase)
        story_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, FoodStoryActivity.class);
                startActivity(intent);
            }
        });

        // restaurant entry(further needs to be saved in firebase)
        restaurant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostsActivity.this, ViewRestaurantsActivity.class);
                startActivity(intent);
            }
        });

    }
}