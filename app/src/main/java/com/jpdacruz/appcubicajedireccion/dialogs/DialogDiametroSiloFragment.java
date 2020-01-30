package com.jpdacruz.appcubicajedireccion.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogDiametroSiloFragment extends DialogFragment {

    //widget
    private TextInputLayout mLargoChapa, mChapasLargo;
    private TextView mDiametroDialgo;
    private Button btnCalcular;
    private TomarDatosDialogListener listener;

    //vars
    private static final String TAG = "DialogDiametroSiloFragm";
    View view;
    double diametroDialog, largoChapa;
    int cantChapas;
    String diametroDialogString, largoChapaString, cantChapasString;

    public DialogDiametroSiloFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.enviarDatosDialogDiametro(diametroDialogString);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_diametro_silo, null);
        builder.setView(view);

        iniciarComponentes();
        iniciarBotones();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (TomarDatosDialogListener) context;
    }

    private void iniciarComponentes() {

        mLargoChapa = view.findViewById(R.id.textinputLargoChapa);
        mChapasLargo = view.findViewById(R.id.editTextPhGrano);
        mDiametroDialgo = view.findViewById(R.id.textViewDiametroDialog);
        btnCalcular = view.findViewById(R.id.buttonDiametroDIalog);
    }

    private void iniciarBotones() {

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularDiametroDialog(v);
            }
        });
    }

    private void calcularDiametroDialog(View v) {

        if (!validarDiametroDialog()) {

            return;
        }

        mChapasLargo.setError("");
        mLargoChapa.setError("");
        largoChapa = Double.parseDouble(largoChapaString);
        cantChapas = Integer.parseInt(cantChapasString);

        diametroDialog = Math.round(((largoChapa * cantChapas)/Math.PI) * 100) / 100.0;
        diametroDialogString = String.valueOf(diametroDialog);
        mDiametroDialgo.setText(diametroDialogString);
    }

    private boolean validarDiametroDialog() {

        largoChapaString = mLargoChapa.getEditText().getText().toString();
        cantChapasString = mChapasLargo.getEditText().getText().toString();

        if (largoChapaString.isEmpty()){

            mLargoChapa.setError("Dato requerido");
            return false;
        }

        if (cantChapasString.isEmpty()){

            mChapasLargo.setError("Dato requerido");
            return false;
        }

        return true;
    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogDiametro(String diametro);
    }
}
