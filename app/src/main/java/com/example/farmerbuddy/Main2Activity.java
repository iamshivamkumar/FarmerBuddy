package com.example.farmerbuddy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {
            Intent home = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(home);
            finish();
        }
    },SPLASH_TIME_OUT);

    }
}
