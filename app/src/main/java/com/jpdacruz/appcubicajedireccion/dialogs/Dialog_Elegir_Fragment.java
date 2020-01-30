package com.jpdacruz.appcubicajedireccion.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.activities.CargaCeldasActivity;
import com.jpdacruz.appcubicajedireccion.activities.CargaSiloBolsaActivity;
import com.jpdacruz.appcubicajedireccion.activities.CargaSilosActivity;
import com.jpdacruz.appcubicajedireccion.adapter.AdapterDialog;
import com.jpdacruz.appcubicajedireccion.clases.Estructura;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog_Elegir_Fragment extends DialogFragment {

    RecyclerView recyclerView;
    ArrayList<Estructura> estructura;

    public Dialog_Elegir_Fragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view;
        estructura = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogFragmentStyle);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.fragment_dialog_elegir, null);

        builder.setView(view);

        recyclerView = view.findViewById(R.id.recyclerViewDialog);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        AdapterDialog adapterDialog = new AdapterDialog(estructura);

        adapterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seleccion = estructura.get(recyclerView.getChildAdapterPosition(v)).getEstructura();

                if (seleccion.equals("Agregar silo")){

                    Intent intent = new Intent(getContext(),CargaSilosActivity.class);
                    startActivity(intent);
                }

                if (seleccion.equals("Agregar celda")){

                    Intent intent = new Intent(getContext(),CargaCeldasActivity.class);
                    startActivity(intent);
                }

                if (seleccion.equals("Agregar silo/bolsa")){

                    Intent intent = new Intent(getContext(), CargaSiloBolsaActivity.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapterDialog);

        return builder.create();
    }

    private void llenarLista() {

        estructura.add(new Estructura
                ("Agregar silo",R.drawable.entresilos));
        estructura.add(new Estructura
                ("Agregar celda",R.drawable.celda));
        estructura.add(new Estructura
                ("Agregar silo/bolsa",R.drawable.silobolsa));
    }
}
