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
import android.widget.TextView;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaCeldasActivity;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterCeldas;

import com.jpdacruz.appcubicajedireccion.clases.Celda;

import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Celdas_Fragment extends Fragment {

    ArrayList<Celda> celdas;
    DataBaseHelper conexion;
    RecyclerView recyclerView;

    public Lista_Celdas_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_lista_celdas,container,false);

        conexion = new DataBaseHelper(getContext());
        celdas = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewCeldas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarCelda();

        AdapterCeldas adapterCeldas = new AdapterCeldas(celdas);

        adapterCeldas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Celda celdaAmodificar = celdas.get(recyclerView.getChildAdapterPosition(v));

                Intent intent = new Intent(getContext(), CargaCeldasActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("celda",celdaAmodificar);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(adapterCeldas);

        return view;
    }

    private void mostrarCelda() {

        Cursor data = conexion.showCeldas();
        Celda celda;

        while (data.moveToNext()) {

            celda = new Celda();
            celda.setIdAuto(data.getInt(0));
            celda.setId(data.getString(1));
            celda.setTipoGrano(data.getString(2));
            celda.setPhGrano(data.getDouble(3));
            celda.setLargoCelda(data.getDouble(4));
            celda.setAnchoCelda(data.getDouble(5));
            celda.setAltoGrano(data.getDouble(6));
            celda.setTipoCelda(data.getString(7));
            celda.setCono(data.getDouble(8));
            celda.setCopete(data.getDouble(9));
            celda.setTotalm3(data.getDouble(10));
            celda.setTotaltons(data.getDouble(11));

            celdas.add(celda);
        }
    }
}
