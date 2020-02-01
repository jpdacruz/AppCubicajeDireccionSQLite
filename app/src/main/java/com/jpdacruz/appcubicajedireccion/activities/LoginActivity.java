package com.jpdacruz.appcubicajedireccion.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jpdacruz.appcubicajedireccion.MainActivity;
import com.jpdacruz.appcubicajedireccion.R;
import com.jpdacruz.appcubicajedireccion.clases.InterfaceGeneral;
import com.jpdacruz.appcubicajedireccion.database.DataBaseHelper;

public class LoginActivity extends AppCompatActivity implements InterfaceGeneral {

    //widgets
    TextInputLayout razon, asignacion;
    Button continuar, nueva, gps;
    TextView gpsSur, gpsOeste;
    ProgressBar progressBar;

    //vars
    LocationManager locationManager;
    LocationListener locationListener;
    DataBaseHelper conexion;
    String razonString, asignacionString, gpsSstring,gpsOstring;
    double gpsS, gpsO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conexion = new DataBaseHelper(this);

        iniciarGPS();
        iniciarComponentes();
        iniciarBotones();
        tomarSharedPref();
    }

    private void iniciarGPS() {

        int permisionCheck = ContextCompat.checkSelfPermission
                (this,Manifest.permission.ACCESS_FINE_LOCATION);

        if (permisionCheck == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }
    }

    private void iniciarComponentes() {

        razon = findViewById(R.id.textinputRazonSocial);
        asignacion = findViewById(R.id.textinputAsig);
        continuar = findViewById(R.id.btnContinuar);
        nueva = findViewById(R.id.btnNueva);
        gps = findViewById(R.id.buttonGPS);
        gpsSur = findViewById(R.id.textViewGPSsur);
        gpsOeste = findViewById(R.id.textViewGPSoeste);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
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

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager = (LocationManager) LoginActivity.this.getSystemService(Context.LOCATION_SERVICE);

                locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {

                        gpsS = formatearDecimales(location.getLatitude(),5);
                        gpsSur.setText("GPS sur: " + gpsS);
                        gpsO = formatearDecimales(location.getLongitude(),5);
                        gpsOeste.setText("GPS oeste: "+ gpsO);
                        locationManager.removeUpdates(locationListener);
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {}

                    public void onProviderEnabled(String provider) {}

                    public void onProviderDisabled(String provider) {}
                };

                int permisionCheck = ContextCompat.checkSelfPermission
                        (LoginActivity.this,Manifest.permission.ACCESS_FINE_LOCATION);

                locationManager.requestLocationUpdates
                        (LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        });
    }

    private void continuarAsignacion() {

        tomarDatos();

        SharedPreferences sharedPreferences = getSharedPreferences("fisca", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("razon", razonString);
        editor.putString("asignacion",asignacionString);
        editor.commit();
    }

    private void nuevaAsignacion() {

        vaciarBasesDatos();

        razon.getEditText().setText("");
        asignacion.getEditText().setText("");
        gpsSur.setText("GPS sur: ");
        gpsOeste.setText("GPS Oeste: ");
        razon.getEditText().setEnabled(true);
        asignacion.getEditText().setEnabled(true);
    }

    private void vaciarBasesDatos() {

         conexion.onDelete();
    }


    private void tomarSharedPref() {

        SharedPreferences sharedPreferences = getSharedPreferences("fisca", Context.MODE_PRIVATE);

        razonString = sharedPreferences.getString("razon","");
        asignacionString = sharedPreferences.getString("asignacion","");

        razon.getEditText().setText(razonString);
        asignacion.getEditText().setText(asignacionString);

        if (!razonString.equals("")){

            razon.getEditText().setEnabled(false);
            asignacion.getEditText().setEnabled(false);
        }
    }

    private Boolean validaDatos(View v) {

        tomarDatos();

        if (razonString.isEmpty()){

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

    @Override
    public Double formatearDecimales(Double numero, Integer numeroDecimales) {

        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);

    }
}
