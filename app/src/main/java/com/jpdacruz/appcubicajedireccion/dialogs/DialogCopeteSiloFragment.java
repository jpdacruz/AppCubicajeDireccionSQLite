package com.jpdacruz.appcubicajedireccion.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogCopeteSiloFragment extends DialogFragment {

    private View view;
    private Button btnCopetePositivo, btnCopeteNegativo, btnCopeteRaso;
    private TomarDatosDialogListener listener;

    public DialogCopeteSiloFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Calcular Cono");


        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_copete_silo, null);
        builder.setView(view);

        iniciarComponentes(view);

        iniciarBotones(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (TomarDatosDialogListener) context;
    }

    private void iniciarBotones(View view) {

        btnCopetePositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.enviarDatosDialogCopete("p");

                dismiss();
            }
        });

        btnCopeteNegativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.enviarDatosDialogCopete("n");
                dismiss();

            }
        });

        btnCopeteRaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.enviarDatosDialogCopete("r");
                dismiss();
            }
        });
    }

    private void iniciarComponentes(View view) {

        btnCopetePositivo = view.findViewById(R.id.btnCopetePositivo);
        btnCopeteNegativo = view.findViewById(R.id.btnCopeteNegativo);
        btnCopeteRaso = view.findViewById(R.id.btnCopeteRaso);
    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogCopete(String string);
    }

}
