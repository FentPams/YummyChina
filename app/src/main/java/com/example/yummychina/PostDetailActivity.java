package com.example.yummychina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PostDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView imgPost,imgCurrentUser;
    TextView txtPostDesc, txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button post_btn;

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

        Bundle extras = getIntent().getExtras();
        String imageLink = extras.get("imageLink").toString();
        String fromWhom = extras.get("fromWhom").toString();
        String description = extras.get("description").toString();
        String postId = extras.get("postId").toString();

        Picasso.get().load(imageLink).into(imgPost);
        txtPostTitle.setText(description);
        txtPostDateName.setText(fromWhom);

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
    }
}