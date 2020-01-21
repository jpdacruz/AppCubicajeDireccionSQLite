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
public class DialogTamanioCeldaFragment extends DialogFragment {

    //widget
    private TextInputLayout mLargoCelda, mAnchoCelda;
    private TextView mTamanioCelda;
    private Button btnCalcular;
    private TomarDatosDialogListener listener;

    //vars
    private static final String TAG = "DialogTamanioCeldaFragm";
    View view;
    String largoCeldaString, anchoCeldaString;

    public DialogTamanioCeldaFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Ingrese Tamaño Celda")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.enviarDatosDialogTamCelda(largoCeldaString, anchoCeldaString);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_tamanio_celda, null);
        builder.setView(view);

        largoCeldaString = "0.00";
        anchoCeldaString = "0.00";

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

        mLargoCelda = view.findViewById(R.id.textinputLargoCelda);
        mAnchoCelda = view.findViewById(R.id.textinputAnchoCelda);
        mTamanioCelda = view.findViewById(R.id.textViewTamanioCelda);
        btnCalcular = view.findViewById(R.id.buttonTamanioDialog);
    }

    private void iniciarBotones() {

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularTamañoDialog(v);
            }
        });
    }

    private void calcularTamañoDialog(View v) {

        if (!validarDatosDialog()) {

            return;
        }

        mAnchoCelda.setError("");
        mLargoCelda.setError("");
        mTamanioCelda.setText(largoCeldaString + " mts * " + anchoCeldaString + " mts");
    }

    private boolean validarDatosDialog() {

        largoCeldaString = mLargoCelda.getEditText().getText().toString();
        anchoCeldaString = mAnchoCelda.getEditText().getText().toString();

        if (largoCeldaString.isEmpty()){

            mLargoCelda.setError("Dato obligatorio");
            largoCeldaString = "0.00";
            anchoCeldaString = "0.00";
            return false;
        }

        if (anchoCeldaString.isEmpty()){

            mAnchoCelda.setError("Dato obligatorio");
            largoCeldaString = "0.00";
            anchoCeldaString = "0.00";
            return false;
        }

        return true;
    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogTamCelda(String largoCeldaString, String anchoCeldaString);
    }
}
