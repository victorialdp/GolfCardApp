package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditPlayers extends AppCompatActivity {

    LinearLayout leftSide;
    LinearLayout rightSide;
    LinearLayout container;

    int numHoles;
    int numPlayers;
    ArrayList<EditText> playerNames;
    ArrayList<String> inputs;

    EditText p1name;
    EditText p2name;
    TextView p1Text;
    Button start;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_players);


        Bundle data = this.getIntent().getExtras();
        numHoles = data.getInt("holes");
        numPlayers = data.getInt("players");
        inputs = new ArrayList<>(numPlayers);
        playerNames = new ArrayList<>(numPlayers);

        initilizeArray();

        leftSide = (LinearLayout) findViewById(R.id.leftside) ;
        rightSide = (LinearLayout) findViewById(R.id.rightside);
        container = (LinearLayout) findViewById(R.id.layoutHolder);

        if(numPlayers > 6)
        {
            FrameLayout.LayoutParams temp = (FrameLayout.LayoutParams) container.getLayoutParams();
            temp.gravity = Gravity.CENTER_HORIZONTAL;
            container.setLayoutParams(temp);
        }
        else
        {
            FrameLayout.LayoutParams temp = (FrameLayout.LayoutParams) container.getLayoutParams();
            temp.gravity = Gravity.CENTER;
            container.setLayoutParams(temp);
        }


        p1Text = (TextView) findViewById(R.id.p1text);
        p1name = (EditText) findViewById(R.id.player1Name);
        p2name = (EditText) findViewById(R.id.player2Name);

        playerNames.add(0, p1name);
        playerNames.add(1, p2name);
        start = (Button) findViewById(R.id.startGame2);

        newMake(numPlayers);


        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                for(int i = 0; i < numPlayers; ++i)
                {
                    String name = playerNames.get(i).getText().toString();
                    name = name.replaceAll("\\s+", "");

                    if(name.equals(""))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Player " + i+1 + " name is blank!", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                }

                for(int i = 0; i < playerNames.size(); ++i)
                {
                    inputs.set(i, playerNames.get(i).getText().toString());
                }

                Intent test = new Intent (v.getContext(), ScoreCard.class);
                Bundle data = new Bundle();

                for(int i = 0; i < inputs.size(); ++i)
                {
                    Log.i("inputThing", inputs.get(i));
                }

                data.putStringArrayList("playerNames", inputs);
                data.putInt("holes", numHoles);
                data.putInt("players", numPlayers);
                test.putExtras(data);
                startActivity(test);
            }
        });

    }

    public void initilizeArray()
    {
        for(int i = 0; i < numPlayers; ++i)
        {
            inputs.add("");
        }
    }

    public void newMake(int numPlayers)
    {
        if(numPlayers == 2)
        {
            return;
        }

        for(int i = 3; i <= numPlayers; ++i)
        {
            TextView label = new TextView(getApplicationContext());
            label.setText("Player " + i + ":");
            label.setGravity(Gravity.CENTER);
            label.setBackgroundColor(Color.WHITE);
            label.setBackgroundResource(R.drawable.roundedbutton);
            Typeface font = ResourcesCompat.getFont(this, R.font.comfortaa);
            label.setTypeface(font);
            label.setTextColor(p1Text.getCurrentTextColor());
            label.setTextSize(20);

            leftSide.addView(label, p1Text.getLayoutParams());
        }

        for(int i = 2; i < numPlayers; ++i)
        {
            EditText input = new EditText(getApplicationContext());
            input.setTextSize(20);
            input.setHintTextColor(Color.GRAY);
            input.setGravity(Gravity.CENTER_VERTICAL);
            input.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            playerNames.add(i, input);

            String stringNumber  = "";
            switch(i)
            {
                case 2:
                    stringNumber = "Input player three";
                    break;
                case 3:
                    stringNumber = "Input player four";
                    break;
                case 4:
                    stringNumber = "Input player five";
                    break;
                case 5:
                    stringNumber = "Input player six";
                    break;
                case 6:
                    stringNumber ="Input player seven";
                    break;
                case 7:
                    stringNumber ="Input player eight";
                    break;
            }

            input.setHint(stringNumber);

        }

        stuff();
    }

    public void stuff()
    {
        for(int i = 0; i < playerNames.size(); ++i)
        {
            playerNames.get(i).setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            if(i != playerNames.size()-1)
            {
                int finalI = i;
                playerNames.get(i).setOnKeyListener(new View.OnKeyListener()
                {
                    @Override
                    public boolean onKey(View view, int j, KeyEvent keyEvent)
                    {
                        if(j == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN)
                        {
                            playerNames.get(finalI).requestFocus();
                            return true;
                        }
                        return false;
                    }
                });
            }
            else {
                playerNames.get(i).setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int j, KeyEvent keyEvent) {
                        if (j == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                            start.callOnClick();

                            return true;
                        }
                        return false;
                    }
                });
            }

        }

        for(int i = 2; i < numPlayers; ++i)
        {
            rightSide.addView(playerNames.get(i), p1name.getLayoutParams());
        }
    }
}