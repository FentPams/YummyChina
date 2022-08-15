package com.example.yummychina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class supports users to sign up, entered from SingIn class,
 * will transit to CityCategory class if sign up successfully
 *
 * Features:
 * 1) Sign up with name, username, email, phone number, password
 * 2) The data will be stored into firebase for authentication
 *
 * The according layout: activity_sign_up.xml
 */
public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;
    private TextInputLayout name, username, email, phoneNo, password;
    private Button signup_go, back_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hooks
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);
        signup_go = findViewById(R.id.signup_go);
        back_to_login = findViewById(R.id.back_to_login);

        //signup button
        signup_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        //back button
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //read users'input and respond accordingly
    private void signUp() {
        String nameStr = name.getEditText().getText().toString();
        String usernameStr = username.getEditText().getText().toString();
        String emailStr = email.getEditText().getText().toString();
        String phoneNoStr = phoneNo.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();
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

    //transit to city category activity if sign up successfully
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(this, CityCategoryActivity.class);
        startActivity(intent);
    }
}