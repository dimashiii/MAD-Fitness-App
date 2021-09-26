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

public class AddExercise extends AppCompatActivity {

    EditText enterExerciseName;
    EditText enterCount;
    EditText enterDuration;
    EditText enterGif;
    EditText enterExerciseDescription;
    Button btnSave;
    Button btnAdd;
    Button btnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        enterExerciseName = findViewById(R.id.enterExerciseName);
        enterCount = findViewById(R.id.enterCount);
        enterDuration = findViewById(R.id.enterDuration);
        enterGif = findViewById(R.id.enterGif);
        enterExerciseDescription = findViewById(R.id.enterExerciseDescription);
        btnSave = findViewById(R.id.btnSave);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        ExercisesModel exerciseModel = new ExercisesModel();

        btnSave.setOnClickListener(view->{
            HashMap<String, Object> map = new HashMap<>();
            map.put("enterExerciseName", enterExerciseName.getText().toString());
            map.put("enterDuration", enterDuration.getText().toString());
            map.put("enterCount", enterCount.getText().toString());
            map.put("enterExerciseDescription", enterExerciseDescription.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("ExercisesModel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                enterExerciseName.setText("");
                enterDuration.setText("");
                enterCount.setText("");
                enterExerciseDescription.setText("");
                Toast.makeText(this, "Details Inserted Successfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Inserted !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        btnView.setOnClickListener(view->{
            Intent intent = new Intent(this,PlanWorkout.class);

            String exerciseName = enterExerciseName.getText().toString();
            String duration = enterDuration.getText().toString();
            String count = enterCount.getText().toString();
            //gif
            String description = enterExerciseDescription.getText().toString();


            intent.putExtra("exerciseName",exerciseName);
            intent.putExtra("count",count);
            intent.putExtra("duration",duration);
            //intent.putExtra gif
            intent.putExtra("description",description);

            startActivity(intent);
        });

        btnAdd.setOnClickListener(view->{
            Intent i= new Intent(this,AddExercise.class);
            startActivity(i);
        });


    }

}