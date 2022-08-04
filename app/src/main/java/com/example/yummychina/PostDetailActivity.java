package com.example.yummychina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {
    ImageView imgPost,imgCurrentUser;
    TextView txtPostDesc, txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button post_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

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

        Picasso.get().load(imageLink).into(imgPost);
        txtPostTitle.setText(description);
        txtPostDateName.setText(fromWhom);
    }
}