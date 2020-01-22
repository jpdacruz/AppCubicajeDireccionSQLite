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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogConoCeldaFragment extends DialogFragment {

    //widget
    private TextInputLayout mAlturaConoInferior;
    private TextView mAlturaConoInf;
    private Button btnGalpon, btnCeldatrape, btnCeldaConico;
    private DialogConoCeldaFragment.TomarDatosDialogListener listener;

    //vars
    private static final String TAG = "DialogConoCeldaFragment";
    View view;
    String altuConoInfCeldaString,tipoCelda;

    public DialogConoCeldaFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Ingrese altura Cono inferior")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        listener.enviarDatosConoInfDialogCelda(altuConoInfCeldaString, tipoCelda);
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_cono_celda, null);
        builder.setView(view);

        altuConoInfCeldaString ="0.00";
        tipoCelda = "Elegir";

        iniciarComponentes();
        iniciarBotones();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (DialogConoCeldaFragment.TomarDatosDialogListener) context;
    }

    private void iniciarComponentes() {

        mAlturaConoInferior = view.findViewById(R.id.textinputAlturaCopeteCelda);
        mAlturaConoInf = view.findViewById(R.id.textViewConoCelda);
        btnGalpon = view.findViewById(R.id.buttonGalponCelda);
        btnCeldatrape = view.findViewById(R.id.buttonCeldaTrape);
        btnCeldaConico = view.findViewById(R.id.buttonCeldaConica);
    }

    private void iniciarBotones() {

        btnGalpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                altuConoInfCeldaString = "0.00";
                tipoCelda = "Galpon";
                mAlturaConoInf.setText(altuConoInfCeldaString);
            }
        });

        btnCeldatrape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tipoCelda = "Piso_Trapesoidal";
                calcularConoInfCelda(v);
            }
        });

        btnCeldaConico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tipoCelda = "Piso_Conico";
                calcularConoInfCelda(v);
            }
        });
    }

    private void calcularConoInfCelda(View v) {

        if (!validarDatosDialog()) {

            return;
        }

        mAlturaConoInferior.setError("");

        mAlturaConoInf.setText(altuConoInfCeldaString + " mts / " + tipoCelda);
    }

    private boolean validarDatosDialog() {

        altuConoInfCeldaString = mAlturaConoInferior.getEditText().getText().toString();
        
        if (altuConoInfCeldaString.isEmpty()){

            mAlturaConoInferior.setError("Dato obligatorio");
            altuConoInfCeldaString ="0.00";
            tipoCelda = "Elegir";
            return false;
        }
        return true;
    }

    public interface TomarDatosDialogListener {

        void enviarDatosConoInfDialogCelda(String altuConoInfCeldaString, String tipoCelda);
    }

}
