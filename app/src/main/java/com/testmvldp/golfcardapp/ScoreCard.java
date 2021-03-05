package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class ScoreCard extends AppCompatActivity {

    private EditText p1H1;
    private EditText p1H2;
    private EditText p1H3;
    private TextView p1Total;

    private EditText p2H1;
    private EditText p2H2;
    private EditText p2H3;
    private TextView p2Total;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        p1H1 = (EditText) findViewById(R.id.Player1Hole1_EditText);
        p1H2 = (EditText) findViewById(R.id.Player1Hole2_EditText);
        p1H3 = (EditText) findViewById(R.id.Player1Hole3_EditText);
        p1Total = (TextView) findViewById(R.id.Player1RT_TextView);

        p2H1 = (EditText) findViewById(R.id.Player2Hole1_EditText);
        p2H2 = (EditText) findViewById(R.id.Player2Hole2_EditText);
        p2H3 = (EditText) findViewById(R.id.Player2Hole3_EditText);
        p2Total = (TextView) findViewById(R.id.Player2RT_TextView);

        TextWatcher p1 = new TextWatcher()
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
                String[] holeStrings = new String[3];
                int[] holeInts = new int[3];

                holeStrings[0] = p1H1.getText().toString();
                holeStrings[1] = p1H2.getText().toString();
                holeStrings[2] = p1H3.getText().toString();

                int total = 0;

                for(int i = 0; i < 3; ++i)
                {
                    if(holeStrings[i].equals(""))
                    {
                        holeInts[i] = 0;
                    }
                    else
                    {
                        holeInts[i] = Integer.valueOf(holeStrings[i]);
                    }

                    total += holeInts[i];
                }

                p1Total.setText(Integer.toString(total));
            }
        };

        TextWatcher p2 = new TextWatcher()
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
                String[] holeStrings = new String[3];
                int[] holeInts = new int[3];

                holeStrings[0] = p2H1.getText().toString();
                holeStrings[1] = p2H2.getText().toString();
                holeStrings[2] = p2H3.getText().toString();

                int total = 0;

                for(int i = 0; i < 3; ++i)
                {
                    if(holeStrings[i].equals(""))
                    {
                        holeInts[i] = 0;
                    }
                    else
                    {
                        holeInts[i] = Integer.valueOf(holeStrings[i]);
                    }

                    total += holeInts[i];
                }

                p2Total.setText(Integer.toString(total));
            }
        };

        p1H1.addTextChangedListener(p1);
        p1H2.addTextChangedListener(p1);
        p1H3.addTextChangedListener(p1);

        p2H1.addTextChangedListener(p2);
        p2H2.addTextChangedListener(p2);
        p2H3.addTextChangedListener(p2);

    }


}