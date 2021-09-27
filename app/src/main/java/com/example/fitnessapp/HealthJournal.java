package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HealthJournal extends AppCompatActivity {

    CardView bmiCard;
    CardView healthPrCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_journal);

        bmiCard = (CardView)findViewById(R.id.bmiBtn);
        healthPrCard = (CardView)findViewById(R.id.healthprBtn);

        bmiCard.setOnClickListener(view ->{
            Intent intentNext = new Intent(this, StartBmiCalActivity.class);
            startActivity(intentNext);
        });

        healthPrCard.setOnClickListener(view ->{
            Intent intentNext = new Intent(this, MainActivity.class);
            startActivity(intentNext);
        });
    }

}