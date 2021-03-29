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

    protected void onCreate(Bundle savedInstanceState)
    {

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

        numHoles.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        numPlayers.setRawInputType(InputType.TYPE_CLASS_NUMBER);



        TextWatcher holesText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String value = numHoles.getText().toString();
                value = value.replaceAll("\\s+", "");

                for(int i = 0; i < value.length(); ++i)
                {
                    if(!Character.isDigit(value.charAt(0)))
                    {
                        if(i == 0)
                        {
                            numHoles.setText("");
                        }
                        else
                        {
                            numHoles.setText(value.substring(0, i));
                        }
                    }
                }

                if(value.equals(""))
                {

                }
                else
                {
                    holes = Integer.valueOf(numHoles.getText().toString());
                }
            }
        };

        TextWatcher playersText = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

                String value = numPlayers.getText().toString();
                value = value.replaceAll("\\s+", "");

                if(value.equals(""))
                {

                }
                else
                {
                    players = Integer.valueOf(value);
                }
            }
        };

        numHoles.addTextChangedListener(holesText);
        numPlayers.addTextChangedListener(playersText);


    }


}
