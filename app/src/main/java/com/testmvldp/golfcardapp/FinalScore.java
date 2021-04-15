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
        int numPlayers = results.getInt("players");
        ArrayList<String> names = results.getStringArrayList("names");

        int[] playerOrder = new int[numPlayers];
        ArrayList<Integer> copy = new ArrayList<>(numPlayers);

        for(int i = 0; i < numPlayers; ++i)
        {
            copy.add(scores.get(i));
        }

       for(int i = 0; i < numPlayers; ++i)
       {
           int minScore = 100000;
           int minIndex = 0;

           for(int j = 0; j < numPlayers; ++j)
           {
               int score = copy.get(j);
               if(score < minScore)
               {
                   minIndex = j;
                   minScore = score;
               }
           }
           playerOrder[i] = minIndex + 1;
           copy.set(minIndex, 1000);

       }

       TextView winner = (TextView) findViewById(R.id.winnerTextView);
       String winnerString = names.get(playerOrder[0]-1) + " is the winner!";
       winner.setText(winnerString);

        TextView winnerScore = (TextView) findViewById(R.id.totalamt);
        String winscore = " " + scores.get(playerOrder[0]-1) + " ";
        winnerScore.setText(winscore);


    }
}