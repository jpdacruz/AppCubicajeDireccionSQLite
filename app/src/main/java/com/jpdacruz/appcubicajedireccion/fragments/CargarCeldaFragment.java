package com.jpdacruz.appcubicajedireccion.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpdacruz.appcubicajedireccion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CargarCeldaFragment extends Fragment {


    public CargarCeldaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cargar_celda, container, false);
    }

}
