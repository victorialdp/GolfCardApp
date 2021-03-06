package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class FinalScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalscore);

        Bundle results = this.getIntent().getExtras();
        ArrayList<Integer> scores = results.getIntegerArrayList("results");

        int[] playerOrder = new int[8];

       for(int i = 0; i < 8; ++i)
       {
           int maxScore = 0;
           int maxIndex = 0;

           for(int j = 0; j < 8; ++j)
           {
               int score = scores.get(j);
               if(score > maxScore)
               {
                   maxIndex = j;
                   maxScore = score;
               }
           }
           playerOrder[i] = maxIndex + 1;
           scores.set(maxIndex, -1000);
       }

       TextView winner = (TextView) findViewById(R.id.winnerTextView);
       String winnerString = "Player " + playerOrder[0] + " is the winner!";
       winner.setText(winnerString);

    }
}