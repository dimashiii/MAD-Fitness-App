package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ExerciseDisplay();
            }
        });

    }

    //Navigation part-->workout
    public void ExerciseDisplay(){
        Intent intent = new Intent(this,PlanWorkout.class);

        String exerciseName = enterExerciseName.getText().toString();
        String count = enterCount.getText().toString();
        String duration = enterDuration.getText().toString();
        //gif;
        String description = enterExerciseDescription.getText().toString();

        intent.putExtra("EXERCISE_NAME",exerciseName);
        intent.putExtra("COUNT",count);
        intent.putExtra("DURATION",duration);
        //intent.putExtra gif
        intent.putExtra("DESCRIPTION",description);


        startActivity(intent);

    }
}