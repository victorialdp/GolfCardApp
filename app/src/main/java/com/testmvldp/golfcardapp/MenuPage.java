package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page);
        Bundle results = this.getIntent().getExtras();
        int numHoles = results.getInt("holes");
        int numPlayers = results.getInt("players");
        ArrayList<String> names = results.getStringArrayList("names");
        Button editPlayers = (Button) findViewById(R.id.button2);
        editPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent values = new Intent(view.getContext(), EditPlayers.class);
                Bundle data = new Bundle();
                data.putInt("holes", numHoles);
                data.putInt("players", numPlayers);
                data.putStringArrayList("names", names);
                values.putExtras(data);
                startActivity(values);
            }
        });

        Button rules = (Button) findViewById(R.id.button5);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent values = new Intent(view.getContext(), Rules.class);
                Bundle data = new Bundle();
                data.putInt("holes", numHoles);
                data.putInt("players", numPlayers);
                values.putExtras(data);
                startActivity(values);
            }
        });

        Button restart = (Button) findViewById(R.id.button6);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(view.getContext(), PlayersScreen.class);
                startActivity(edit);
            }
        });

    }
}
