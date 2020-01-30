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
import android.widget.TextView;
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaCeldasActivity;
import com.jpdacruz.appcubicajedireccion.activities.CargaSiloBolsaActivity;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterCeldas;

import com.jpdacruz.appcubicajedireccion.adapter.AdapterSb;
import com.jpdacruz.appcubicajedireccion.clases.Celda;

import com.jpdacruz.appcubicajedireccion.clases.InterfaceGeneral;
import com.jpdacruz.appcubicajedireccion.clases.SiloBolsa;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Celdas_Fragment extends Fragment implements InterfaceGeneral {

    private static final String TAG = "Lista_Celdas_Fragment";

    ArrayList<Celda> celdas;
    ArrayList<SiloBolsa> siloBolsas;
    DataBaseHelper conexion;
    RecyclerView recyclerView, recyclerViewsb;


    public Lista_Celdas_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_lista_celdas,container,false);

        conexion = new DataBaseHelper(getContext());
        celdas = new ArrayList<>();
        siloBolsas = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewCeldas);
        recyclerViewsb = view.findViewById(R.id.recyclerViewSiloBolsa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewsb.setLayoutManager(new LinearLayoutManager(getContext()));

        mostrarCelda();
        mostrarSb();
        armarReciclerCelda();
        armarReciclerSb();
        return view;
    }

    private void armarReciclerSb() {

        AdapterSb adapterSb = new AdapterSb(siloBolsas);
        adapterSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SiloBolsa siloBolsaAmodificar = siloBolsas.get(recyclerViewsb.getChildAdapterPosition(v));

                Intent intentsb = new Intent(getContext(), CargaSiloBolsaActivity.class);

                Bundle bundleSb = new Bundle();
                bundleSb.putSerializable("sb", siloBolsaAmodificar);
                intentsb.putExtras(bundleSb);
                startActivity(intentsb);
            }
        });

        DividerItemDecoration dividerItemDecorationsb = new DividerItemDecoration(recyclerViewsb.getContext(),1);
        recyclerViewsb.addItemDecoration(dividerItemDecorationsb);
        recyclerViewsb.setAdapter(adapterSb);
    }

    private void armarReciclerCelda() {

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
    }

    private void mostrarSb() {

        Cursor datasb = conexion.showSb();

        SiloBolsa siloBolsa;

        while ((datasb.moveToNext())){

            siloBolsa = new SiloBolsa();
            siloBolsa.setIdAuto(datasb.getInt(0));
            siloBolsa.setId(datasb.getString(1));
            siloBolsa.setTipoGrano(datasb.getString(2));
            siloBolsa.setpHgrano(datasb.getDouble(3));
            siloBolsa.setLargoSB(datasb.getDouble(4));
            siloBolsa.setAnchoSB(datasb.getDouble(5));
            siloBolsa.setAlturaBase(datasb.getDouble(6));
            siloBolsa.setAlturaParabola(datasb.getDouble(7));
            siloBolsa.setMetrosCubicosSB(datasb.getDouble(8));
            siloBolsa.setToneladasSB(datasb.getDouble(9));

            Log.i(TAG,siloBolsa.toString());

            siloBolsas.add(siloBolsa);
        }
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
            celda.setTotaltons(formatearDecimales(data.getDouble(11),2));
            celdas.add(celda);
        }
    }

    @Override
    public Double formatearDecimales(Double numero, Integer numeroDecimales) {

        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }
}
