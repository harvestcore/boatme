package com.agm.boatme.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.agm.boatme.R;
import com.agm.boatme.RecognitionManager;

public class SettingsFragment extends Fragment {

    Switch talkbackSwitch;

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

        return root;
    }

}
