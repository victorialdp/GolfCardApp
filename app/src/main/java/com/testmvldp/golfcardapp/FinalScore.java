package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
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
           int minScore = 100000;
           int minIndex = 0;

           for(int j = 0; j < 8; ++j)
           {
               int score = scores.get(j);
               if(score < minScore)
               {
                   minIndex = j;
                   minScore = score;
               }
           }
           playerOrder[i] = minIndex + 1;
           scores.set(minIndex, 1000);
       }

       TextView winner = (TextView) findViewById(R.id.winnerTextView);
       String winnerString = "Player " + playerOrder[0] + " is the winner!";
       winner.setText(winnerString);

       Button again = (Button) findViewById(R.id.playAgainButton);
       again.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent playerScreen = new Intent(view.getContext(), PlayersScreen.class);
               startActivity(playerScreen);
           }
       });

    }
}