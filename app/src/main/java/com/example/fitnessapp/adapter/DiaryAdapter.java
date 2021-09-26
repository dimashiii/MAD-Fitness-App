package com.example.fitnessapp.adapter;

import com.example.fitnessapp.model.diarymodel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class DiaryAdapter {


    private DatabaseReference databaseReference;
    public  DiaryAdapter(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(diarymodel.class.getSimpleName());
    }

    public Task<Void> add(diarymodel dmodel){
        return databaseReference.push().setValue(dmodel);
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




