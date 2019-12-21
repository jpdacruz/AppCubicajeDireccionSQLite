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
import android.widget.Button;

import com.jpdacruz.appcubicajedireccion.R;

public class DialogConoSiloFragment extends DialogFragment {

    //widgets
    private View view;
    private Button btnCalcular, btnRaso;
    private TomarDatosDialogListener listener;

    public DialogConoSiloFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Calcular Cono");


        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_cono_silo, null);
        builder.setView(view);

        iniciarComponentes(view);

        iniciarBotones();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (TomarDatosDialogListener) context;
    }

    private void iniciarBotones() {

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.enviarDatosDialogCono(true);
                dismiss();
            }
        });

        btnRaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.enviarDatosDialogCono(false);
                dismiss();
            }
        });
    }

    private void iniciarComponentes(View view) {

        btnCalcular = view.findViewById(R.id.btnDialogConoCalcular);
        btnRaso = view.findViewById(R.id.btnDialogSinCono);

    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogCono(Boolean bool);
    }
}
