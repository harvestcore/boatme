package com.agm.boatme.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.agm.boatme.MapManager;
import com.agm.boatme.R;
import com.agm.boatme.RecognitionManager;

public class SettingsFragment extends Fragment {

    Switch talkbackSwitch;
    Button addPointDialogButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        talkbackSwitch = root.findViewById(R.id.talkbackSwitch);
        talkbackSwitch.setChecked(RecognitionManager.getInstance().isEnableTalkback());
        talkbackSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecognitionManager.getInstance().setEnableTalkback(talkbackSwitch.isChecked());
            }
        });

        Spinner spinner = root.findViewById(R.id.mapStyleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.map_styles, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MapManager.getInstance().setMapStyle(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                MapManager.getInstance().setMapStyle("Streets");
            }
        });

        addPointDialogButton = root.findViewById(R.id.addPointDialog);
        addPointDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPointDialog addPointDialog = new AddPointDialog();
                addPointDialog.show(getActivity().getSupportFragmentManager(), "Add new point");
            }
        });

        return root;
    }

}
