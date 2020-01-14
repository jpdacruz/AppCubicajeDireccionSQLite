package com.jpdacruz.appcubicajedireccion.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogTipoPHFragment extends DialogFragment {

    //widgetes
    private Spinner mSpinner;
    private TextInputLayout mPhGrano;
    private Button btnListarPh;
    private View view;
    private TomarDatosDialogListener listener;

    //vars
    double phGrano;
    String phGranoString, tipoGranoString;

    private static final String TAG = "DialogTipoPHFragment";

    public DialogTipoPHFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Tipo y PH del grano")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        botonOk();

                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_dialog_tipo_ph, null);
        builder.setView(view);

        iniciarComponentes();
        crearSpiner();
        iniciarBotones();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (TomarDatosDialogListener) context;
    }

    private void iniciarComponentes() {

        mSpinner = view.findViewById(R.id.spinnerSilos);
        mPhGrano = view.findViewById(R.id.editTextPhGrano);
        btnListarPh = view.findViewById(R.id.buttonListadoPh);
    }

    private void crearSpiner() {

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tipoGranoString = mSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void iniciarBotones() {

        btnListarPh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void botonOk() {

        if (!validarDatos()){

            return;
        }

        listener.enviarDatosDialogTipoPh(tipoGranoString, phGranoString);
    }

    private boolean validarDatos() {

        phGranoString = mPhGrano.getEditText().getText().toString();

        if (phGranoString.isEmpty()){

            mPhGrano.setError("Dato obligatorio");
            return false;
        }

        return true;
    }

    public interface TomarDatosDialogListener {

        void enviarDatosDialogTipoPh(String tipo, String ph);
    }

}
