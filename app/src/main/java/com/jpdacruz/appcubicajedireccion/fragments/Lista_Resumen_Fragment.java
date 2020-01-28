package com.jpdacruz.appcubicajedireccion.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaDiferenciaGrano;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterDiferencia;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterResumen;
import com.jpdacruz.appcubicajedireccion.clases.DiferenciaGrano;
import com.jpdacruz.appcubicajedireccion.clases.SiloSuma;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Resumen_Fragment extends Fragment {

    private static final String TAG = "Lista_Resumen_Fragment";
    private ArrayList<SiloSuma> siloSumas;
    private ArrayList<DiferenciaGrano> diferenciaGranos;
    DataBaseHelper conexion;

    private RecyclerView recyclerView, recyclerViewDif;

    public Lista_Resumen_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_lista_resumen,container,false);

        conexion = new DataBaseHelper(getContext());
        siloSumas = new ArrayList<>();
        diferenciaGranos = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewSuma);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDif = view.findViewById(R.id.reciclerViewDif);
        recyclerViewDif.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarDif();
        mostrarSuma();
        armarReciclerSuma();
        armarReciclerDife();
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


    private void armarReciclerDife() {

        AdapterDiferencia adapterDiferencia = new AdapterDiferencia(diferenciaGranos);

        adapterDiferencia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DiferenciaGrano diferenciaGrano = diferenciaGranos.get(recyclerViewDif.getChildAdapterPosition(v));

                int idAutoDif = diferenciaGrano.getIdAuto();
                Integer deleteRow = conexion.deleteDif(idAutoDif);

                    if (deleteRow > 0 ){

                        Toast.makeText(getContext(),"Eliminado",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    }else {

                        Toast.makeText(getContext(),"No se pudo eliminar",Toast.LENGTH_LONG).show();
                }
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration
                (recyclerViewDif.getContext(),1);
        recyclerViewDif.addItemDecoration(dividerItemDecoration);
        recyclerViewDif.setAdapter(adapterDiferencia);
    }

    private void mostrarDif() {

        Cursor cursorDif = conexion.showDif();
        DiferenciaGrano diferenciaGrano;

        while ( (cursorDif.moveToNext())){

            diferenciaGrano = new DiferenciaGrano();
            diferenciaGrano.setIdAuto(cursorDif.getInt(0));
            diferenciaGrano.setGrano(cursorDif.getString(1));
            diferenciaGrano.setCubicajeKg(cursorDif.getDouble(2));
            diferenciaGrano.setAfip(cursorDif.getDouble(3));
            diferenciaGrano.setDiferencia(cursorDif.getDouble(4));
            diferenciaGrano.setPorcentaje(cursorDif.getDouble(5));
            diferenciaGrano.setMasOmenos(cursorDif.getString(6));

            diferenciaGranos.add(diferenciaGrano);
        }
    }

    private void mostrarSuma() {

        Cursor cursorSum = conexion.sumarGranos();
        SiloSuma siloSuma;

        while ((cursorSum.moveToNext())){

            siloSuma = new SiloSuma();
            siloSuma.setTipoGrano(cursorSum.getString(0));
            siloSuma.setCubicaje(cursorSum.getDouble(1));

            siloSumas.add(siloSuma);

            Iterator<DiferenciaGrano> it = diferenciaGranos.iterator();

            while (it.hasNext()) {

            DiferenciaGrano diferenciaGrano = it.next();

                if (diferenciaGrano.getGrano().equals(siloSuma.getTipoGrano())) {

                    siloSumas.remove(siloSuma);

                }
            }
        }
    }
}
