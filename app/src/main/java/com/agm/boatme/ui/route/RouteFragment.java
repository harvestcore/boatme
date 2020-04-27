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

import com.agm.boatme.R;

public class RouteFragment extends Fragment implements RVAdapter.OnPointListener {

    RecyclerView recyclerView;

    String[] data = {
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1",
            "test1"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_route, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RVAdapter rvAdapter = new RVAdapter(getContext(), data, this);
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
