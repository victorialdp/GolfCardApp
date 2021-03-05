package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

// splash page loads and goes to playersScreen
        Handler handler =new Handler ();
        handler.postDelayed(new Runnable() {

            public void run() {
                startActivity(new Intent( home.this,ScoreCard.class));
                finish();
            }
        }, 4000);


    }
}