package com.jpdacruz.appcubicajedireccion.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Silo;

public class CargaSilosActivity extends AppCompatActivity {

    //vars
    private Silo silo;
    String idSilo,tipoGrano,phGranoString, largoChapasString, cantLargoChapasString,diametroSiloString,
            alturaConoSiloString, alturaCopeteSiloString, anchoChapaString, cantChapasAltoString, cubicajeSiloString;
    double phGrano,largoChapa,cantLargoChapas, diametroSilo, radioC, radio2, volumenCilindro, alturaGrano,
            alturaConoSilo,conoSilo, anchoChapa, cantChapasAlto, alturaCopeteSilo, copeteSilo, volumenSilo, cubicajeSilo;

    //widgets
    FloatingActionButton fabAceptar;
    Button mCalcularDiametro, mCalcularCono, mCalcularCopete, mCalcularCubicaje;
    TextView mToneladasSilo;
    TextInputLayout mIdSilo,mPhGrano,mLargoChapa,mChapasLargo,mDiametro,mAnchoChapa, mCantidadChapasAlto,mCono, mCopete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_silos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        silo = new Silo();

        iniciarWidgets();

        iniciarBotonesListener();

        crearSpiner();

    }

    private void iniciarWidgets() {

        mIdSilo = findViewById(R.id.textinputIDSilo);
        mPhGrano = findViewById(R.id.textinputPHSilo);
        mDiametro = findViewById(R.id.textinputDiametroSilo);
        mLargoChapa = findViewById(R.id.textinputLargoChapa);
        mChapasLargo = findViewById(R.id.textinputTotalChapas);
        mAnchoChapa = findViewById(R.id.textinputAnchoChapa);
        mCantidadChapasAlto = findViewById(R.id.textinputAlturaGranoSilo);
        mCono = findViewById(R.id.textinputConoSilo);
        mCopete = findViewById(R.id.textinputCopeteSilo);

        mToneladasSilo = findViewById(R.id.textViewMostrarToneldas);

        mCalcularDiametro = findViewById(R.id.buttonCalcularDiametro);
        mCalcularCono = findViewById(R.id.buttonCalcularCono);
        mCalcularCopete = findViewById(R.id.buttonCalcularCopete);
        mCalcularCubicaje = findViewById(R.id.botonCalcularSilo);

        fabAceptar = findViewById(R.id.fabGuardar);

    }

    private void iniciarBotonesListener() {

        fabAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mCalcularCono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListenerBtnCono(view);
            }
        });

        mCalcularCopete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListenerBtnCopete(view);
            }
        });
    }

    private void crearSpiner() {

        final Spinner spinner = findViewById(R.id.spinner_Silos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tipoGrano = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private boolean validarDatosDiametro(){

        idSilo = mIdSilo.getEditText().getText().toString().trim();
        phGranoString = mPhGrano.getEditText().getText().toString();
        largoChapasString = mLargoChapa.getEditText().getText().toString();
        cantLargoChapasString = mChapasLargo.getEditText().getText().toString();

        if (idSilo.isEmpty()) {

            mIdSilo.setError("Ingrese ID silo");
            return false;

        } else if (phGranoString.isEmpty()) {

            mPhGrano.setError("Ingrese PH del grano");
            return false;

        } else if (tipoGrano.equals("SELECCIONA")) {

            Toast.makeText(getApplicationContext(), "Debe elegir un Grano", Toast.LENGTH_LONG).show();
            return false;

        } else if (largoChapasString.isEmpty()) {

            mLargoChapa.setError("Ingrese largo chapa");
            return false;

        } else if (cantLargoChapasString.isEmpty()) {

            mChapasLargo.setError("Ingrese cantidad chapas largo");
            return false;

        }else {

            //resetea los datos de error
            mIdSilo.setError(null);
            mPhGrano.setError(null);
            mLargoChapa.setError(null);
            mChapasLargo.setError(null);

            //asinacion de las variables int y double
            phGrano = Double.parseDouble(phGranoString);
            largoChapa = Double.parseDouble(largoChapasString);
            cantLargoChapas = Double.parseDouble(cantLargoChapasString);

            return true;
        }
    }

    private boolean validarDatosVolumen(){

        anchoChapaString = mAnchoChapa.getEditText().getText().toString();
        cantChapasAltoString = mCantidadChapasAlto.getEditText().getText().toString();

        alturaConoSiloString = mCono.getEditText().getText().toString();
        if (alturaConoSiloString.isEmpty()){

            calcularCono();
        }

        alturaCopeteSiloString = mCopete.getEditText().getText().toString();
        if (alturaCopeteSiloString.isEmpty()){

            calcularCopete();
        }

        if (anchoChapaString.isEmpty()){

            mAnchoChapa.setError("Ingrese ancho chapa");
            return false;

        }else if (cantChapasAltoString.isEmpty()){

            mCantidadChapasAlto.setError("Ingrese cantidad chapas con grano");
            return false;
        }else{

            mCantidadChapasAlto.setError(null);
            mAnchoChapa.setError(null);

            anchoChapa = Double.parseDouble(anchoChapaString);
            cantChapasAlto = Double.parseDouble(cantChapasAltoString);

            return true;
        }
    }

    public void calcularDiametro(View view) {

        if (!validarDatosDiametro()){

            return;
        }

        diametroSilo = (largoChapa*cantLargoChapas)/Math.PI;
        radioC = diametroSilo/2;
        radio2 = radioC * radioC;
        diametroSiloString = String.valueOf(diametroSilo);
        mDiametro.getEditText().setText(diametroSiloString);
    }

    public void calcularCubicajeSilo(View view) {

        if (!validarDatosVolumen()) {

            return;
        }

        alturaGrano = anchoChapa * cantChapasAlto;
        volumenCilindro = (Math.PI * radio2 * alturaGrano);
        volumenSilo = volumenCilindro + conoSilo + copeteSilo;
        cubicajeSilo = volumenSilo*phGrano;
        cubicajeSiloString = String.valueOf(cubicajeSilo);
        mToneladasSilo.setText(cubicajeSiloString);
    }

    public void ListenerBtnCono(View view) {

        calcularCono();

    }

    public void ListenerBtnCopete(View view) {

        calcularCopete();
    }

    private void calcularCono() {

        alturaConoSiloString = mCono.getEditText().getText().toString();

        if (alturaConoSiloString.isEmpty()){

            alturaConoSilo = (diametroSilo/2)*0.7;
            alturaConoSiloString = String.valueOf(alturaConoSilo);
            mCono.getEditText().setText(alturaConoSiloString);
            conoSilo = (Math.PI * radio2 * alturaConoSilo)/3;

        } else {

            alturaConoSilo = Double.parseDouble(alturaConoSiloString);
        }
    }

    private void calcularCopete() {

        alturaCopeteSiloString = mCopete.getEditText().getText().toString();

        if (alturaCopeteSiloString.isEmpty()){

            alturaCopeteSilo = (diametroSilo/2)*0.5;
            alturaCopeteSiloString = String.valueOf(alturaCopeteSilo);
            mCopete.getEditText().setText(alturaCopeteSiloString);
            copeteSilo = (Math.PI * radio2 * alturaCopeteSilo)/3;

        } else {

            alturaCopeteSilo = Double.parseDouble(alturaCopeteSiloString);
        }
    }
}
