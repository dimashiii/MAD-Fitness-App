package com.example.fitnessapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.model.ModelHealthTips;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterHealthTips extends FirebaseRecyclerAdapter<ModelHealthTips,AdapterHealthTips.tips_viewholder>
{
    public AdapterHealthTips(@NonNull FirebaseRecyclerOptions<ModelHealthTips> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final tips_viewholder holder, final int position, @NonNull final ModelHealthTips model)
    {
        holder.htopic.setText(model.getHtopic());
        holder.hdesc.setText(model.getHdesc());
        holder.hdate.setText(model.getHdate());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText purl=myview.findViewById(R.id.uimgurl);
                final EditText htopic=myview.findViewById(R.id.uhtopic);
                final EditText hdesc=myview.findViewById(R.id.uhdesc);
                final EditText hdate=myview.findViewById(R.id.uhdate);
                Button submit=myview.findViewById(R.id.usubmit);

                purl.setText(model.getPurl());
                htopic.setText(model.getHtopic());
                hdesc.setText(model.getHdesc());
                hdate.setText(model.getHdate());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("htopic",htopic.getText().toString());
                        map.put("hdesc",hdesc.getText().toString());
                        map.put("hdesc",hdesc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("healthTips")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
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


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Tips");
                builder.setMessage("Delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("healthTips")
                                .child(getRef(position).getKey()).removeValue();
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

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public tips_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_health_tip,parent,false);
        return new tips_viewholder(view);
    }


    class tips_viewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView htopic,hdesc,hdate;
        public tips_viewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            htopic=(TextView)itemView.findViewById(R.id.htopictext);
            hdesc=(TextView)itemView.findViewById(R.id.hdesctext);
            hdate=(TextView)itemView.findViewById(R.id.hdatetext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
