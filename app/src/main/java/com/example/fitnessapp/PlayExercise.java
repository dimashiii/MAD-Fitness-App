package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.model.ExercisesModel;
import com.google.firebase.storage.StorageReference;

public class PlayExercise extends AppCompatActivity {
   /* ImageView gif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_exercise);

        Intent intent = getIntent();

        ImageView imageView = (ImageView) findViewById(R.id.gif);
        ExercisesModel imageViewTarget = new ExercisesModel(imageView);
        Glide.with(this).load(downloadUrl).into(imageViewTarget);

        // Reference to an image file in Firebase Storage
        StorageReference storageReference = ;

// ImageView in your Activity
        ImageView imageView = ...;

// Load the image using Glide
        Glide.with(this /* context *//*)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(imageView);
    }*/
}