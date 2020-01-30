package com.jpdacruz.appcubicajedireccion.activities;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.os.Bundle;

=======
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jpdacruz.appcubicajedireccion.MainActivity;
>>>>>>> Stashed changes
import com.jpdacruz.appcubicajedireccion.R;

public class SplashActivity extends AppCompatActivity {

<<<<<<< Updated upstream
=======
    ProgressBar progressBar;


>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
<<<<<<< Updated upstream
=======

        progressBar = findViewById(R.id.progressBar);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
>>>>>>> Stashed changes
    }
}
