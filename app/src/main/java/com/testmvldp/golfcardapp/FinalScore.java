package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Log.i("scores size", "" + scores.size());
        Log.i("inputThing", "" + names.size());


        for(int i = 0; i < numPlayers; ++i)
        {
            if(scores.get(i) == 0)
            {
                copy.add(100000);
                scores.set(i, 10000);
            }
            else
            {
                copy.add(scores.get(i));
            }

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

       int first = playerOrder[0]-1;
       int firstScore = scores.get(first);

       String tieGame = names.get(first);
       for(int i = 0; i < scores.size(); ++i)
       {
           if(scores.get(i) == firstScore && i != first)
           {
               tieGame += " + " + names.get(i);
           }
       }
       tieGame += " Wins!";

        TextView winner = (TextView) findViewById(R.id.winnerTextView);

       if(!tieGame.equals(names.get(first) + " Wins!"))//If tie
       {
           winner.setText(tieGame);
       }
       else
       {
           String winnerString = names.get(playerOrder[0]-1) + " is the winner!";
           winner.setText(winnerString);
       }

        TextView winnerScore = (TextView) findViewById(R.id.totalamt);
        String winscore = " " + scores.get(first) + " ";
        winnerScore.setText(winscore);

        Button again = (Button) findViewById(R.id.homeFromRules);

        again.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent beggining = new Intent(v.getContext(), PlayersScreen.class);
                startActivity(beggining);
            }

        });
    }
}