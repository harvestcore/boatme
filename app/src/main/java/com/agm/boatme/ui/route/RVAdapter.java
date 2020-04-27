package com.agm.boatme.ui.route;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agm.boatme.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Holder> {

    Context context;
    String[] data;
    LayoutInflater inflater;
    OnPointListener onPointListener;

    public RVAdapter(Context context, String[] data, OnPointListener onPointListener) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.onPointListener = onPointListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.route_row, parent, false);
        return new Holder(view, this.onPointListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.text.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout pointLayout;
        TextView text;
        OnPointListener onPointListener;

        public Holder(@NonNull View itemView, OnPointListener onPointListener) {
            super(itemView);
            text = itemView.findViewById(R.id.route_txt);
            pointLayout = itemView.findViewById(R.id.pointFragment);
            this.onPointListener = onPointListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPointListener.onPointClick(getAdapterPosition());
        }
    }

    public interface OnPointListener {
        void onPointClick(int position);
    }
}
