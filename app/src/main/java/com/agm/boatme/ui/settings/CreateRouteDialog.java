package com.agm.boatme.ui.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agm.boatme.MapManager;
import com.agm.boatme.MarinePoints;
import com.agm.boatme.R;

public class CreateRouteDialog extends AppCompatDialogFragment {
    Spinner aPort;
    Spinner bPort;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_route_dialog, null);

        builder.setView(view).setTitle("Create new route").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ap = aPort.getSelectedItem().toString();
                String bp = bPort.getSelectedItem().toString();

                if (ap != bp) {
                    MapManager.getInstance().setRoute(MarinePoints.getInstance().getRoute(ap, bp));
                }
            }
        });

        aPort = view.findViewById(R.id.aPortSpinner);
        bPort = view.findViewById(R.id.bPortSpinner);

        ArrayAdapter<CharSequence> a1 = ArrayAdapter.createFromResource(getContext(), R.array.ports, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> a2 = ArrayAdapter.createFromResource(getContext(), R.array.ports, android.R.layout.simple_spinner_dropdown_item);
        aPort.setAdapter(a1);
        bPort.setAdapter(a2);

        return builder.create();
    }
}
