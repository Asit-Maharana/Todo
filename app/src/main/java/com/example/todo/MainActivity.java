package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // on below line we are calling handler to run a task
        // for specific time interval
        new Handler().postDelayed(() -> {
            // on below line we are
            // creating a new intent
            Intent i = new Intent(MainActivity.this, MainActivity2.class);

            // on below line we are
            // starting a new activity.
            startActivity(i);

            // on the below line we are finishing
            // our current activity.
            finish();
        }, 2000);
    }
}