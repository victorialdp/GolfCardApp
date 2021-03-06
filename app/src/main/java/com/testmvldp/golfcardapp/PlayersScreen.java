package com.testmvldp.golfcardapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class PlayersScreen extends AppCompatActivity {
    private GridLayout mlayout;
    Button addPlayers;
    DynamicClass dnc;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);

        Button start = (Button) findViewById(R.id.startGame);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent test = new Intent (v.getContext(), ScoreCard.class);
                startActivity(test);
            }
        });

    }
}
