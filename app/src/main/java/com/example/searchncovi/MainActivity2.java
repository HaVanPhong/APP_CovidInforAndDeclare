package com.example.searchncovi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressBarLoadApp;
    ImageView imgBackground;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        progressBarLoadApp = findViewById(R.id.progress_load_app);
        imgBackground = findViewById(R.id.img_covid);

        CountDownTimer countDownTimer = new CountDownTimer(3000, 25) {
            @Override
            public void onTick(long millisUntilFinished) {
                int current = progressBarLoadApp.getProgress();
//                int percent = random.nextInt((10 - 2) + 1) + 2;
//                progressBarLoadApp.setProgress(current + percent);
                progressBarLoadApp.setProgress(current + 1);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                progressBarLoadApp.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(), "Search Ncovi version 1.0", Toast.LENGTH_LONG).show();
            }
        };
        countDownTimer.start();

        imgBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Search Ncovi version 1.0", Toast.LENGTH_LONG).show();
            }
        });

    }

}