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
public class DialogAlturaGranoFragment extends DialogFragment {

    private TextInputLayout mAnchoChapa, mChapasAlto;
    private TextView mALturaGranoDialog;
    private Button btnCalcular;
    private TomarDatosDialogListener listener;
    View view;

    //vars
    private static final String TAG = "DialogAlturaGranoFragme";
    double AlturaGranoDialog, AnchoChapa,cantChapasAlto;
    String AlturaGranoDialogString, anchoChapaString, cantChapasAltoString;

    public DialogAlturaGranoFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Calcular Altura Grano")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.enviarDatosDialogAlturaGrano(AlturaGranoDialogString);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_altura_grano, null);
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

        mAnchoChapa = view.findViewById(R.id.textinputAlturaGrano);
        mChapasAlto = view.findViewById(R.id.textinputCantChapasAlto);
        mALturaGranoDialog = view.findViewById(R.id.textViewAlturaGranoDialog);
        btnCalcular = view.findViewById(R.id.buttonAlturaGranoDialog);
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

        mChapasAlto.setError("");
        mAnchoChapa.setError("");
        AnchoChapa = Double.parseDouble(anchoChapaString);
        cantChapasAlto = Double.parseDouble(cantChapasAltoString);
        AlturaGranoDialog = AnchoChapa * cantChapasAlto;
        AlturaGranoDialogString = String.valueOf(AlturaGranoDialog);
        mALturaGranoDialog.setText(AlturaGranoDialogString);
    }

    private boolean validarDiametroDialog() {

        anchoChapaString  = mAnchoChapa.getEditText().getText().toString();
        cantChapasAltoString = mChapasAlto.getEditText().getText().toString();

        if (anchoChapaString.isEmpty()){

            mAnchoChapa.setError("Dato obligatorio");
            return false;
        }

        if (cantChapasAltoString.isEmpty()){

            mChapasAlto.setError("Dato obligatorio");
            return false;
        }

        return true;
    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogAlturaGrano(String diametro);
    }

}
