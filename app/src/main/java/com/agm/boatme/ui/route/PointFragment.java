package com.agm.boatme.ui.route;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agm.boatme.R;

public class PointFragment extends Fragment {

    TextView text;
    String textString;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("ResourceType") View root = inflater.inflate(R.id.pointItem, container, false);

        text = root.findViewById(R.id.pointText);

        getData();
        setData();

        return root;
    }

    private void getData() {
        if (getActivity().getIntent().hasExtra("text")) {
            textString = getActivity().getIntent().getStringExtra("text");
        } else {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        text.setText(textString);
    }
}
