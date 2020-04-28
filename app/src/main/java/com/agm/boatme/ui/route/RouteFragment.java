package com.agm.boatme.ui.route;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agm.boatme.MapManager;
import com.agm.boatme.R;

public class RouteFragment extends Fragment implements RVAdapter.OnPointListener {

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_route, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        MapManager.getInstance().addToRoute(12.1f, -123.4567f, "Motril", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "Málaga", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test3", false);
        MapManager.getInstance().addToRoute(12.12f, -123.4567f, "Benalmádena", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test5", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test6", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "Benalmádena", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test1", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test2", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test3", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test4", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test5", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test6", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test7", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test1", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test2", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test3", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test4", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test5", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test6", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test7", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test1", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test2", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test3", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test4", false);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test5", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test6", true);
        MapManager.getInstance().addToRoute(12.12345f, -123.4567f, "test7", false);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RVAdapter rvAdapter = new RVAdapter(getContext(), MapManager.getInstance().getRoute(), this);
        recyclerView.setAdapter(rvAdapter);

        return root;
    }

    @Override
    public void onPointClick(int position) {

//        Intent intent = new Intent(getContext(), PointFragment.class);
//        intent.putExtra("text", data[position]);
//        getContext().startActivity(intent);
    }
}
