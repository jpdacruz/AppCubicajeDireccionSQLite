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
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaDiferenciaGrano;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterSilos;
import com.jpdacruz.appcubicajedireccion.clases.Silo;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_silos_Fragment extends Fragment{

    ArrayList<Silo> silos;
    DataBaseHelper conexion;
    RecyclerView recyclerView;

    public Lista_silos_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_lista_silos_es,container,false);

        conexion = new DataBaseHelper(getContext());
        silos = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewSilos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarSilos();

        AdapterSilos adapterSilos = new AdapterSilos(silos);

        adapterSilos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Silo siloAmodificar = silos.get(recyclerView.getChildAdapterPosition(v));

                Intent intent = new Intent(getContext(), CargaDiferenciaGrano.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("silo", siloAmodificar);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(adapterSilos);

        return view;
    }

    private void mostrarSilos() {

        Cursor data = conexion.showSilos();
        Silo silo;

        while (data.moveToNext()) {

            silo = new Silo();
            silo.setIdAuto(data.getInt(0));
            silo.setId(data.getString(1));
            silo.setTipoGrano(data.getString(2));
            silo.setPhGrano(data.getDouble(3));
            silo.setDiametro(data.getDouble(4));
            silo.setAltoGrano(data.getDouble(5));
            silo.setCono(data.getDouble(6));
            silo.setCopete(data.getDouble(7));
            silo.setTotalm3(data.getDouble(8));
            silo.setTotaltons(data.getDouble(9));

            silos.add(silo);

        }
    }
}
