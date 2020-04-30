package com.agm.boatme.ui.route;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agm.boatme.InterestPoint;
import com.agm.boatme.R;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Holder> {

    Context context;
    ArrayList<InterestPoint> data;
    LayoutInflater inflater;
    OnPointListener onPointListener;

    public RVAdapter(Context context, ArrayList<InterestPoint> data, OnPointListener onPointListener) {
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
        InterestPoint point = data.get(position);
        holder.text.setText(point.getName());
        String lat = String.valueOf(point.getLat());
        String lng = String.valueOf(point.getLng()).substring(0,8);
        lat = lat.length() > 8 ? lat.substring(0, 8) : lat;
        lng = lng.length() > 8 ? lng.substring(0, 8) : lng;
        holder.lat.setText("Lat:" + lat);
        holder.lng.setText("Lng:" + lng);

        if (point.isPort()) {
            holder.image.setImageResource(R.drawable.port);
        } else {
            holder.image.setImageResource(R.drawable.coordinate);
        }

        if (holder.pointLayout != null) {
            holder.pointLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout pointLayout;
        TextView text;
        TextView lat;
        TextView lng;
        ImageView image;
        OnPointListener onPointListener;

        public Holder(@NonNull View itemView, OnPointListener onPointListener) {
            super(itemView);
            text = itemView.findViewById(R.id.route_txt);
            lat = itemView.findViewById(R.id.route_lat);
            lng = itemView.findViewById(R.id.route_lng);
            image = itemView.findViewById(R.id.route_icon);
            pointLayout = itemView.findViewById(R.id.pointItem);
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
