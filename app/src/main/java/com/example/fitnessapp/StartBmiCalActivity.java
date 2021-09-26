package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;

public class StartBmiCalActivity extends AppCompatActivity {

    Button startCalBmi;
    Button Diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_bmi_cal);

        startCalBmi = (Button)findViewById(R.id.bmistartbtn);
        Diary = (Button)findViewById(R.id.diarybtn);

        startCalBmi.setOnClickListener(view ->{
            Intent intentNext = new Intent(this, CalcBmi.class);
            startActivity(intentNext);
        });

        Diary.setOnClickListener(view ->{
            Intent intentNext = new Intent(this, CalcBmi.class);
            startActivity(intentNext);
        });

    }
}












