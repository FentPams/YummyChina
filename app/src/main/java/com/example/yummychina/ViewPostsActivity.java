package com.example.yummychina;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewPostsActivity extends AppCompatActivity {

    private ListView postsLitView;
    private FirebaseAuth mAtuh;
    private List<String> fromWhims;
    private List<String> imageLinks;
    private List<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        mAtuh = FirebaseAuth.getInstance();

        postsLitView = findViewById(R.id.postsLitView);

        fromWhims = new ArrayList<>();
        imageLinks = new ArrayList<>();
        descriptions = new ArrayList<>();
        PostAdapter postAdapter = new PostAdapter(this, fromWhims, imageLinks, descriptions);
        postsLitView.setAdapter(postAdapter);


        FirebaseDatabase.getInstance().getReference().child("users").child(mAtuh.getCurrentUser().getUid()).child("received_posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fromWhims.add(dataSnapshot.child("from").getValue(String.class));
                imageLinks.add(dataSnapshot.child("imageLink").getValue(String.class));
                //dataMap.put("imageLink", dataSnapshot.child("imageLink").getValue(String.class));
                descriptions.add(dataSnapshot.child("description").getValue(String.class));
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
    }
}