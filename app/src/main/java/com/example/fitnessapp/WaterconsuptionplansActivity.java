package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fitnessapp.adapter.WaterPlanAdapter;
import com.example.fitnessapp.databinding.ActivityWaterconsuptionplansBinding;
import com.example.fitnessapp.model.WaterBeginnerModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class WaterconsuptionplansActivity extends AppCompatActivity {
    ActivityWaterconsuptionplansBinding binding;
    String uniqueID;
    WaterPlanAdapter adapter;
    DatabaseReference myRef, myRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaterconsuptionplansBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        uniqueID = pref.getString("uuid", "");


        binding.WaterAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, WaterformActivity.class);
            startActivity(intent);
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setHasFixedSize(true);
        getData();

    }

    private void getData() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("waterConsumptionPlan").child(uniqueID);
        FirebaseRecyclerOptions<WaterBeginnerModel> options
                = new FirebaseRecyclerOptions.Builder<WaterBeginnerModel>()
                .setQuery(query, WaterBeginnerModel.class)
                .build();

        adapter = new WaterPlanAdapter(options, getApplicationContext(), key -> {
            myRef = FirebaseDatabase.getInstance().getReference("waterConsumptionPlan").child(uniqueID).child(key);
            myRef1 = FirebaseDatabase.getInstance().getReference().child("waterConsumption").child(uniqueID).child(key);
            myRef.removeValue();
            myRef1.removeValue();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
        });
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}