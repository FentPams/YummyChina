package com.example.yummychina.activity;

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
import com.example.yummychina.model.Comment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class displays the single post, enter from ViewPostsActivity by clicking single post
 * It reads from firebase and load the image, description, user, story in this interface.
 * Supports add comments function
 *
 *  Features:
 *  1) See real time posts(the image, description, user, story) read from firebase
 *  2) Add comments from users and stores in firebase
 *  3) Display the comments in real time(with time showed)
 *
 *  Expected Features(not completed):
 *  1) Delete comment by users
 *  2) "Like" feature
 *  3) Bookmark feature
 *  4) UI needs improvement
 *
 *  The according layout:activity_post_detail.xml
 */
public class PostDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView imgPost,imgCurrentUser;
    TextView txtPostDesc, txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button post_btn;
    private ListView commentsListView;
    private List<Comment> comments;

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
        comments = new ArrayList<>();
        // hook for comment adapter
        CommentAdapter commentAdapter = new CommentAdapter(this,comments);
        commentsListView.setAdapter(commentAdapter);

        // load data of post from firebase
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

        // loads comments, user and time from firebase
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

            // read users' input comment into firebase
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comment comment = new Comment();
                comment.setFromWhom(dataSnapshot.child("from").getValue(String.class));
                comment.setContent(dataSnapshot.child("comment").getValue(String.class));
                comment.setDate(new Date(
                        Long.parseLong(dataSnapshot.child("time").getValue(String.class))));
                comments.add(0, comment);
                commentAdapter.notifyDataSetChanged();
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
    }
}