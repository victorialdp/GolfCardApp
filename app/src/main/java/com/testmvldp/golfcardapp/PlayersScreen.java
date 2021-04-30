package com.testmvldp.golfcardapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class PlayersScreen extends AppCompatActivity {


    private int holes = 0;
    private int players = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);
        Button start = (Button) findViewById(R.id.editPlayers);
        EditText numHoles = (EditText) findViewById(R.id.holeNumber);
        EditText numPlayers = (EditText) findViewById(R.id.playerNumbers);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String stringHoles = numHoles.getText().toString();
                String stringPlayers = numPlayers.getText().toString();

                if (stringPlayers.equals("") | stringHoles.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter a Value in all fields!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                else {
                    players = Integer.parseInt(stringPlayers);
                    holes = Integer.parseInt(stringHoles);
                }

                if (holes < 3 | holes > 16) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Holes must be between 3 and 16!", Toast.LENGTH_SHORT);
                    toast.show();
                    players = 0;
                    holes = 0;
                } else if (players < 2 | players > 8) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Players must be between 2 and 8!", Toast.LENGTH_SHORT);
                    toast.show();
                    players = 0;
                    holes = 0;
                } else if (players != 0 && holes != 0) {
                    Intent values = new Intent(v.getContext(), EditPlayers.class);
                    Bundle data = new Bundle();

                    data.putInt("holes", holes);
                    data.putInt("players", players);
                    values.putExtras(data);
                    startActivity(values);
                }
            }
        });

        numPlayers.setNextFocusDownId(R.id.holeNumber);
        numPlayers.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        numPlayers.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });

        numHoles.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        numPlayers.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });

        numPlayers.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    numHoles.requestFocus();
                    return true;
                }

                return false;
            }
        });

        numHoles.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                {
                    start.callOnClick();
                    return true;
                }

                return false;
            }
        });
    }


}
