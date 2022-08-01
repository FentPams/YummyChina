package com.example.yummychina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;
    private TextInputLayout name, username, email, phoneNo, password;
    private Button signup_go, back_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);
        signup_go = findViewById(R.id.signup_go);
        back_to_login = findViewById(R.id.back_to_login);

        signup_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }


    private void signUp() {
        String nameStr = name.getEditText().getText().toString();
        String usernameStr = username.getEditText().getText().toString();
        String emailStr = email.getEditText().getText().toString();
        String phoneNoStr = phoneNo.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();
        System.out.println("huici email:" + emailStr);
        System.out.println("huici password:" + passwordStr);
        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Sign up Succeed", Toast.LENGTH_LONG).show();

                    FirebaseUser user = mAuth.getCurrentUser();

                    // Firebase Database: insert user's name under uid
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref =  database.getReference().child("users").child(user.getUid());
                    ref.child("name").setValue(nameStr);
                    ref.child("username").setValue(usernameStr);
                    ref.child("email").setValue(emailStr);
                    ref.child("phoneNo").setValue(phoneNoStr);

                    // Firebase Authentication: update user's profile with displayname
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(usernameStr)
                            .build();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User profile updated.");
                                    }
                                }
                            });

                    transitionToSocialMediaActivity();
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(this, SocialMediaActivity.class);
        startActivity(intent);
    }
}