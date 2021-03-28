package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditPlayers extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);
        mlayout = (GridLayout) findViewById(R.id.myLayout);
        Button addItem = (Button) findViewById(R.id.addbutton);
        View additem = null;
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dnc = new DynamicClass(context);
                mlayout.addView(dnc.descriptionTextView(getApplicationContext(),"Player 1"),3);
                mlayout.addView(dnc.recievedQuantityEditText(getApplicationContext()),4);
            }
        });
    }
     */
        setContentView(R.layout.activity_edit_players);

        super.onCreate(savedInstanceState);
        Button start = (Button) findViewById(R.id.startGame2);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EditPlayers.this, ScoreCard.class));
            }
        });

    }
}