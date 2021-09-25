package com.example.fitnessapp.adapter;

import com.example.fitnessapp.model.WorkoutPlanModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class WorkoutPlanAdapter {

    private DatabaseReference databaseReference;

    public WorkoutPlanAdapter() {


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(WorkoutPlanModel.class.getSimpleName());
    }

    public Task<Void> add(WorkoutPlanModel workoutPlanModel) {

        return databaseReference.push().setValue(workoutPlanModel);
    }

    public ValueEventListener view(WorkoutPlanModel workoutPlanModel) {
        return databaseReference.addValueEventListener((ValueEventListener) workoutPlanModel);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    /*recyclerView = findViewById(R.id.workoutRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FirebaseRecyclerOptions<WorkoutPlanModel> options = new FirebaseRecyclerOptions.Builder<WorkoutPlanModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("WorkoutPlanModel"),WorkoutPlanModel.class).build();



    workoutPlanAdapter = new WorkoutPlanAdapter(options);
        recyclerView.setAdapter(workoutPlanAdapter);*/




}
