package com.jpdacruz.appcubicajedireccion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.Celda;
import com.jpdacruz.appcubicajedireccion.clases.SiloBolsa;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogConoCeldaFragment;
import com.jpdacruz.appcubicajedireccion.dialogs.DialogTipoPHFragment;

public class CargaSiloBolsaActivity extends AppCompatActivity implements

        DialogTipoPHFragment.TomarDatosDialogListener{

    //vars
    private static final String TAG = "CargaSiloBolsaActivity";

    String idSb,tipoGranSb,phGranoSbString, largoSbString, anchoSbString,alturaBaseSbString,
            alturaParabolSbString, cubicajeStringSb;

    int idAutoSb;

    double  phGranoSb,largoSb, anchoSb, alturaBaseSb, alturaParabolaSb,
            volumenaSb, cubicajeSb;

    DataBaseHelper conexion;

    //widgets
    Button mIngreseTipoPhSb, mCalcularCubicajeSb, mIngresarSb, mActualizarSb, mEliminarSb;

    TextView mToneladasSb;

    TextInputLayout mIdSb,mphGranoSb,mLargoSb, mAnchoSb, mAlturaBaseSb,mAlturaParabolaSb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_silobolsa);

        Toolbar toolbar = findViewById(R.id.toolbarSiloBolsa);
        setSupportActionBar(toolbar);

        tipoGranSb = "";
        conexion = new DataBaseHelper(this);
        iniciarWidgets();
        comprobarBundle();
        iniciarEditTextListener();
        iniciarBotonesListener();
    }

    private void iniciarWidgets() {

        mIdSb = findViewById(R.id.textinputIDsb);
        mphGranoSb = findViewById(R.id.textinputPHsb);
        mLargoSb = findViewById(R.id.textinputLargosb);
        mAnchoSb = findViewById(R.id.textinputAnchosb);
        mAlturaBaseSb = findViewById(R.id.textinputAlturaBasesb);
        mAlturaParabolaSb = findViewById(R.id.textinputAlturaParabolasb);
        mToneladasSb = findViewById(R.id.textViewMostrarToneldassb);
        mIngreseTipoPhSb = findViewById(R.id.buttonTipoPHsb);
        mCalcularCubicajeSb = findViewById(R.id.botonCalcularsb);
        mIngresarSb = findViewById(R.id.botonIngresarsb);
        mActualizarSb = findViewById(R.id.botonUpdatesb);
        mEliminarSb = findViewById(R.id.botonDeletesb);
    }

    private void comprobarBundle() {

        Bundle bundleEnviado = getIntent().getExtras();
        SiloBolsa sbEnviado = null;

        if (bundleEnviado!=null){

            sbEnviado = (SiloBolsa) bundleEnviado.getSerializable("sb");

            idAutoSb = sbEnviado.getIdAuto();
            idSb = sbEnviado.getId();
            tipoGranSb = sbEnviado.getTipoGrano();
            phGranoSbString = String.valueOf(sbEnviado.getpHgrano());
            largoSbString = String.valueOf(sbEnviado.getLargoSB());
            anchoSbString = String.valueOf(sbEnviado.getAnchoSB());
            alturaBaseSbString = String.valueOf(sbEnviado.getAlturaBase());
            alturaParabolSbString = String.valueOf(sbEnviado.getAlturaParabola());
            cubicajeStringSb = String.valueOf(sbEnviado.getToneladasSB());

            setEditText(mIdSb, idSb);
            setEditText(mphGranoSb, tipoGranSb + " " +  phGranoSbString);
            phGranoSb = Double.parseDouble(phGranoSbString);
            setEditText(mLargoSb, largoSbString);
            setEditText(mAnchoSb, anchoSbString);
            setEditText(mAlturaBaseSb, alturaBaseSbString);
            setEditText(mAlturaParabolaSb,alturaParabolSbString);
            mToneladasSb.setText("");
            mIngresarSb.setVisibility(View.GONE);

        }else {

            mActualizarSb.setVisibility(View.INVISIBLE);
            mEliminarSb.setVisibility(View.GONE);
        }
    }

    private void iniciarEditTextListener() {

        mLargoSb.getEditText().addTextChangedListener(new TextWatcher() {
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

        mAnchoSb.getEditText().addTextChangedListener(new TextWatcher() {
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

        mphGranoSb.getEditText().addTextChangedListener(new TextWatcher() {
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

        mAlturaBaseSb.getEditText().addTextChangedListener(new TextWatcher() {
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

        mAlturaParabolaSb.getEditText().addTextChangedListener(new TextWatcher() {
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

        mIngresarSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validarProcesoCalcular()){

                    return;

                }else if  (cubicajeStringSb.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Presione Calcular Para Continuar", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    insertaSbDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mEliminarSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarSbDB();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mActualizarSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validarProcesoCalcular()){

                    return;

                }else if  (mToneladasSb.getText().equals("")){

                    Toast.makeText(getApplicationContext(),
                            "Presione Calcular Para Continuar", Toast.LENGTH_LONG).show();
                    return;

                }else {

                    actualizarSbDB();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mIngreseTipoPhSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarDialogTipoPH();
            }
        });

        mCalcularCubicajeSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcularcubicajeSb();
            }
        });
    }

    private void actualizarSbDB() {

        boolean insertSb = conexion.upDateSb(idAutoSb, idSb,tipoGranSb,phGranoSb,largoSb, anchoSb,
                alturaBaseSb, alturaParabolaSb, volumenaSb,cubicajeSb);

        if (insertSb){

            Toast.makeText(getApplicationContext(), "SiloBolsa actualizado",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Error en el ingreso de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void eliminarSbDB() {

        Integer deleteRow = conexion.deleteSb(idAutoSb);

        if (deleteRow > 0 ){

            Toast.makeText(getApplicationContext(),"Silo Bolsa eliminado",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"No se pudo eliminar",Toast.LENGTH_LONG).show();
        }
    }

    private void insertaSbDB() {

        boolean insertSb = conexion.addSb(idSb,tipoGranSb,phGranoSb,largoSb, anchoSb,
                alturaBaseSb, alturaParabolaSb, volumenaSb,cubicajeSb);

        if (insertSb){

            String b = idSb +" " +tipoGranSb +" "+ phGranoSb +" "+ largoSb +" "+ anchoSb
                    +" "+ alturaBaseSb +" "+ alturaParabolaSb +" "+ volumenaSb +" "+cubicajeSb;

            Log.i(TAG,b);
            Toast.makeText(getApplicationContext(), "Datos insertados correctamente",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Error en el ingreso de datos", Toast.LENGTH_LONG).show();
        }
    }

    private void iniciarDialogTipoPH() {

        DialogTipoPHFragment dialogF = new DialogTipoPHFragment();
        dialogF.show(getSupportFragmentManager(),TAG);
    }

    public void calcularcubicajeSb() {

        if (!validarProcesoCalcular()){

            return;
        }

        largoSb = Double.parseDouble(largoSbString);
        anchoSb = Double.parseDouble(anchoSbString);
        alturaBaseSb = Double.parseDouble(alturaBaseSbString);
        alturaParabolaSb = Double.parseDouble(alturaParabolSbString);

        volumenaSb = (anchoSb * largoSb * alturaBaseSb) + (((anchoSb * largoSb * alturaParabolaSb ) * 2) / 3);

        cubicajeSb = volumenaSb * phGranoSb;

        cubicajeStringSb = String.valueOf(cubicajeSb);

        mToneladasSb.setText(cubicajeStringSb + " Toneladas");
    }

    private boolean validarProcesoCalcular() {

        idSb = getEditTextString(mIdSb);
        largoSbString = getEditTextString(mLargoSb);
        anchoSbString = getEditTextString(mAnchoSb);
        alturaBaseSbString = getEditTextString(mAlturaBaseSb);
        alturaParabolSbString = getEditTextString(mAlturaParabolaSb);

        if (idSb.isEmpty()) {

            mIdSb.setError("Dato requerido");
            return false;

        }else if (tipoGranSb.isEmpty()) {

            Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR UN GRANO", Toast.LENGTH_LONG).show();
            return false;

        }else if (tipoGranSb.equals("SELECCIONA GRANO")) {

            Toast.makeText(getApplicationContext(), "DEBE SELECCIONAR UN GRANO", Toast.LENGTH_LONG).show();
            return false;

        }else if (phGranoSbString.isEmpty()) {

            mphGranoSb.setError("Dato requerido");
            return false;

        }else if (largoSbString.isEmpty()){

            mLargoSb.setError("Dato requerido");
            return false;

        }else if (anchoSbString.isEmpty()){

            mAnchoSb.setError("Dato requerido");
            return false;

        }else if (alturaBaseSbString.isEmpty()){

            mAlturaBaseSb.setError("Dato requerido");
            return false;

        }else if(alturaParabolSbString.isEmpty()){

            mAlturaParabolaSb.setError("Dato requerido");
            return false;

        }else {

            mIdSb.setError(null);
            mphGranoSb.setError(null);
            mAlturaBaseSb.setError("");
            mAlturaParabolaSb.setError("");
            mLargoSb.setError("");
            mAnchoSb.setError("");
            return true;
        }
    }

    @Override
    public void enviarDatosDialogTipoPh(String tipo, String ph) {

        tipoGranSb = tipo;
        phGranoSbString = ph;

        setEditText(mphGranoSb, tipoGranSb + " " +  phGranoSbString);
        phGranoSb = Double.parseDouble(phGranoSbString);
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

        mToneladasSb .setText("");
        cubicajeStringSb = "";
    }
}
