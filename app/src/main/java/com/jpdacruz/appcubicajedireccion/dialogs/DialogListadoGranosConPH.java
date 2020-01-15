package com.jpdacruz.appcubicajedireccion.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogListadoGranosConPH extends DialogFragment{

    private static final String TAG = "DialogListadoGranosConP";
    private TextView listadoGranos;
    private View view;

    public DialogListadoGranosConPH() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Listado Granos y su PH")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_listado_granos_con_ph, null);
        builder.setView(view);

        return builder.create();
    }

}
