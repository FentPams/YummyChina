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
    private List<String> users;
    private ArrayAdapter adapter;
    private FirebaseAuth mAtuh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        mAtuh = FirebaseAuth.getInstance();

        postsLitView = findViewById(R.id.postsLitView);
        users = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        postsLitView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("users").child(mAtuh.getCurrentUser().getUid()).child("received_posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String from = dataSnapshot.child("from").getValue(String.class);
                System.out.println("huici from:" + from);
                users.add(from);
                adapter.notifyDataSetChanged();
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