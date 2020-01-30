package com.jpdacruz.appcubicajedireccion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Celda;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoCeldaFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogTipoPHFragment;

public class CargaCeldasActivity extends AppCompatActivity implements

        DialogConoCeldaFragment.TomarDatosDialogListener,
        DialogTipoPHFragment.TomarDatosDialogListener{

    //vars
    private static final String TAG = "CargaCeldasActivity";

    String idCelda,tipoGranoCelda,phGranoCeldaStringCelda, largoCeldaString, anchoCeldaString,alturaGranoStringCelda,
            alturaConoStringCelda, alturaCopeteStringCelda, tipoCeldaString, cubicajeStringCelda;

    int idAutoCelda;

    double  phGranoCelda,largoCelda, anchoCelda, tamañoCelda, alturaGrano,
            alturaConoCelda, alturaCopeteCelda, volumenCelda, cubicajeCelda;

    DataBaseHelper conexion;

    //widgets
    Button mIngreseTipoPhCelda, mCalcularConoCelda, mCalcularCubicajeCelda, mIngresarCelda, mActualizarCelda, mEliminarCelda;

    TextView mToneladasCelda;

    TextInputLayout midCelda,mphGranoCeldaCelda,mLargoCelda, mAnchoCelda, mAlturaGranoCelda,mConoCelda, mCopeteCelda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_celdas);

        Toolbar toolbar = findViewById(R.id.toolbarCeldas);
        setSupportActionBar(toolbar);

        conexion = new DataBaseHelper(this);
        iniciarWidgets();
        comprobarBundle();
        iniciarEditTextListener();
        iniciarBotonesListener();
    }

    private void iniciarWidgets() {

        midCelda = findViewById(R.id.textinputIDCelda);
        mphGranoCeldaCelda = findViewById(R.id.textinputPHCelda);
        mLargoCelda = findViewById(R.id.textinputLargoCelda);
        mAnchoCelda = findViewById(R.id.textinputAnchoCelda);
        mAlturaGranoCelda = findViewById(R.id.textinputAlturaGranoCelda);
        mConoCelda = findViewById(R.id.textinputConoCelda);
        mCopeteCelda = findViewById(R.id.textinputCopeteCelda);
        mToneladasCelda = findViewById(R.id.textViewMostrarToneldasCeldas);
        mIngreseTipoPhCelda = findViewById(R.id.buttonTipoPHCelda);
        mCalcularConoCelda = findViewById(R.id.buttonCalcularConoCelda);
        mCalcularCubicajeCelda = findViewById(R.id.botonCalcularCelda);
        mIngresarCelda = findViewById(R.id.botonIngresarCelda);
        mActualizarCelda = findViewById(R.id.botonUpdateCelda);
        mEliminarCelda = findViewById(R.id.botonDeleteCelda);
    }

    private void comprobarBundle() {

        Bundle bundleEnviado = getIntent().getExtras();
        Celda celdaEnviada = null;

        if (bundleEnviado!=null){

            celdaEnviada = (Celda) bundleEnviado.getSerializable("celda");

            idAutoCelda = celdaEnviada.getIdAuto();
            idCelda = celdaEnviada.getId();
            tipoGranoCelda = celdaEnviada.getTipoGrano();
            phGranoCeldaStringCelda = String.valueOf(celdaEnviada.getPhGrano());
            largoCeldaString = String.valueOf(celdaEnviada.getLargoCelda());
            anchoCeldaString = String.valueOf(celdaEnviada.getAnchoCelda());
            alturaGranoStringCelda = String.valueOf(celdaEnviada.getAltoGrano());
            alturaConoStringCelda = String.valueOf(celdaEnviada.getCono());
            alturaCopeteStringCelda = String.valueOf(celdaEnviada.getCopete());
            tipoCeldaString = celdaEnviada.getTipoCelda();
            cubicajeStringCelda = String.valueOf(celdaEnviada.getTotaltons());

            setEditText(midCelda, idCelda);
            setEditText(mphGranoCeldaCelda, tipoGranoCelda + " " +  phGranoCeldaStringCelda);
            phGranoCelda = Double.parseDouble(phGranoCeldaStringCelda);
            setEditText(mLargoCelda, largoCeldaString);
            setEditText(mAnchoCelda, anchoCeldaString);
            setEditText(mAlturaGranoCelda, alturaGranoStringCelda);
            setEditText(mConoCelda,alturaConoStringCelda + "mts / " + tipoCeldaString);
            setEditText(mCopeteCelda,alturaCopeteStringCelda);
            mToneladasCelda.setText("");
            mIngresarCelda.setVisibility(View.GONE);

        }else {

            mActualizarCelda.setVisibility(View.INVISIBLE);
            mEliminarCelda.setVisibility(View.GONE);
        }
    }

    private void iniciarEditTextListener() {

        mLargoCelda.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetEditText(mConoCelda);
                resetEditText(mCopeteCelda);
                resetToneladas();
            }
        });

        mAnchoCelda.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                resetEditText(mConoCelda);
                resetEditText(mCopeteCelda);
                resetToneladas();
            }
        });

        mphGranoCeldaCelda.getEditText().addTextChangedListener(new TextWatcher() {
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

        mAlturaGranoCelda.getEditText().addTextChangedListener(new TextWatcher() {
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

        mConoCelda.getEditText().addTextChangedListener(new TextWatcher() {
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

        mCopeteCelda.getEditText().addTextChangedListener(new TextWatcher() {
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

        mIngresarCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarDatos(view)){

                    return;

                }else if  (cubicajeStringCelda.isEmpty()){

                    Snackbar.make(view,"Presiona CALCULAR para continuar",Snackbar.LENGTH_LONG).show();
                    return;

                }else {

                    insertaCeldaDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mEliminarCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarCeldaDB();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mActualizarCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validarDatos(v)){

                    return;

                }else if  (mToneladasCelda.getText().equals("")){

                    Snackbar.make(v,"Presiona CALCULAR para continuar",Snackbar.LENGTH_LONG).show();
                    return;

                }else {

                    actualizarCeldaDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mIngreseTipoPhCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarDialogTipoPH();
            }
        });

        mCalcularConoCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarDialogCono(view);
            }
        });

        mCalcularCubicajeCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularcubicajeCelda(v);
            }
        });
    }

    private void actualizarCeldaDB() {

        conexion.upDateCelda(idAutoCelda, idCelda,tipoGranoCelda,phGranoCelda,largoCelda, anchoCelda,
                alturaGrano, tipoCeldaString, alturaConoCelda, alturaCopeteCelda, volumenCelda,cubicajeCelda);
    }

    private void eliminarCeldaDB() {

        conexion.deleteCelda(idAutoCelda);
    }

    private void insertaCeldaDB() {

        conexion.addCelda(idCelda,tipoGranoCelda,phGranoCelda,largoCelda, anchoCelda,
                alturaGrano,tipoCeldaString, alturaConoCelda, alturaCopeteCelda, volumenCelda,cubicajeCelda);
    }

    private void iniciarDialogTipoPH() {

        DialogTipoPHFragment dialogF = new DialogTipoPHFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogCono(View view) {

        if (!validadProcesoConoCopete(view)){

            return;
        }

        DialogConoCeldaFragment dialogF = new DialogConoCeldaFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    public void calcularcubicajeCelda(View v) {

        if (!validarDatos(v)){

            return;
        }

        idCelda = getEditTextString(midCelda);

        alturaGranoStringCelda = getEditTextString(mAlturaGranoCelda);
        alturaGrano = Double.parseDouble(alturaGranoStringCelda);

        alturaCopeteStringCelda = getEditTextString(mCopeteCelda);
        alturaCopeteCelda = Double.parseDouble(alturaCopeteStringCelda);

        tamañoCelda = (largoCelda * anchoCelda * alturaGrano) + ((largoCelda*anchoCelda*alturaCopeteCelda/2));

        if (tipoCeldaString.equals("Galpon")){

            volumenCelda = tamañoCelda;

        }else if (tipoCeldaString.equals("Piso Trapesoidal")){

            volumenCelda = tamañoCelda + (largoCelda - (alturaConoCelda*0.577*2))
                    * (anchoCelda - (alturaConoCelda*0.577*2)) * alturaConoCelda;

        }else if (tipoCeldaString.equals("Piso Conico")){

            volumenCelda = tamañoCelda + ((anchoCelda*largoCelda*alturaConoCelda)/2);
        }

        cubicajeCelda = Math.round((volumenCelda * phGranoCelda) * 100 / 100.0);

        cubicajeStringCelda = String.valueOf(cubicajeCelda);

        mToneladasCelda.setText(cubicajeStringCelda + " Toneladas");
    }

    private boolean validarDatos(View view) {

        if (!validarProcesoDiametro(view)){

            return false;

        }else if (!validadProcesoConoCopete(view)){

            return false;

        }else if (!validarProcesoCalcular(view)) {

            return false;

        }else {

            return true;
        }
    }

    private boolean validarProcesoCalcular(View view) {

        alturaGranoStringCelda = getEditTextString(mAlturaGranoCelda);
        alturaConoStringCelda = getEditTextString(mConoCelda);
        alturaCopeteStringCelda = getEditTextString(mCopeteCelda);

        if (alturaGranoStringCelda.isEmpty()){

            mAlturaGranoCelda.setError("Dato requerido");
            return false;

        }else if(alturaConoStringCelda.isEmpty()){

            mConoCelda.setError("Dato requerido");
            return false;

        }else if (tipoCeldaString.equals("Elegir")) {

            Snackbar.make(view,"Debe seleccionar altura  y tipo de Cono",Snackbar.LENGTH_LONG).show();
            return false;

        }else if (alturaCopeteStringCelda.isEmpty()){

            mCopeteCelda.setError("Dato requerido");
            return false;
        }

        mAlturaGranoCelda.setError("");
        mConoCelda.setError("");
        mCopeteCelda.setError("");

        return true;
    }

    private boolean validarProcesoDiametro(View view) {

        idCelda = getEditTextString(midCelda);
        phGranoCeldaStringCelda = getEditTextString(mphGranoCeldaCelda);

        if (idCelda.isEmpty()) {

            midCelda.setError("Dato requerido");
            return false;

        } else if (phGranoCeldaStringCelda.isEmpty()) {

            mphGranoCeldaCelda.setError("Dato requerido");
            return false;

        } else if (tipoGranoCelda.equals("SELECCIONA GRANO")) {

            Snackbar.make(view,"DEBE SELECCIONAR UN GRANO",Snackbar.LENGTH_LONG).show();

            return false;

        } else {

            midCelda.setError(null);
            mphGranoCeldaCelda.setError(null);
            return true;
        }
    }

    private boolean validadProcesoConoCopete(View view) {

        largoCeldaString = getEditTextString(mLargoCelda);
        anchoCeldaString = getEditTextString(mAnchoCelda);

        if (largoCeldaString.isEmpty()){

            mLargoCelda.setError("Dato requerido");
            return false;

        }else if (anchoCeldaString.isEmpty()){

            mAnchoCelda.setError("Dato requerido");
            return false;

        }else {

            largoCelda = Double.parseDouble(largoCeldaString);
            anchoCelda = Double.parseDouble(anchoCeldaString);
            mLargoCelda.setError("");
            mAnchoCelda.setError("");
            return true;
        }
    }

    @Override
    public void enviarDatosDialogTipoPh(String tipo, String ph) {

        tipoGranoCelda = tipo;
        phGranoCeldaStringCelda = ph;

        setEditText(mphGranoCeldaCelda, tipoGranoCelda + " " +  phGranoCeldaStringCelda);
        phGranoCelda = Double.parseDouble(phGranoCeldaStringCelda);
    }

    @Override
    public void enviarDatosConoInfDialogCelda(String altuConoInfCelda, String tipoCelda) {

        alturaConoStringCelda = altuConoInfCelda;
        tipoCeldaString = tipoCelda;

        alturaConoCelda = Double.parseDouble(alturaConoStringCelda);

        setEditText(mConoCelda, alturaConoStringCelda + "mts / " + tipoCeldaString);
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

        mToneladasCelda .setText("");
        cubicajeStringCelda = "";
    }
}
