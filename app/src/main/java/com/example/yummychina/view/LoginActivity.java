package com.example.yummychina.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

/**
 * This class supports login feature, links the CityCategory Activity
 *
 * Features:
 * 1)Sign in with email and password(authenticated by firebase)
 * 2)Jump to sign up interface(SignUpActivity) by clicking signup button
 * 3)Animation actions when transiting to sign up interface
 *
 * The according layout:activity_login.xml
 */
public class LoginActivity extends AppCompatActivity {
    private Button callSignUp, login_btn;
    private ImageView image;
    private TextView logoText, sloganText;
    private TextInputLayout username, password;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // needs to figure out the updated method to display fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // hooks
        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText =findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username =findViewById(R.id.username);
        password =findViewById(R.id.password);
        login_btn =findViewById(R.id.login_btn);

        mAuth = FirebaseAuth.getInstance();

        // animation action
        callSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(username,"username_trans");
                pairs[4] = new Pair<View,String>(password,"password_trans");
                pairs[5] = new Pair<View,String>(login_btn,"button_trans");
                pairs[6] = new Pair<View,String>(callSignUp,"login_signup_trans");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent,options.toBundle());
                }
                else{
                    startActivity(intent);
                }

            }
        });

        // login
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            transitionToSocialMediaActivity();
        }
    }

    // read users' input and respond accordingly
    private void signIn() {
        mAuth.signInWithEmailAndPassword(username.getEditText().getText().toString(), password.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Sign In Succeed", Toast.LENGTH_LONG).show();
                    transitionToSocialMediaActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //transit to city category activity if sign in successfully
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(this, CityCategoryActivity.class);
        startActivity(intent);
    }
}