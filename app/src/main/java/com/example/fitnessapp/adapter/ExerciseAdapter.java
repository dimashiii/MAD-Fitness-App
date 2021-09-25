package com.example.fitnessapp.adapter;
import com.example.fitnessapp.model.ExercisesModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ExerciseAdapter {


    private DatabaseReference databaseReference;
    public  ExerciseAdapter(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ExercisesModel.class.getSimpleName());
    }

    public Task<Void> add(ExercisesModel exercisesModel){
        return databaseReference.push().setValue(exercisesModel);
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




/*package com.example.fitnessapp.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.ExercisesModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
*/

/*public class ExerciseAdapter extends FirebaseRecyclerAdapter<ExercisesModel,ExerciseAdapter.myViewHolder> {
    public  ExerciseAdapter(@NonNull FirebaseRecyclerOptions<ExercisesModel> options){
        super(options);
    }

    @Override
    protected  void  onBindViewHolder(@NonNull final myViewholder holder, final int position, final ExercisesModel exercisesModel){
            holder.exerciseName.setText(exercisesModel.getExerciseName());
            holder.count.setText(exercisesModel.getCount());
            holder.duration.setText(exercisesModel.getDuration());
            holder.description.setText(exercisesModel.getExerciseDescription());
            //image

                holder.edit.setOnClickListener(new View.OnClickListener(){
                     @Override
                     public  void onClick(View view){
                        final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                                .setContentHolder(new ViewHolder(R.layout.activity_add_exercise))
                                .setExpanded(true,1100)
                                .create();
                        View myView = dialogPlus.getHolderView();
                        final EditText exerciseName = myView.findViewById(R.id.enterExerciseName);
                        final EditText count = myView.findViewById(R.id.enterCount);
                        final EditText duration = myView.findViewById(R.id.enterDuration);
                        final EditText description = myView.findViewById(R.id.enterExerciseDescription);
                        Button save = myView.findViewById(R.id.btnSave);
                        Button add = myView.findViewById(R.id.btnadd);
                        Button viewbtn = myView.findViewById(R.id.btnView);

                        exerciseName.setText(exercisesModel.getExerciseName());
                        count.setText(exercisesModel.getCount());
                        duration.setText(exercisesModel.getDuration());
                        description.setText(exercisesModel.getExerciseDescription());

                        dialogPlus.show();

                            save.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("exerciseName",exerciseName.getText().toString());
                                    map.put("count",count.getText().toString());
                                    map.put("duration",duration.getText().toString());
                                    map.put("description",description.getText().toString());

                                    FirebaseDatabase.getInstance().getReference().child("ExercsesModel").child(getRef(position).getKey()).updateChildren(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    dialogPlus.dismiss();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    dialogPlus.dismiss();
                                                }
                                            });
                                }
                            });
                     }
                });

                holder.delete.setOnClickListener(view ->{

                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                        builder.setTitle("Delete");
                        builder.setMessage("Delete--?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("ExercisesModel").child(getRef(position).getKey()).removeValue();
                            }


                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                    }
                });
    }


    @NonNull
    @Override
    public  myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card_layout,parent,false);
        return  new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        TextView exerciseName,count,duration,description;
        Button edit, delete, save;
        public  myViewholder(@NonNull View itemView){
            super(itemView);
            exerciseName = (TextView)itemView.findViewById(R.id.enterExerciseName);
            count = (TextView)itemView.findViewById(R.id.enterCount);
            duration = (TextView)itemView.findViewById(R.id.enterDuration);
            description = (TextView)itemView.findViewById(R.id.enterExerciseDescription);
            edit = (Button)itemView.findViewById(R.id.editbtn2);
            delete = (Button)itemView.findViewById(R.id.deletebtn);


        }
    }


}*//*

    private ArrayList<ExercisesModel> exerciselist;








    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView exerciseName;
        private TextView count;
        private TextView duration;
        private TextView description;

        public  MyViewHolder(final View view){
            super(view);
            exerciseName = view.findViewById(R.id.enterExerciseName);
            count = view.findViewById(R.id.enterCount);
            duration = view.findViewById(R.id.enterDuration);
            description = view.findViewById(R.id.enterExerciseDescription);
        }
    }

    private DatabaseReference databaseReference;
    public  ExerciseAdapter(){
        super();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ExercisesModel.class.getSimpleName());
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plan_workout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.MyViewHolder holder, int position) {
         String exercisename = exerciselist.get(position).getExerciseName();
         holder.exerciseName.setText(exercisename);
    }

    @Override
    public int getItemCount() {
        return exerciselist.size();
    }


    public Task<Void> add(ExercisesModel exercisesModel){

        return databaseReference.push().setValue(exercisesModel);
    }

    public ValueEventListener view(ExercisesModel exercisesModel){
        return databaseReference.addValueEventListener((ValueEventListener) exercisesModel);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }*/