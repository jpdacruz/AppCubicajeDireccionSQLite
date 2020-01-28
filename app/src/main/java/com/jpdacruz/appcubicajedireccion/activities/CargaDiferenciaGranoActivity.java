package com.jpdacruz.appcubicajedireccion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.DiferenciaGrano;
import com.jpdacruz.appcubicajedireccion.clases.SiloSuma;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

public class CargaDiferenciaGranoActivity extends AppCompatActivity {

    private static final String TAG = "CargaDiferenciaGranoActivity";

    //vars

    String granoDifString, cubicajeString, afipString, diferenciaString, porcentajeString, masOmenosString;
    double cubicaje,afip,diferencia, porcentaje;
    Boolean validarCalcular;

    DataBaseHelper conexion;


    //widgets

    TextInputLayout mGranoDif,mCubicajeDif, mAfipDif, mDifereciaDif, mPorcentajeDif, mMasOmenosDif;
    Button mCalcularDif, mIngresarDif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_diferencia_grano);

        Toolbar toolbar = findViewById(R.id.toolbarDiferencia);
        setSupportActionBar(toolbar);

        conexion = new DataBaseHelper(this);
        validarCalcular = false;

        iniciarWidgets();
        comprobarBundle();
        iniciarEditTextListener();
        iniciarBotonesListener();
    }

    private void iniciarWidgets() {

        mGranoDif = findViewById(R.id.textinputGranoDif);
        mCubicajeDif = findViewById(R.id.textinputKGDif);
        mAfipDif = findViewById(R.id.textinputAFIPDif);
        mDifereciaDif = findViewById(R.id.textinputDifDif);
        mPorcentajeDif = findViewById(R.id.textinputPorcentajeDif);
        mMasOmenosDif = findViewById(R.id.textinputMasoMenosDif);

        mCalcularDif = findViewById(R.id.botonCalcularDif);
        mIngresarDif = findViewById(R.id.botonIngresarDif);
    }

    private void comprobarBundle() {

        Bundle bundleEnviado = getIntent().getExtras();

        SiloSuma datosGranoEnviado = (SiloSuma) bundleEnviado.getSerializable("diferencia");

        granoDifString = datosGranoEnviado.getTipoGrano();
        cubicaje = datosGranoEnviado.getCubicaje() * 1000;
        cubicajeString = String.valueOf(cubicaje);

        setEditText(mGranoDif, granoDifString);
        setEditText(mCubicajeDif, cubicajeString);
    }

    private void iniciarEditTextListener() {

        mAfipDif.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                setEditText(mDifereciaDif,"");
                setEditText(mPorcentajeDif,"");
                setEditText(mMasOmenosDif,"");
                validarCalcular = false;
            }
        });
    }

    private void iniciarBotonesListener() {

        mCalcularDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                afipString = getEditTextString(mAfipDif);

                if (afipString.isEmpty()){

                    mAfipDif.setError("Dato Obligatorio");
                    return;

                }else {

                    afip = Double.parseDouble(afipString);

                    diferencia = cubicaje - afip;
                    diferenciaString = String.valueOf(diferencia);
                    setEditText(mDifereciaDif, diferenciaString);

                    porcentaje = (((cubicaje - afip) / cubicaje ) * 100);
                    porcentajeString = String.valueOf(porcentaje);
                    setEditText(mPorcentajeDif,porcentajeString);

                    if (cubicaje > afip){

                        masOmenosString = "de mas";
                        setEditText(mMasOmenosDif, masOmenosString);
                        mMasOmenosDif.getEditText().setTextColor(getResources().getColor(R.color.error));

                        validarCalcular = true;

                    }else {

                        masOmenosString = "de menos";
                        setEditText(mMasOmenosDif,masOmenosString);
                        mMasOmenosDif.getEditText().setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        validarCalcular = true;
                    }
                }
            }
        });

        mIngresarDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarCalcular == false){

                    return;

                }else {

                    insertDiferenciaDB();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void insertDiferenciaDB() {

        boolean insertDif = conexion.addDifGrano(granoDifString,cubicaje,afip,diferencia,porcentaje,masOmenosString);

        if (insertDif){

            Toast.makeText(getApplicationContext(), "Datos insertados correctamente",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Error en el ingreso de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void setEditText(TextInputLayout editText, String string) {

        editText.getEditText().setText(string);
    }

    private String getEditTextString(TextInputLayout editText) {

        return editText.getEditText().getText().toString();

    }

}
