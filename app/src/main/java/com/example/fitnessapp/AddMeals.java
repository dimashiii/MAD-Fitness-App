package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model.ExercisesModel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddMeals extends AppCompatActivity  {

    EditText enterCategory;
    EditText enterMealName;
    EditText enterCalorieAmount;
    EditText enterCookingTime;
    EditText enterIngredients;
    EditText enterMethod;
    Button btnSave;
    Button btnAdd;
    Button btnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meals);

        enterCategory = findViewById(R.id.enterCategory);
        enterMealName = findViewById(R.id.enterMealName);
        enterCalorieAmount = findViewById(R.id.enterCalorieAmount);
        enterCookingTime = findViewById(R.id.enterCookingTime);
        enterIngredients = findViewById(R.id.enterIngredients);
        btnSave = findViewById(R.id.btnSave);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        ExercisesModel exerciseModel = new ExercisesModel();

        btnSave.setOnClickListener(view->{
            HashMap<String, Object> map = new HashMap<>();
            map.put("enterCategory", enterCategory.getText().toString());
            map.put("enterMealName", enterMealName.getText().toString());
            map.put("enterCalorieAmount", enterCalorieAmount.getText().toString());
            map.put("enterCookingTime",  enterCookingTime.getText().toString());
            map.put("enterIngredients",  enterIngredients.getText().toString());
            map.put("enterMethod",  enterMethod.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("ExercisesModel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                enterCategory.setText("");
                enterMealName.setText("");
                enterCalorieAmount.setText("");
                enterCookingTime.setText("");
                enterIngredients.setText("");
                enterMethod.setText("");
                Toast.makeText(this, "Details Inserted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Inserted !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        btnView.setOnClickListener(view->{
            Intent intent = new Intent(this,PlanWorkout.class);

            String Category = enterCategory.getText().toString();
            String MealName = enterMealName.getText().toString();
            String CalorieAmount = enterCalorieAmount.getText().toString();
            String  CookingTime =  enterCookingTime.getText().toString();
            String  Ingredients =  enterIngredients.getText().toString();



            intent.putExtra("Category",Category);
            intent.putExtra("MealName",MealName);
            intent.putExtra("CalorieAmount",CalorieAmount);
            intent.putExtra(" CookingTime", CookingTime);
            intent.putExtra(" Ingredients", Ingredients);

            startActivity(intent);
        });

        btnAdd.setOnClickListener(view->{
            Intent i= new Intent(this,AddExercise.class);
            startActivity(i);
        });


    }

}


