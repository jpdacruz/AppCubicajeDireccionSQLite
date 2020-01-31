package com.jpdacruz.appcubicajedireccion.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {

    //widgets
    TextInputLayout razon, cuit, asignacion;
    Button continuar, nueva;
    ProgressBar progressBar;

    //vars

    DataBaseHelper conexion;
    String razonString;
    String cuitString;
    String asignacionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conexion = new DataBaseHelper(this);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        iniciarComponentes();
        iniciarBotones();
        tomarSharedPref();
    }

    private void iniciarComponentes() {

        razon = findViewById(R.id.textinputRazonSocial);
        cuit = findViewById(R.id.textinputCuit);
        asignacion = findViewById(R.id.textinputAsig);
        continuar = findViewById(R.id.btnContinuar);
        nueva = findViewById(R.id.btnNueva);
    }

     private void iniciarBotones() {

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean continuar = validaDatos(v);

                if (continuar){

                    continuarAsignacion();

                    progressBar.setVisibility(View.VISIBLE);
                    intentDelayed();

                }else {

                    return;
                }
            }
        });

        nueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Snackbar snackbar = Snackbar.make(v,"Eliminar datos fiscalizacion anterior?",Snackbar.LENGTH_LONG)
                            .setAction("Continuar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    nuevaAsignacion();
                                }
                            });
                    snackbar.show();
            }
        });
    }

    private void continuarAsignacion() {

        tomarDatos();

        SharedPreferences sharedPreferences = getSharedPreferences("fisca", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("razon", razonString);
        editor.putString("cuit",cuitString);
        editor.putString("asignacion",asignacionString);
        editor.commit();
    }

    private void nuevaAsignacion() {

        vaciarBasesDatos();

        razon.getEditText().setText("");
        cuit.getEditText().setText("");
        asignacion.getEditText().setText("");
        razon.getEditText().setEnabled(true);
        cuit.getEditText().setEnabled(true);
        asignacion.getEditText().setEnabled(true);
    }

    private void vaciarBasesDatos() {

         conexion.onDelete();
    }


    private void tomarSharedPref() {

        SharedPreferences sharedPreferences = getSharedPreferences("fisca", Context.MODE_PRIVATE);

        razonString = sharedPreferences.getString("razon","");
        cuitString = sharedPreferences.getString("cuit","");
        asignacionString = sharedPreferences.getString("asignacion","");

        razon.getEditText().setText(razonString);
        cuit.getEditText().setText(cuitString);
        asignacion.getEditText().setText(asignacionString);

        if (!razonString.equals("")){

            razon.getEditText().setEnabled(false);
        }
        

        if (!cuitString.equals("")){

            cuit.getEditText().setEnabled(false);
        }

        if (!asignacionString.equals("")){

            asignacion.getEditText().setEnabled(false);
        }
    }

    private Boolean validaDatos(View v) {

        tomarDatos();

        if (razonString.isEmpty()){

            Snackbar.make(v,"Debe completar todos los datos",Snackbar.LENGTH_LONG).show();

            return false;

        }else if (cuitString.isEmpty()){

            Snackbar.make(v,"Debe completar todos los datos",Snackbar.LENGTH_LONG).show();

            return false;

        }else if (asignacionString.isEmpty()){

            Snackbar.make(v,"Debe completar todos los datos",Snackbar.LENGTH_LONG).show();

            return  false;

        }else {

            return true;
        }
    }

    private void tomarDatos() {

        razonString = razon.getEditText().getText().toString();
        cuitString = cuit.getEditText().getText().toString();
        asignacionString = asignacion.getEditText().getText().toString();

    }

    private void intentDelayed() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

}
