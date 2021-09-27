package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartDiary extends AppCompatActivity {
    Button startwriteDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_diary);

        startwriteDiary = findViewById(R.id.diarystartbtn2);

        startwriteDiary.setOnClickListener(view ->{
            Intent intentNext = new Intent(this, BmiDiaryForm.class);
            startActivity(intentNext);
        });
    }
}



