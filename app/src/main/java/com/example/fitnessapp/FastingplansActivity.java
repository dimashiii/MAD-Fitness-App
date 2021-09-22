package com.example.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fitnessapp.adapter.FastingAdapter;
import com.example.fitnessapp.databinding.ActivityFastingplansBinding;
import com.example.fitnessapp.model.FastingBeginnerModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FastingplansActivity extends AppCompatActivity {
    ActivityFastingplansBinding binding;
    String uniqueID;
    FastingAdapter adapter;
    DatabaseReference myRef, myRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFastingplansBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("fitnessPref", Activity.MODE_PRIVATE);
        uniqueID = pref.getString("uuid", "");

        binding.fastingAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, FastingformActivity.class);
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
                .child("fastingPlans").child(uniqueID);
        FirebaseRecyclerOptions<FastingBeginnerModel> options
                = new FirebaseRecyclerOptions.Builder<FastingBeginnerModel>()
                .setQuery(query, FastingBeginnerModel.class)
                .build();

        adapter = new FastingAdapter(options, getApplicationContext(), (key, position) -> {

            myRef = FirebaseDatabase.getInstance().getReference("fastingPlans").child(uniqueID).child(key);
            myRef1 = FirebaseDatabase.getInstance().getReference().child("fastingTime").child(uniqueID).child(key);
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