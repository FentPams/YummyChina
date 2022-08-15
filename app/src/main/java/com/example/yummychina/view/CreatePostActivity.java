package com.example.yummychina.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class supports users to create posts in real time (by clicking the "+" button on the top right)
 *
 * Features:
 * The created posts will be stored in firebase under the path "cuisine/benbang/posts"
 * The image id, user name, image link, description, story will be stored.
 *
 * The according layout file: activity_create_post.xml
 */
public class CreatePostActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ImageView postImageView;
    private Button btnCreatePost;
    private EditText edtDescription;
    private Bitmap bitmap;
    private String imageIdentifier;
    private Bundle extras;
    private EditText edtStory;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // hooks
        mAuth = FirebaseAuth.getInstance();
        extras = getIntent().getExtras();

        postImageView = findViewById(R.id.postImageView);
        btnCreatePost = findViewById(R.id.btnCreatePost);
        edtDescription = findViewById(R.id.edtDes);
        back_btn = findViewById(R.id.back_pressed);
        edtStory = findViewById(R.id.edtStory);

        //listeners
        postImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadTheSelectedImageToFirebaseStorage();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePostActivity.super.onBackPressed();
            }
        });
    }
    // select image to post
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            Uri chosenImageData = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageData);
                postImageView.setImageBitmap(bitmap);
                edtDescription.setVisibility(View.VISIBLE);
                edtStory.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //store pic into firebase storage
    private void uploadTheSelectedImageToFirebaseStorage() {
        if (bitmap != null) {
            //Get data from ImageView as bytes
            postImageView.setDrawingCacheEnabled(true);
            postImageView.buildDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();
            //generate unique image name
            imageIdentifier = UUID.randomUUID() + ".png";
            UploadTask uploadTask = FirebaseStorage.getInstance().getReference().child("my_images").child(imageIdentifier).putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    //handle unsuccessful uploads
                    Toast.makeText(CreatePostActivity.this, exception.toString(), Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                // upload status message
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CreatePostActivity.this, "Uploading Process Succeed.", Toast.LENGTH_LONG).show();

                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                uploadPostToFirebaseDataBase(task.getResult().toString());
                            } else {
                                Toast.makeText(CreatePostActivity.this, "Upload Image Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    //store upload information into database
    private void uploadPostToFirebaseDataBase(String uploadedImageLink) {
        String cuisineName = extras.get("cuisine_name").toString();
        String dishName = extras.get("dish_name").toString();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("cuisines").child(cuisineName).child(dishName).child("posts");

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("from", mAuth.getCurrentUser().getDisplayName());
        dataMap.put("imageLink", uploadedImageLink);
        dataMap.put("imageId", imageIdentifier);
        dataMap.put("description", edtDescription.getText().toString());
        dataMap.put("story", edtStory.getText().toString());
        //post status message
        databaseReference.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreatePostActivity.this, "Post Sent", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(CreatePostActivity.this, "Post Sent Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}