package com.example.yummychina.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummychina.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class displays recipe of food, enter from ViewPostsActivity by clicking recipe button
 * <p>
 * Features:
 * Load recipe from firebase(but data is hardcoded into firebase, needs improvement)
 * <p>
 * Expected Features:
 * 1) Links to youtube video and source of recipe
 * 2) Supports bookmark feature
 * <p>
 * The according layout: activity_recipe.xml
 */
public class RecipeActivity extends AppCompatActivity {

    private ListView instructionListView;
    private ListView ingredientsListView;
    private List<String> instruction;
    private List<String> ingredients;
    private ArrayAdapter instructionAdapter;
    private ArrayAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //hooks
        instructionListView = findViewById(R.id.instruction);
        ingredientsListView = findViewById(R.id.ingredients);

        instruction = new ArrayList<>();
        ingredients = new ArrayList<>();

        instructionAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, instruction);
        ingredientsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients);


        instructionListView.setAdapter(instructionAdapter);
        ingredientsListView.setAdapter(ingredientsAdapter);

        // read from firebase under the cuisines/benbang/xiaolongbao path
        Bundle extras = getIntent().getExtras();
        String cuisineName = extras.get("cuisine_name").toString();
        String dishName = extras.get("dish_name").toString();

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("cuisines").child(cuisineName).child(dishName);

        //load instructions from firebase
        databaseReference.child("instruction").addChildEventListener(new ChildEventListener() {
            // load to interface
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                instruction.add(dataSnapshot.getValue().toString());
                instructionAdapter.notifyDataSetChanged();
            }

            // need future implementation
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

        //load  ingredients from firebase
        databaseReference.child("ingredients").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ingredients.add(dataSnapshot.getValue().toString());
                ingredientsAdapter.notifyDataSetChanged();
            }

            // need future implementation
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