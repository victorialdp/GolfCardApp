package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EditPlayers extends AppCompatActivity {

    LinearLayout leftSide;
    LinearLayout rightSide;

    int numHoles;
    int numPlayers;
    ArrayList<String> inputs;

    EditText p1name;
    EditText p2name;
    TextView p1Text;

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

        Bundle data = this.getIntent().getExtras();
        numHoles = data.getInt("holes");
        numPlayers = data.getInt("players");
        inputs = new ArrayList<>(numPlayers);

        leftSide = (LinearLayout) findViewById(R.id.leftside) ;
        rightSide = (LinearLayout) findViewById(R.id.rightside);

        p1Text = (TextView) findViewById(R.id.p1text);
        p1name = (EditText) findViewById(R.id.player1Name);
        p2name = (EditText) findViewById(R.id.player2Name);

        p1name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                inputs.add(0, p1name.getText().toString());
            }
        });

        p2name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                inputs.add(1, p2name.getText().toString());
            }
        });
        makeLayout(numPlayers);



        Button start = (Button) findViewById(R.id.startGame2);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent test = new Intent (v.getContext(), ScoreCard.class);
                Bundle data = new Bundle();

//                for(int i = 0; i < inputs.size(); ++i)
//                {
//                    System.out.println("INPUT " + i + " " + inputs.get(i));
//                }

                data.putStringArrayList("playerNames", inputs);
                data.putInt("holes", numHoles);
                test.putExtras(data);
                startActivity(test);
            }
        });

    }

    public void makeLayout(int numPlayers)
    {
        if(numPlayers == 2)
        {
            return;
        }

        for(int i = 3; i <= numPlayers; ++i)
        {
           TextView label = new TextView(getApplicationContext());
           label.setText("Player " + i);
           label.setGravity(Gravity.CENTER);
           leftSide.addView(label, p1Text.getLayoutParams());
        }

        for(int i = 2; i < numPlayers; ++i)
        {
            EditText input = new EditText(getApplicationContext());

            int index = i;
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable)
                {
                    inputs.set(index, input.getText().toString());
                }
            });

            rightSide.addView(input, p1name.getLayoutParams());
        }
    }
}