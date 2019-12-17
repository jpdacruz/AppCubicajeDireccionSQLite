package com.jpdacruz.appcubicajedireccion.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog_Elegir_Fragment extends DialogFragment {


    public Dialog_Elegir_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_elegir, container, false);

        Button btnToSilo, btnToCelda,btnToSiloB,btnToESilo;

        btnToSilo = view.findViewById(R.id.buttonToSIlo);
        btnToCelda = view.findViewById(R.id.buttonToCelda);
        btnToSiloB = view.findViewById(R.id.buttonToSiloBolsa);
        btnToESilo = view.findViewById(R.id.buttonToEsilo);

        btnToSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CargaSilosActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
