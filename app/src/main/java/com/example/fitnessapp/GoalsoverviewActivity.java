package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.databinding.ActivityGoalsoverviewBinding;

import java.util.UUID;

public class GoalsoverviewActivity extends AppCompatActivity {
    String uniqueID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGoalsoverviewBinding binding = ActivityGoalsoverviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        uniqueID = pref.getString("uuid", "");

        if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            pref.edit().putString("uuid", uniqueID).apply();
        }

        binding.start.setOnClickListener(v -> {


            startActivity(new Intent(this, DailygoalsActivity.class));

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}