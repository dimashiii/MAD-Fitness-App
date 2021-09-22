package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.databinding.ActivityDailygoalsBinding;

public class DailygoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDailygoalsBinding binding = ActivityDailygoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fastingBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, FastingplansActivity.class));
        });

        binding.waterBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, WaterconsuptionplansActivity.class));
        });
    }
}