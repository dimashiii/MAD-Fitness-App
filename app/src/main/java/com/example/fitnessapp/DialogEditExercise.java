package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model.ExercisesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DialogEditExercise extends AppCompatActivity {

    EditText entereExerciseName;
    EditText entereCount;
    EditText entereDuration;
    EditText entereGif;
    EditText entereExerciseDescription;
    Button btnSave;
    Button btnBacK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_edit_exercise);

        entereExerciseName = findViewById(R.id.entereExerciseName);
        entereCount = findViewById(R.id.entereCount);
        entereDuration = findViewById(R.id.entereDuration);
        entereGif = findViewById(R.id.entereGif);
        entereExerciseDescription = findViewById(R.id.entereExerciseDescription);
        btnSave = findViewById(R.id.btnSaveMeals);
        btnBacK = findViewById(R.id.btnBacK);

        ExercisesModel exerciseModel = new ExercisesModel();


        btnSave.setOnClickListener(view->{
            HashMap<String, Object> map = new HashMap<>();
            map.put("enterExerciseName", entereExerciseName.getText().toString());
            map.put("enterDuration", entereDuration.getText().toString());
            map.put("enterCount", entereCount.getText().toString());
            map.put("enterGif",entereGif.getText().toString());
            map.put("enterExerciseDescription", entereExerciseDescription.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("ExercisesModel").push()
                    .setValue(map).addOnSuccessListener(success -> {
                entereExerciseName.setText("");
                entereDuration.setText("");
                entereCount.setText("");
                entereExerciseDescription.setText("");
                entereGif.setText("");
                Toast.makeText(this, "Details UpdatedSuccessfully!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fail -> {
                Toast.makeText(this, "Details Not Updated !!", Toast.LENGTH_SHORT).show();
                ;
            });

        });

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("ExercisesModel");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ExercisesModel exercisesModel = snapshot.getValue(ExercisesModel.class);

                    String exerciseName = exercisesModel.getExerciseName();
                    String exerciseDuration = exercisesModel.getDuration();
                    String exerciseCount = String.valueOf(exercisesModel.getCount());
                    String exerciseDescripton = String.valueOf(exercisesModel.getExerciseDescription());

                    entereExerciseName.setText(exerciseName);
                    entereDuration.setText(exerciseDuration);
                    entereCount.setText(exerciseCount);
                    entereExerciseDescription.setText(exerciseDescripton);

                    entereExerciseName.setText(String.valueOf(postSnapshot.child("enterExerciseName").getValue()));
                    entereDuration.setText(String.valueOf(postSnapshot.child("enterDuration").getValue()));
                    entereCount.setText(String.valueOf(postSnapshot.child("enterCount").getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBacK.setOnClickListener(view->{
            Intent intent = new Intent(this, PlanWorkout.class);

            startActivity(intent);
        });

    }
}





