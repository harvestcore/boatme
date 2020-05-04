package com.agm.boatme.ui.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agm.boatme.MapManager;
import com.agm.boatme.R;

public class AddPointDialog extends AppCompatDialogFragment {
    EditText addPointName;
    EditText addPointLatitude;
    EditText addPointLongitude;
    Switch addPointIsPort;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view).setTitle("Add new point").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = addPointName.getText().toString();
                float latitude = Float.parseFloat(addPointLatitude.getText().toString());
                float longitude = Float.parseFloat(addPointLongitude.getText().toString());
                boolean isPort = addPointIsPort.isSelected();

                MapManager.getInstance().addToRoute(latitude, longitude, name, isPort);
            }
        });

        addPointName = view.findViewById(R.id.addPoint_name);
        addPointLatitude = view.findViewById(R.id.addPoint_latitude);
        addPointLongitude = view.findViewById(R.id.addPoint_longitude);
        addPointIsPort = view.findViewById(R.id.addPoint_switch);

        return builder.create();
    }
}
