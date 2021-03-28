package com.testmvldp.golfcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class PlayersScreen extends AppCompatActivity {
    //private GridLayout mlayout;
    //Button addPlayers;
    //DynamicClass dnc;
    //Context context;
    //@Override

    private int holes = 0;
    private int players = 0;

    protected void onCreate(Bundle savedInstanceState) {

       // super.onCreate(savedInstanceState);
       /* mlayout = (GridLayout) findViewById(R.id.myLayout);
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
        setContentView(R.layout.players_screen);
        super.onCreate(savedInstanceState);
        Button start = (Button) findViewById(R.id.editPlayers);
        start.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent values = new Intent(v.getContext(), EditPlayers.class);
                Bundle data = new Bundle();

                data.putInt("holes", holes);
                data.putInt("players", players);
                values.putExtras(data);
                startActivity(values);
            }
        });

        EditText numHoles = (EditText) findViewById(R.id.holeNumber);
        EditText numPlayers = (EditText) findViewById(R.id.playerNumbers);

        numHoles.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        numPlayers.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        TextWatcher holesText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                holes = Integer.valueOf(numHoles.getText().toString());

            }
        };

        TextWatcher playersText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                players = Integer.valueOf(numPlayers.getText().toString());
            }
        };

        numHoles.addTextChangedListener(holesText);
        numPlayers.addTextChangedListener(playersText);


    }


}
