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
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogCopeteSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogDiametroSiloFragment;

public class CargaSilosActivity extends AppCompatActivity implements
        DialogDiametroSiloFragment.TomarDatosDialogListener,
        DialogConoSiloFragment.TomarDatosDialogListener,
        DialogCopeteSiloFragment.TomarDatosDialogListener {

    //vars
    private static final String TAG = "CargaSilosActivity";
    private Silo silo;
    String idSilo,tipoGrano,phGranoString,diametroSiloString,
            alturaConoSiloString, alturaCopeteSiloString, anchoChapaString,
            alturaGranoChapasString, cubicajeSiloString;
    double phGrano,diametroSilo, radio2, volumenCilindro, alturaGrano,
            alturaConoSilo,conoSilo, anchoChapa, alturaGranoChapas,
            alturaCopeteSilo, copeteSilo, volumenSilo, cubicajeSilo;

    //widgets
    Spinner spinner;
    FloatingActionButton fabAceptar;
    Button mCalcularDiametro, mCalcularCono, mCalcularCopete, mCalcularCubicaje;
    TextView mToneladasSilo;
    TextInputLayout mIdSilo,mPhGrano,mDiametro,mAnchoChapa, mAlturaGranoChapas,mCono, mCopete;


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


        mAnchoChapa = findViewById(R.id.textinputAnchoChapa);
        mAlturaGranoChapas = findViewById(R.id.textinputAlturaGranoSilo);
        mCono = findViewById(R.id.textinputConoSilo);
        mCopete = findViewById(R.id.textinputCopeteSilo);

        mToneladasSilo = findViewById(R.id.textViewMostrarToneldas);

        mCalcularDiametro = findViewById(R.id.buttonCalcularDiametro);
        mCalcularCono = findViewById(R.id.buttonCalcularCono);
        mCalcularCopete = findViewById(R.id.buttonCalcularCopete);
        mCalcularCubicaje = findViewById(R.id.botonCalcularSilo);

        fabAceptar = findViewById(R.id.fabGuardar);
    }

    private void crearSpiner() {

        spinner = findViewById(R.id.spinner_Silos);
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

    private void iniciarBotonesListener() {

        fabAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mCalcularDiametro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calcularDiametro();
            }
        });

        mCalcularCono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarDialogCono();
            }
        });

        mCalcularCopete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarDialogCopete();
            }
        });

        mCalcularCubicaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularCubicajeSilo();
            }
        });
    }

    private void iniciarDialogCopete() {

        if (!validadProcesoConoCopete()){

            return;
        }

        DialogCopeteSiloFragment dialogF = new DialogCopeteSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogCono() {

        if (!validadProcesoConoCopete()){

            return;
        }

        DialogConoSiloFragment dialogF = new DialogConoSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void calcularDiametro() {

        if (!validarProcesoDiametro()){

            return;
        }

        DialogDiametroSiloFragment dialogF = new DialogDiametroSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);

    }

    private void calcularCono() {

        if (!validadProcesoConoCopete()){

            return;
        }

        diametroSiloString = getEditTextString(mDiametro);
        diametroSilo = Double.parseDouble(diametroSiloString);
        alturaConoSilo = Math.round(((diametroSilo/2)*0.7)*100)/100.0;
        alturaConoSiloString = String.valueOf(alturaConoSilo);
        setEditText(mCono,alturaConoSiloString);
    }

    private void calcularCopete(boolean b) {

        if (!validadProcesoConoCopete()){

            return;
        }

        if (b) {

            diametroSiloString = getEditTextString(mDiametro);
            diametroSilo = Double.parseDouble(diametroSiloString);
            alturaCopeteSilo = Math.round(((diametroSilo / 2) * 0.5) * 100) / 100.0;
            alturaCopeteSiloString = String.valueOf(alturaCopeteSilo);
            setEditText(mCopete, alturaCopeteSiloString);

        }else {

            diametroSiloString = getEditTextString(mDiametro);
            diametroSilo = Double.parseDouble(diametroSiloString);
            alturaCopeteSilo = -((diametroSilo /2) *0.5);
            alturaCopeteSiloString = String.valueOf(alturaCopeteSilo);
            setEditText(mCopete, alturaCopeteSiloString);
        }
    }

    public void calcularCubicajeSilo() {

        if (!validarDatos()){

            return;
        }

        Toast.makeText(getApplicationContext(),"HOLA",Toast.LENGTH_LONG).show();



    }

    private boolean validarDatos() {

        if (!validarProcesoDiametro()){

            return false;

        }else if (!validadProcesoConoCopete()){

            return false;

        }else if (!validarProcesoCalcular()) {

            return false;

        }else {

            return true;
        }
    }

    private boolean validarProcesoCalcular() {

        anchoChapaString = getEditTextString(mAnchoChapa);
        alturaGranoChapasString = getEditTextString(mAlturaGranoChapas);
        alturaConoSiloString = getEditTextString(mCono);
        alturaCopeteSiloString = getEditTextString(mCopete);

        if (anchoChapaString.isEmpty()){

            mAnchoChapa.setError("Dato requerido");
            return false;

        }else if (alturaGranoChapasString.isEmpty()){

            mAlturaGranoChapas.setError("");
            return false;

        }else if(alturaConoSiloString.isEmpty()){

            mCono.setError("Dato requerido");
            return false;

        }else if (alturaCopeteSiloString.isEmpty()){

            mCopete.setError("Dato requerido");
            return false;
        }

        return true;
    }

    private boolean validarProcesoDiametro() {

        idSilo = getEditTextString(mIdSilo);
        phGranoString = getEditTextString(mPhGrano);

        if (idSilo.isEmpty()) {

            mIdSilo.setError("Dato requerido");
            return false;

        } else if (phGranoString.isEmpty()) {

            mPhGrano.setError("Dato requerido");
            return false;

        } else if (tipoGrano.equals("SELECCIONA")) {

            Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR UN GRANO", Toast.LENGTH_LONG).show();
            return false;

        } else {

            mIdSilo.setError(null);
            mPhGrano.setError(null);
            return true;
        }
    }

    private boolean validadProcesoConoCopete() {

        diametroSiloString = getEditTextString(mDiametro);

        if (diametroSiloString.isEmpty()){

            mDiametro.setError("Dato requerido");
            return false;

        }else {

            mDiametro.setError("");
            return true;
        }
    }

    @Override
    public void enviarDatosDialogDiametro(String diametro) {

        diametroSiloString = diametro;
        setEditText(mDiametro, diametroSiloString);
    }

    @Override
    public void enviarDatosDialogCono(Boolean bool) {

        if (bool){

            calcularCono();

        }else {

           setEditText(mCono, "0.00");
        }
    }

    @Override
    public void enviarDatosDialogCopete(String string) {

        if (string.equals("p")){

            calcularCopete(true);

        }else if (string.equals("n")){

            calcularCopete(false);

        }else {

            setEditText(mCopete, "0,00");
        }
   }

    private void setEditText(TextInputLayout editText, String string) {

        editText.getEditText().setText(string);
    }

    private void resetEditText(TextInputLayout editText) {

        editText.getEditText().setText("");
    }

    private String getEditTextString(TextInputLayout editText) {

        return editText.getEditText().getText().toString();

    }
}
