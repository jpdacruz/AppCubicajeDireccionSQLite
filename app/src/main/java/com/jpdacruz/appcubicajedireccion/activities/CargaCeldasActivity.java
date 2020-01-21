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

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Silo;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogAlturaGranoFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoCeldaFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogCopeteSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogDiametroSiloFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogTamanioCeldaFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogTipoPHFragment;

public class CargaCeldasActivity extends AppCompatActivity implements

        DialogConoCeldaFragment.TomarDatosDialogListener,
        DialogTamanioCeldaFragment.TomarDatosDialogListener,
        DialogTipoPHFragment.TomarDatosDialogListener{

    //vars

    private static final String TAG = "CargaCeldasActivity";
    String idCelda,tipoGranoCelda,phGranoCeldaStringCelda,tamañoStringCelda, largoCeldaString, anchoCeldaString,
            alturaConoStringCelda, alturaCopeteStringCelda,
            alturaGranoStringCelda, tipoCeldaString, cubicajeStringCelda;
    int idAutoCelda;
    double  phGranoCelda,largoCelda, anchoCelda, tamañoCelda, alturaGrano,
            alturaConoCelda, alturaCopeteCelda, volumenCelda, cubicajeCelda;

    DataBaseHelper conexion;

    //widgets
    Button mCalcularTamañoCelda, mIngreseTipoPhCelda,
            mCalcularConoCelda, mCalcularCubicajeCelda, mIngresarCelda,
            mActualizarCelda, mEliminarCelda;
    TextView mToneladasCelda;
    TextInputLayout midCelda,mphGranoCeldaCelda,mTamañoCelda,mAlturaGranoCelda,mConoCelda, mCopeteCelda;

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
        mTamañoCelda = findViewById(R.id.textinputTamañoCelda);
        mAlturaGranoCelda = findViewById(R.id.textinputAlturaGranoCelda);
        mConoCelda = findViewById(R.id.textinputConoCelda);
        mCopeteCelda = findViewById(R.id.textinputCopeteCelda);
        mToneladasCelda = findViewById(R.id.textViewMostrarToneldasCeldas);
        mIngreseTipoPhCelda = findViewById(R.id.buttonTipoPHCelda);
        mCalcularTamañoCelda = findViewById(R.id.buttonCalcularTamañoCelda);
        mCalcularConoCelda = findViewById(R.id.buttonCalcularConoCelda);
        mCalcularCubicajeCelda = findViewById(R.id.botonCalcularCelda);
        mIngresarCelda = findViewById(R.id.botonIngresarCelda);
        mActualizarCelda = findViewById(R.id.botonUpdateCelda);
        mEliminarCelda = findViewById(R.id.botonDeleteCelda);
    }

    private void comprobarBundle() {

        Bundle bundleEnviado = getIntent().getExtras();
        Silo siloEnviado = null;

        if (bundleEnviado!=null){

            siloEnviado = (Silo) bundleEnviado.getSerializable("silo");

            idAutoCelda = siloEnviado.getIdAuto();
            idCelda = siloEnviado.getId();
            tipoGranoCelda = siloEnviado.getTipoGrano();
            phGranoCeldaStringCelda = String.valueOf(siloEnviado.getPhGrano());
            tamañoStringCelda = String.valueOf(siloEnviado.getDiametro());
            alturaGranoStringCelda = String.valueOf(siloEnviado.getAltoGrano());
            alturaConoStringCelda = String.valueOf(siloEnviado.getCono());
            alturaCopeteStringCelda = String.valueOf(siloEnviado.getCopete());
            cubicajeStringCelda = String.valueOf(siloEnviado.getTotaltons());

            setEditText(midCelda, idCelda);
            setEditText(mphGranoCeldaCelda, tipoGranoCelda + " " +  phGranoCeldaStringCelda);
            phGranoCelda = Double.parseDouble(phGranoCeldaStringCelda);
            setEditText(mTamañoCelda, tamañoStringCelda);
            setEditText(mAlturaGranoCelda, alturaGranoStringCelda);
            setEditText(mConoCelda,alturaConoStringCelda);
            setEditText(mCopeteCelda,alturaCopeteStringCelda);
            mToneladasCelda.setText("");
            mIngresarCelda.setVisibility(View.GONE);

        }else {

            mActualizarCelda.setVisibility(View.INVISIBLE);
            mEliminarCelda.setVisibility(View.GONE);
        }
    }

    private void iniciarEditTextListener() {

        mTamañoCelda.getEditText().addTextChangedListener(new TextWatcher() {
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

                if (!validarDatos()){

                    return;

                }else if  (cubicajeStringCelda.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Presione Calcular Para Continuar", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    insertaSiloDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mEliminarCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarSiloDB();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mActualizarCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validarDatos()){

                    return;

                }else if  (mToneladasCelda.getText().equals("")){

                    Toast.makeText(getApplicationContext(),
                            "Presione Calcular Para Continuar", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    actualizarSiloDB();

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

        mCalcularTamañoCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calcularTamaño();
            }
        });

        mCalcularConoCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarDialogCono();
            }
        });

        mCalcularCubicajeCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularcubicajeCelda();
            }
        });
    }

    private void actualizarSiloDB() {

        boolean insertSilo = conexion.upDateSilo(idAutoCelda, idCelda,tipoGranoCelda,phGranoCelda,tamañoCelda,
                alturaGrano,alturaConoCelda, alturaCopeteCelda, volumenCelda,cubicajeCelda);

        if (insertSilo){

            Toast.makeText(getApplicationContext(), "Silo actualizado",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Error en el ingreso de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void eliminarSiloDB() {

        Integer deleteRow = conexion.deleteSilo(idAutoCelda);

        if (deleteRow > 0 ){

            Toast.makeText(getApplicationContext(),"Silo eliminado",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"No se pudo eliminar",Toast.LENGTH_LONG).show();
        }
    }

    private void insertaSiloDB() {

        boolean insertSilo = conexion.addSilo(idCelda,tipoGranoCelda,phGranoCelda,tamañoCelda,
                alturaGrano,alturaConoCelda, alturaCopeteCelda, volumenCelda,cubicajeCelda);

        if (insertSilo){

            Toast.makeText(getApplicationContext(), "Datos insertados correctamente",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Error en el ingreso de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void iniciarDialogTipoPH() {

        DialogTipoPHFragment dialogF = new DialogTipoPHFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void calcularTamaño() {

        if (!validarProcesoDiametro()){

            return;
        }

        DialogTamanioCeldaFragment dialogF = new DialogTamanioCeldaFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    private void iniciarDialogCono() {

        if (!validadProcesoConoCopete()){

            return;
        }

        DialogConoCeldaFragment dialogF = new DialogConoCeldaFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    public void calcularcubicajeCelda() {

        if (!validarDatos()){

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

        cubicajeCelda = volumenCelda * phGranoCelda;

        cubicajeStringCelda = String.valueOf(cubicajeCelda);

        mToneladasCelda.setText(cubicajeStringCelda + " Toneladas");
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

            Toast.makeText(getApplicationContext(),"Debe seleccionar altura  y tipo de Cono",Toast.LENGTH_LONG).show();
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

    private boolean validarProcesoDiametro() {

        idCelda = getEditTextString(midCelda);
        phGranoCeldaStringCelda = getEditTextString(mphGranoCeldaCelda);

        if (idCelda.isEmpty()) {

            midCelda.setError("Dato requerido");
            return false;

        } else if (phGranoCeldaStringCelda.isEmpty()) {

            mphGranoCeldaCelda.setError("Dato requerido");
            return false;

        } else if (tipoGranoCelda.equals("SELECCIONA GRANO")) {

            Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR UN GRANO", Toast.LENGTH_LONG).show();
            return false;

        } else {

            midCelda.setError(null);
            mphGranoCeldaCelda.setError(null);
            return true;
        }
    }

    private boolean validadProcesoConoCopete() {

        tamañoStringCelda = getEditTextString(mTamañoCelda);

        if (tamañoStringCelda.isEmpty()){

            mTamañoCelda.setError("Dato requerido");
            return false;

        }else if (largoCelda < 1){

            mTamañoCelda.setError("Debe ingresar un tamaño celda valido");
            return false;

        }else if (anchoCelda < 1){

            mTamañoCelda.setError("Debe ingresar un tamaño celda valido");
            return false;

        }else {

            mTamañoCelda.setError("");
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
    public void enviarDatosDialogTamCelda(String largoCString, String anchoCString) {

        largoCeldaString = largoCString;
        anchoCeldaString = anchoCString;

        largoCelda = Double.parseDouble(largoCeldaString);
        anchoCelda = Double.parseDouble(anchoCeldaString);

        setEditText(mTamañoCelda, largoCeldaString + "mts * " + anchoCeldaString + "mts " );
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
