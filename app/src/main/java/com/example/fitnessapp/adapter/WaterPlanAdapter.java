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

import com.example.fitnessapp.R;
import com.example.fitnessapp.WaterdashboardActivity;
import com.example.fitnessapp.WaterformActivity;
import com.example.fitnessapp.model.WaterBeginnerModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class WaterPlanAdapter extends FirebaseRecyclerAdapter<WaterBeginnerModel, WaterPlanAdapter.ViewHolder> {
    FirebaseRecyclerOptions<WaterBeginnerModel> options;
    Context context;
    private ItemDeleteListener listener;

    public WaterPlanAdapter(@NonNull FirebaseRecyclerOptions<WaterBeginnerModel> options, Context context, ItemDeleteListener listener) {
        super(options);
        this.options = options;
        this.context = context;
        this.listener = listener;
    }

    public WaterPlanAdapter(Context applicationContext, @NonNull FirebaseRecyclerOptions<WaterBeginnerModel> options) {
        super(options);
        this.options = options;
        this.context = applicationContext;
    }

    @Override
    protected void onBindViewHolder(WaterPlanAdapter.ViewHolder holder, int position, WaterBeginnerModel model) {

        holder.title.setText(model.getTitle());

        holder.edit.setOnClickListener(v -> {
            String key = getRef(holder.getLayoutPosition()).getKey();
            Intent intent = new Intent(v.getContext(), WaterformActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("activity", 1);

            v.getContext().startActivity(intent);
        });
        holder.delete.setOnClickListener(v -> {
            String key = getRef(holder.getLayoutPosition()).getKey();
            listener.onItemClick(key);
        });

        holder.waterConsumption.setOnClickListener(v -> {
            String key = getRef(holder.getLayoutPosition()).getKey();
            Intent intent = new Intent(v.getContext(), WaterdashboardActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("activity", 1);
            intent.putExtra("waterQty", model.getDailyWater());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public WaterPlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_consumption_layout, parent, false);
        return new ViewHolder(view);
    }

    public interface ItemDeleteListener {
        void onItemClick(String key);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout edit, delete;
        CardView waterConsumption;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.waterPlanLayout);
            edit = itemView.findViewById(R.id.waterBeginnerEditBtn);
            delete = itemView.findViewById(R.id.waterBeginnerDeleteBtn);
            waterConsumption = itemView.findViewById(R.id.waterBeginnerBtn);
        }
    }
}
