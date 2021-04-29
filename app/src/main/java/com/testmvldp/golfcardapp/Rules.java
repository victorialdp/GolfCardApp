package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        Bundle results = this.getIntent().getExtras();
        int numHoles = results.getInt("holes");
        int numPlayers = results.getInt("players");
        Button back = (Button) findViewById(R.id.homeFromRules);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent values = new Intent(view.getContext(), MenuPage.class);
                Bundle data = new Bundle();
                data.putInt("holes", numHoles);
                data.putInt("players", numPlayers);
                values.putExtras(data);
                startActivity(values);
            }
        });
    }
}
