package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.model.Meals;
import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BreakfastMeal extends AppCompatActivity {

    TextView mealName;
    TextView mealTime;
    TextView calories;
    TextView cookingTime;
    TextView ingredients;
    Button editMeal;
    Button deleteMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_meal);

        Intent intent = getIntent();

        mealName = (TextView)findViewById(R.id.mealName);
        mealTime = (TextView)findViewById(R.id.mealTime);
        calories = (TextView)findViewById(R.id.calories);
        cookingTime = (TextView)findViewById(R.id.cookingTime);
        ingredients = (TextView)findViewById(R.id.ingredients);
        editMeal = (Button) findViewById(R.id.editmeals);
        deleteMeal =(Button) findViewById(R.id.deletemeals);

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Meals");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Meals meals = snapshot.getValue(Meals.class);



                    String mealname = meals.getMealName();
                    String mealtime = meals.getCategory();
                    String calorie = meals.getCalorieAmount();
                    String cookingtime = meals.getCookingTime();
                    String ingredient = meals.getIngredients();
                    mealName.setText(mealname);
                    mealTime.setText(mealtime);
                    calories.setText(calorie);
                    cookingTime.setText(cookingtime);
                    ingredients.setText(ingredient);


                    mealName.setText(String.valueOf(postSnapshot.child("enterMealname").getValue()));
                    mealTime.setText(String.valueOf(postSnapshot.child("enterCategory").getValue()));
                    calories.setText(String.valueOf(postSnapshot.child("enterCalorieAmount").getValue()));
                    cookingTime.setText(String.valueOf(postSnapshot.child("enterCookingTime").getValue()));
                    ingredients.setText(String.valueOf(postSnapshot.child("enterIngredients").getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      editMeal.setOnClickListener(view->{
            Intent intentEditmeal = new Intent(this, AddMeals.class);
            startActivity(intentEditmeal);
        });

        deleteMeal.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("Models").removeValue().addOnSuccessListener(success -> {
                mealName.setText("");
                mealTime.setText("");
                calories.setText("");
                cookingTime.setText("");
                ingredients.setText("");
                Toast.makeText(this, "Details Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not deleted Successfully!", Toast.LENGTH_SHORT).show();
            });



        });


    }
}