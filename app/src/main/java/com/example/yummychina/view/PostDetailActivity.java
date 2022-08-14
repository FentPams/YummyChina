package com.example.yummychina.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;
import com.example.yummychina.adapter.CommentAdapter;
import com.example.yummychina.adapter.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView imgPost,imgCurrentUser;
    TextView txtPostDesc, txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button post_btn;
    private ListView commentsListView;
    private List<String> fromWhims;
    private List<String> comments;
    private List<String> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        mAuth = FirebaseAuth.getInstance();

        //hooks
        imgPost = findViewById(R.id.post_detail_img);
        imgCurrentUser= findViewById(R.id.post_detail_user_pic);

        txtPostTitle = findViewById(R.id.post_detail_title);
        txtPostDesc = findViewById(R.id.post_detail_desc);
        txtPostDateName = findViewById(R.id.post_detail_datename);

        editTextComment = findViewById(R.id.post_detail_add_comment);
        post_btn = findViewById(R.id.post_detail_add_comment_button);

        // init list view related fields
        commentsListView = findViewById(R.id.comments);
        fromWhims = new ArrayList<>();
        comments = new ArrayList<>();
        dates = new ArrayList<>();
        CommentAdapter commentAdapter = new CommentAdapter(this, fromWhims, comments, dates);
        commentsListView.setAdapter(commentAdapter);

        Bundle extras = getIntent().getExtras();
        String imageLink = extras.get("imageLink").toString();
        String fromWhom = extras.get("fromWhom").toString();
        String description = extras.get("description").toString();
        String postId = extras.get("postId").toString();
        String story = extras.get("story").toString();

        Picasso.get().load(imageLink).into(imgPost);
        txtPostTitle.setText(description);
        txtPostDateName.setText(fromWhom);
        txtPostDesc.setText(story);

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = editTextComment.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("comments").child(postId);

                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("comment", comment);
                dataMap.put("from", mAuth.getCurrentUser().getDisplayName());
                dataMap.put("time", Long.toString(System.currentTimeMillis()));
                databaseReference.push().setValue(dataMap);
                finish();
                startActivity(getIntent());
            }
        });

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("comments").child(postId);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fromWhims.add(0, dataSnapshot.child("from").getValue(String.class));
                comments.add(0, dataSnapshot.child("comment").getValue(String.class));
                dates.add(0, convertTimeMillsToDate(dataSnapshot.child("time").getValue(String.class)));
                commentAdapter.notifyDataSetChanged();
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

    private String convertTimeMillsToDate(String timeMills) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(Long.parseLong(timeMills));
        return formatter.format(date);
    }
}