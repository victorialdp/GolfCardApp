package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);

        Button editPlayers = (Button) findViewById(R.id.button2);
        Button clear = (Button) findViewById(R.id.button5);
        Button restart = (Button) findViewById(R.id.button6);
        Button rules = (Button) findViewById(R.id.button7);

    }
}
