package com.example.fitnessapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.FastingformActivity;
import com.example.fitnessapp.FastingstopwatchActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.model.FastingBeginnerModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FastingAdapter extends FirebaseRecyclerAdapter<FastingBeginnerModel, FastingAdapter.ViewHolder> {

    FirebaseRecyclerOptions<FastingBeginnerModel> options;
    Context context;
    private ItemClickListener listener;

    public FastingAdapter(@NonNull FirebaseRecyclerOptions<FastingBeginnerModel> options, Context context, ItemClickListener listener) {
        super(options);
        this.options = options;
        this.context = context;
        this.listener = listener;
    }

    public FastingAdapter(Context applicationContext, @NonNull FirebaseRecyclerOptions<FastingBeginnerModel> options) {
        super(options);
        this.options = options;
        this.context = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull FastingAdapter.ViewHolder holder, int position, @NonNull FastingBeginnerModel fastingBeginnerModel) {

        holder.title.setText(fastingBeginnerModel.getTitle());

        holder.edit.setOnClickListener(v -> {
            String key =getRef(holder.getLayoutPosition()).getKey();
            Intent intent = new Intent(v.getContext(), FastingformActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("activity", 1);
            v.getContext().startActivity(intent);

        });
        holder.delete.setOnClickListener(v -> {
            String key1 = getRef(holder.getLayoutPosition()).getKey();
            listener.onItemClick(key1, position);
        });

        holder.waterConsumption.setOnClickListener(v -> {
            String key = getRef(holder.getLayoutPosition()).getKey();
            Intent intent = new Intent(v.getContext(), FastingstopwatchActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("activity", 1);
            v.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public FastingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fasting_layout, parent, false);
        return new ViewHolder(view);
    }


    public interface ItemClickListener {
        void onItemClick(String key, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout edit, delete;
        CardView waterConsumption;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.fastingPlanTitle);
            edit = itemView.findViewById(R.id.fastingBeginnerEditBtn);
            delete = itemView.findViewById(R.id.fastingBeginnerDeleteBtn);
            waterConsumption = itemView.findViewById(R.id.fastingBeginnerBtn);

        }
    }
}
