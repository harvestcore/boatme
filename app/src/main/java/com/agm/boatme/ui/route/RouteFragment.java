package com.agm.boatme.ui.route;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RVAdapter rvAdapter = new RVAdapter(getContext(), MapManager.getInstance().getRoute(), this);
        recyclerView.setAdapter(rvAdapter);

        MapManager.getInstance().deselectCurrentFromList();
        return root;
    }

    @Override
    public void onPointClick(int position) {
        MapManager.getInstance().removeFromRoute(position);
        recyclerView.setAdapter(new RVAdapter(getContext(), MapManager.getInstance().getRoute(), this));
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new RVAdapter(getContext(), MapManager.getInstance().getRoute(), this));
    }
}
