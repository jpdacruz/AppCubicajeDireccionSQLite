package com.jpdacruz.appcubicajedireccion.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cargar_datos_main_Fragment extends Fragment {

    private static final String TAG = "Cargar_datos_main_Fragm";

    public Cargar_datos_main_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_carga_datos_principal,container,false);

        final Button btnSilo,btnCelda,btnSBolsa;

        btnSilo = view.findViewById(R.id.buttonSilo);
        btnCelda = view.findViewById(R.id.buttonCelda);
        btnSBolsa = view.findViewById(R.id.buttonSbolsa);

        btnSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarSilosFragment cargarSilosFragment = new CargarSilosFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
                        .replace(R.id.frameCargar,cargarSilosFragment,"cargarSilosFragment");
                fragmentTransaction.commit();
            }
        });

        btnCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarCeldaFragment cargarCeldaFragment = new CargarCeldaFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
                        .replace(R.id.frameCargar,cargarCeldaFragment,"cargarCeldaFragment");
                fragmentTransaction.commit();
            }
        });


        btnSBolsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarSiloBolsaFragment cargarSiloBolsaFragment = new CargarSiloBolsaFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction()
                        .replace(R.id.frameCargar,cargarSiloBolsaFragment,"cargarSbolsaFragment");
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
