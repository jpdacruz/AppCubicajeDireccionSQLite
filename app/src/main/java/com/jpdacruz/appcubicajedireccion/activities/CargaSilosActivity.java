package com.jpdacruz.appcubicajedireccion.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.InterfaceGeneral;
import com.jpdacruz.appcubicajedireccion.clases.Silo;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogAlturaGranoFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogCopeteSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogDiametroSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogTipoPHFragment;

public class CargaSilosActivity extends AppCompatActivity implements
        DialogDiametroSiloFragment.TomarDatosDialogListener,
        DialogConoSiloFragment.TomarDatosDialogListener,
        DialogCopeteSiloFragment.TomarDatosDialogListener,
        DialogAlturaGranoFragment.TomarDatosDialogListener,
        DialogTipoPHFragment.TomarDatosDialogListener,
        InterfaceGeneral {

    //vars

    private static final String TAG = "CargaSilosActivity";
    String idSilo,tipoGrano,phGranoString,diametroSiloString,
            alturaConoSiloString, alturaCopeteSiloString,
            alturaGranoString, cubicajeSiloString;
    int idAuto;
    double  phGrano,diametroSilo, radio2, volumenCilindro, alturaGrano,
            alturaConoSilo,conoSilo,alturaCopeteSilo,
            copeteSilo, volumenSilo, cubicajeSilo;

    DataBaseHelper conexion;

    //widgets
    Button mCalcularDiametro, mCalcularAlturaGrano, mIngreseTipoPh,
            mCalcularCono, mCalcularCopete, mCalcularCubicaje, mIngresarSilo, mActualizarSilo, mEliminarSilo;
    TextView mToneladasSilo;
    TextInputLayout mIdSilo,mPhGrano,mDiametro,mAlturaGrano,mCono, mCopete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_silos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conexion = new DataBaseHelper(this);
        iniciarWidgets();
        comprobarBundle();
        iniciarEditTextListener();
        iniciarBotonesListener();
    }

    private void iniciarWidgets() {

        mIdSilo = findViewById(R.id.textinputIDSilo);
        mPhGrano = findViewById(R.id.textinputPHSilo);
        mDiametro = findViewById(R.id.textinputDiametroSilo);
        mAlturaGrano = findViewById(R.id.textinputAlturaGrano);
        mCono = findViewById(R.id.textinputConoSilo);
        mCopete = findViewById(R.id.textinputCopeteSilo);
        mToneladasSilo = findViewById(R.id.textViewMostrarToneldas);
        mIngreseTipoPh = findViewById(R.id.buttonTipoPH);
        mCalcularDiametro = findViewById(R.id.buttonCalcularDiametro);
        mCalcularAlturaGrano = findViewById(R.id.buttonCalcularAlturaGrano);
        mCalcularCono = findViewById(R.id.buttonCalcularCono);
        mCalcularCopete = findViewById(R.id.buttonCalcularCopete);
        mCalcularCubicaje = findViewById(R.id.botonCalcularSilo);
        mIngresarSilo = findViewById(R.id.botonIngresarSilo);
        mActualizarSilo = findViewById(R.id.botonUpdate);
        mEliminarSilo = findViewById(R.id.botonDelete);
    }

    private void comprobarBundle() {

        Bundle bundleEnviado = getIntent().getExtras();
        Silo siloEnviado = null;

        if (bundleEnviado!=null){

            siloEnviado = (Silo) bundleEnviado.getSerializable("silo");

            idAuto = siloEnviado.getIdAuto();
            idSilo = siloEnviado.getId();
            tipoGrano = siloEnviado.getTipoGrano();
            phGranoString = String.valueOf(siloEnviado.getPhGrano());
            diametroSiloString = String.valueOf(siloEnviado.getDiametro());
            alturaGranoString = String.valueOf(siloEnviado.getAltoGrano());
            alturaConoSiloString = String.valueOf(siloEnviado.getCono());
            alturaCopeteSiloString = String.valueOf(siloEnviado.getCopete());

            setEditText(mIdSilo, idSilo);
            setEditText(mPhGrano, tipoGrano + " " +  phGranoString);
            phGrano = Double.parseDouble(phGranoString);
            setEditText(mDiametro, diametroSiloString);
            setEditText(mAlturaGrano, alturaGranoString);
            setEditText(mCono,alturaConoSiloString);
            setEditText(mCopete,alturaCopeteSiloString);
            mToneladasSilo.setText("");
            mIngresarSilo.setVisibility(View.GONE);

        }else {

            mActualizarSilo.setVisibility(View.INVISIBLE);
            mEliminarSilo.setVisibility(View.GONE);
        }
    }

    private void iniciarEditTextListener() {

        mDiametro.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetEditText(mCono);
                resetEditText(mCopete);
                resetToneladas();
            }
        });

        mPhGrano.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetToneladas();
            }
        });

        mAlturaGrano.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetToneladas();
            }
        });

        mCono.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetToneladas();
            }
        });

        mCopete.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetToneladas();
            }
        });
    }

    private void iniciarBotonesListener() {

        mIngresarSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarDatos(view)){

                    return;

                }else if  (cubicajeSiloString.isEmpty()){

                    Snackbar.make(view,"Presiona CALCULAR para continuar",Snackbar.LENGTH_LONG).show();

                }else {

                    insertaSiloDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mEliminarSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Eliminar silo?",Snackbar.LENGTH_LONG)
                        .setAction("Continuar", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                eliminarSiloDB();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }
                        }).show();
            }
        });

        mActualizarSilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validarDatos(v)){

                    return;

                }else if (mToneladasSilo.getText().equals("")){

                    Snackbar.make(v,"Presiona CALCULAR para continuar",Snackbar.LENGTH_LONG).show();

                    return;

                }else {

                    actualizarSiloDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mIngreseTipoPh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarDialogTipoPH();
            }
        });

        mCalcularDiametro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calcularDiametro(view);
            }
        });

        mCalcularAlturaGrano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarDialogAlturaGrano();
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

                calcularCubicajeSilo(v);
            }
        });
    }

    private void actualizarSiloDB() {

        conexion.upDateSilo(idAuto, idSilo,tipoGrano,phGrano,diametroSilo,
                alturaGrano,alturaConoSilo, alturaCopeteSilo, volumenSilo,cubicajeSilo);
    }

    private void eliminarSiloDB() {

        conexion.deleteSilo(idAuto);
    }

    private void insertaSiloDB() {

        conexion.addSilo(idSilo,tipoGrano,phGrano,diametroSilo,
                alturaGrano,alturaConoSilo, alturaCopeteSilo, volumenSilo,cubicajeSilo);
    }

    private void iniciarDialogTipoPH() {

        DialogTipoPHFragment dialogF = new DialogTipoPHFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void calcularDiametro(View view) {

        if (!validarProcesoDiametro(view)){

            return;
        }

        DialogDiametroSiloFragment dialogF = new DialogDiametroSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogAlturaGrano() {

        DialogAlturaGranoFragment dialogF = new DialogAlturaGranoFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogCono() {

        if (!validadProcesoConoCopete()){

            return;
        }

        DialogConoSiloFragment dialogF = new DialogConoSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogCopete() {

        if (!validadProcesoConoCopete()){

            return;
        }

        DialogCopeteSiloFragment dialogF = new DialogCopeteSiloFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void calcularCono() {

        if (!validadProcesoConoCopete()){

            return;
        }

        diametroSiloString = getEditTextString(mDiametro);
        diametroSilo = Double.parseDouble(diametroSiloString);
        alturaConoSilo = formatearDecimales(((diametroSilo/2)*0.7),2);
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
            alturaCopeteSilo = formatearDecimales(((diametroSilo/2)*0.5),2);
            alturaCopeteSiloString = String.valueOf(alturaCopeteSilo);
            setEditText(mCopete, alturaCopeteSiloString);

        }else {

            diametroSiloString = getEditTextString(mDiametro);
            diametroSilo = Double.parseDouble(diametroSiloString);
            alturaCopeteSilo = formatearDecimales(-((diametroSilo/2)*0.5),2);
            alturaCopeteSiloString = String.valueOf(alturaCopeteSilo);
            setEditText(mCopete, alturaCopeteSiloString);
        }
    }

    public void calcularCubicajeSilo(View v) {

        if (!validarDatos(v)){

            return;
        }

        idSilo = getEditTextString(mIdSilo);
        diametroSiloString = getEditTextString(mDiametro);
        alturaGranoString = getEditTextString(mAlturaGrano);
        alturaConoSiloString = getEditTextString(mCono);
        alturaCopeteSiloString = getEditTextString(mCopete);
        diametroSilo = Double.parseDouble(diametroSiloString);
        alturaGrano= Double.parseDouble(alturaGranoString);
        alturaConoSilo = Double.parseDouble(alturaConoSiloString);
        alturaCopeteSilo = Double.parseDouble(alturaCopeteSiloString);

        radio2 = (diametroSilo/2)*(diametroSilo/2);
        volumenCilindro = (Math.PI * radio2 *alturaGrano);

        if (alturaConoSilo == 0){

            conoSilo = 0;

        }else {

            conoSilo = (Math.PI * radio2 * alturaConoSilo)/3;
        }

        if (alturaCopeteSilo == 0){

            copeteSilo = 0;

        }else {

            copeteSilo = (Math.PI * radio2 * alturaCopeteSilo)/3;
        }

        volumenSilo = volumenCilindro + conoSilo + copeteSilo;
        cubicajeSilo = formatearDecimales((volumenSilo * phGrano),2);
        cubicajeSiloString = String.valueOf(cubicajeSilo);

        mToneladasSilo.setText(cubicajeSiloString + " Toneladas");
    }

    private boolean validarDatos(View v) {

        if (!validarProcesoDiametro(v)){

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

        alturaGranoString = getEditTextString(mAlturaGrano);
        alturaConoSiloString = getEditTextString(mCono);
        alturaCopeteSiloString = getEditTextString(mCopete);


        if (alturaGranoString.isEmpty()){

            mAlturaGrano.setError("Dato requerido");
            return false;

        }else if(alturaConoSiloString.isEmpty()){

            mCono.setError("Dato requerido");
            return false;

        }else if (alturaCopeteSiloString.isEmpty()){

            mCopete.setError("Dato requerido");
            return false;
        }

        mAlturaGrano.setError("");
        mCono.setError("");
        mCopete.setError("");

        return true;
    }

    private boolean validarProcesoDiametro(View view) {

        idSilo = getEditTextString(mIdSilo);
        phGranoString = getEditTextString(mPhGrano);

        if (idSilo.isEmpty()) {

            mIdSilo.setError("Dato requerido");
            return false;

        } else if (phGranoString.isEmpty()) {

            mPhGrano.setError("Dato requerido");
            return false;

        } else if (tipoGrano.equals("SELECCIONA GRANO")) {

            Snackbar.make(view,"DEBE SELECCIONAR UN GRANO",Snackbar.LENGTH_LONG).show();
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
    public void enviarDatosDialogTipoPh(String tipo, String ph) {

        tipoGrano = tipo;
        phGranoString = ph;

        setEditText(mPhGrano, tipoGrano + " " +  phGranoString);
        phGrano = Double.parseDouble(phGranoString);

    }

    @Override
    public void enviarDatosDialogDiametro(String diametro) {

        diametroSiloString = diametro;
        setEditText(mDiametro, diametroSiloString);
    }

    @Override
    public void enviarDatosDialogAlturaGrano(String alturaGrano) {

        alturaGranoString = alturaGrano;
        setEditText(mAlturaGrano, alturaGranoString);
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

            setEditText(mCopete, "0.00");
        }
   }

    private void setEditText(TextInputLayout editText, String string) {

        editText.getEditText().setText(string);
    }

    private String getEditTextString(TextInputLayout editText) {

        return editText.getEditText().getText().toString();

    }

    private void resetEditText(TextInputLayout editText){

        editText.getEditText().setText("");
    }

    private void resetToneladas() {

        mToneladasSilo.setText("");
        cubicajeSiloString = "";
    }

    @Override
    public Double formatearDecimales(Double numero, Integer numeroDecimales) {

        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }
}
