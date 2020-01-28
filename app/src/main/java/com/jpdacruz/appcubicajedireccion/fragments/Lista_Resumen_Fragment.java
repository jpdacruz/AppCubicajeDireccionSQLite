package com.jpdacruz.appcubicajedireccion.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaDiferenciaGrano;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterResumen;
import com.jpdacruz.appcubicajedireccion.clases.SiloSuma;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Resumen_Fragment extends Fragment {

    private static final String TAG = "Lista_Resumen_Fragment";
    private ArrayList<SiloSuma> siloSumas;
    DataBaseHelper conexion;
    RecyclerView recyclerView;

    public Lista_Resumen_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_lista_resumen,container,false);

        conexion = new DataBaseHelper(getContext());
        siloSumas = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewSuma);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarSuma();
        armarReciclerSuma();
        return view;
    }

    private void armarReciclerSuma() {

        AdapterResumen adapterResumen = new AdapterResumen(siloSumas);

        adapterResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SiloSuma siloSuma = siloSumas.get(recyclerView.getChildAdapterPosition(v));

                Intent intent = new Intent(getContext(), CargaDiferenciaGrano.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("diferencia", siloSuma);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        DividerItemDecoration dividerItemDecorationsb = new DividerItemDecoration
                (recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecorationsb);
        recyclerView.setAdapter(adapterResumen);
    }

    private void mostrarSuma() {

        Cursor cursor = conexion.sumarGranos();
        SiloSuma siloSuma;

        while (cursor.moveToNext()){

            siloSuma = new SiloSuma();
            siloSuma.setTipoGrano(cursor.getString(0));
            siloSuma.setCubicaje(cursor.getDouble(1));

            siloSumas.add(siloSuma);
        }
    }
}
