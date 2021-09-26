package com.example.fitnessapp.adapter;

import com.example.fitnessapp.model.ExercisesModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class MealsAdapter {
    private DatabaseReference databaseReference;
    public  MealsAdapter(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ExercisesModel.class.getSimpleName());
    }

    public Task<Void> add(MealsAdapter mealsAdapter){
        return databaseReference.push().setValue(mealsAdapter);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public  Task<Void> remove(String key)
    {
        return  databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public  Query get(){
        return databaseReference;
    }
}
