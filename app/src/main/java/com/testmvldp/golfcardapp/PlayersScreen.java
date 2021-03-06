package com.testmvldp.golfcardapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PlayersScreen extends AppCompatActivity {
    private GridLayout mlayout;
    Button addPlayers;
    DynamicClass dnc;
    Context context;

    ArrayList<String> names = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);

        initializeArray();

        EditText player1Name = (EditText) findViewById(R.id.player1Name);
        EditText player2Name = (EditText) findViewById(R.id.player2Name);

        TextWatcher p1 = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable)
            {
                names.set(0, player1Name.getText().toString());
            }
        };

        TextWatcher p2 = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable)
            {
                names.set(1, player2Name.getText().toString());
            }
        };

        player1Name.addTextChangedListener(p1);
        player2Name.addTextChangedListener(p2);

        Button start = (Button) findViewById(R.id.startGame);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent test = new Intent (v.getContext(), ScoreCard.class);
                Bundle playerNames = new Bundle();

                playerNames.putStringArrayList("playerNames", names);
                test.putExtras(playerNames);
                startActivity(test);
            }
        });

    }

    public void initializeArray()
    {
        for(int i = 0; i < 8; ++i)
        {
            names.add("");
        }
    }


}
