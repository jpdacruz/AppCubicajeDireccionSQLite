package com.jpdacruz.appcubicajedireccion.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Celda;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogCeldaConoFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CargarCeldaFragment extends Fragment {

    //vars
    private static final String TAG = "CargarCeldaFragment";
    private ArrayList <Celda> listaCeldas;
    public Celda celda;
    private String mId, mTipoGrano;
    private double mPh,mAlturaCelda,mAnchoCelda, mLargoCelda,mAlturaGrano, mCono, mCopete, mVolumen, mVolumenGrano, mMc3, mToneladas;


    //widgets
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinnerGranos;
    private Button calcularCono, calcularCopete,calcularCelda;
    private EditText idCelda, ph,alturaCelda,anchoCelda,largoCelda,alturaGrano,cono,copete;

    public CargarCeldaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        celda = new Celda();

        View view = inflater.inflate(R.layout.fragment_cargar_celda, container, false);

        iniciarComponentes(view);

        crearSpinner(view);

        return view;
    }

    private void iniciarComponentes(View view) {

        calcularCono = view.findViewById(R.id.buttonCeldaCono);
        calcularCopete = view.findViewById(R.id.buttonCeldaCopete);
        calcularCelda = view.findViewById(R.id.buttonCalcularCelda);

        idCelda = view.findViewById(R.id.editTextIdCelda);
        ph = view.findViewById(R.id.editTextphCelda);
        alturaCelda = view.findViewById(R.id.editTextAlturaCelda);
        anchoCelda = view.findViewById(R.id.editTextAnchoCelda);
        largoCelda = view.findViewById(R.id.editTextLargoCelda);
        alturaGrano = view.findViewById(R.id.editTextCeldaAlturaGrano);
        cono = view.findViewById(R.id.editTextCeldaCono);
        copete = view.findViewById(R.id.editTextCeldaCopete);

        calcularCono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularVolumenCelda();

                toDialogCeldaCono();
            }
        });
    }

    private void calcularVolumenCelda() {

        mId = idCelda.getText().toString();
        mPh = Double.parseDouble(ph.getText().toString());
        mAlturaCelda = Double.parseDouble(alturaCelda.getText().toString());
        mAlturaGrano = Double.parseDouble(alturaGrano.getText().toString());
        mAnchoCelda = Double.parseDouble(anchoCelda.getText().toString());
        mLargoCelda = Double.parseDouble(largoCelda.getText().toString());
        mVolumenGrano = (mLargoCelda*mAnchoCelda*mAlturaGrano)*mPh;
        mVolumen = (mLargoCelda*mAnchoCelda*mAlturaCelda)* 0.8;
    }

    private void toDialogCeldaCono() {

        DialogCeldaConoFragment dialogCeldaConoFragment = new DialogCeldaConoFragment();
        dialogCeldaConoFragment.setTargetFragment(CargarCeldaFragment.this,1);
        dialogCeldaConoFragment.show(getFragmentManager(),TAG);
    }

    private void crearSpinner(View view) {

        spinnerGranos = view.findViewById(R.id.spinnercELDA);
        adapter = ArrayAdapter.createFromResource
                (getContext(),R.array.tipos_granos,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGranos.setAdapter(adapter);

        spinnerGranos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                celda.setTipoGrano(spinnerGranos.getSelectedItem().toString());
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
