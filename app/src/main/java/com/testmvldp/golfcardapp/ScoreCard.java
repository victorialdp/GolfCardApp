package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.ImageButton;
import java.util.ArrayList;

public class ScoreCard extends AppCompatActivity {

    private ImageButton goRight;
    private ImageButton goLeft;

    private int currentPage = 1;
    private int numHoles;
    private int numPlayers;


    private ArrayList<EditText> holes;
    private ArrayList<Integer> intResults;
    private ArrayList<String> names;

    private TextView player1name;
    private TextView player2name;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        Bundle results = this.getIntent().getExtras();
        names = results.getStringArrayList("playerNames");
        numHoles = results.getInt("holes");
        numPlayers = results.getInt("players");
        intResults = new ArrayList<>(16);//4 players times 4 holes

        ArrayList<String> tempArray = new ArrayList<>();
        for(int i = 0; i < numPlayers; ++i)
        {
            tempArray.add(names.get(i));
        }

        names = tempArray;

        holes = new ArrayList<>(8);

        EditText l1 = (EditText) findViewById(R.id.Player1Hole1_EditText);
        EditText l2 = (EditText) findViewById(R.id.Player1Hole2_EditText);
        EditText l3 = (EditText) findViewById(R.id.Player1Hole3_EditText);
        EditText l4 = (EditText) findViewById(R.id.Player1Hole4_EditText);

        EditText r1 = (EditText) findViewById(R.id.Player2Hole1_EditText);
        EditText r2 = (EditText) findViewById(R.id.Player2Hole2_EditText);
        EditText r3 = (EditText) findViewById(R.id.Player2Hole3_EditText);
        EditText r4 = (EditText) findViewById(R.id.Player2Hole4_EditText);

        holes.add(l1);
        holes.add(l2);
        holes.add(l3);
        holes.add(l4);

        holes.add(r1);
        holes.add(r2);
        holes.add(r3);
        holes.add(r4);


        goRight = (ImageButton) findViewById(R.id.right_players_scroll);
        goLeft = (ImageButton) findViewById(R.id.left_players_scroll);

        goRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               changePage("Right");
            }
        });

        goLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                changePage("Left");
            }
        });

        player1name = (TextView) findViewById(R.id.Player1_textView);
        player2name = (TextView) findViewById(R.id.Player2_textView);

        player1name.setText(names.get(0));
        player2name.setText(names.get(1));

        for(int i = 0; i < 18; ++i)
        {
            intResults.add(100000);
        }


        Button endGame = (Button) findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent results = new Intent(view.getContext(), FinalScore.class);
                Bundle scores = new Bundle();

                scores.putIntegerArrayList("results", intResults);
                results.putExtras(scores);

                startActivity(results);
            }
        });

        ImageButton menu = findViewById(R.id.menubutton);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(view.getContext(), MenuPage.class);
                startActivity(menu);
            }
        });

    }

//    public void setBoard()
//    {
//        if(currentPage == 0)
//        {
//                for(int i = 0; i < 4; ++i)
//                {
//
//                }
//        }
//    }

//    public TextWatcher makeTextWatcher(int player, int hole)
//    {
//        TextWatcher temp = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable)
//            {
//                score
//            }
//        };
//
//        return temp;
//    }

    public void changePage(String direciton)
    {
        String leftPlayer = player1name.getText().toString();
        int index = -100;

        for(int i = 0; i < numPlayers; ++i)
        {
            if(leftPlayer.equals(names.get(i)))
            {
                index = i;
            }
        }

        if(direciton.equals("Right"))
        {
            if(index == names.size()-2)
            {
                player1name.setText(names.get(0));
                player2name.setText(names.get(1));
            }
            else
            {
                player1name.setText(names.get(index+2));
                player2name.setText(names.get(index+3));
            }
        }
        else
        {
            if (index == 0)
            {
                player1name.setText(names.get(names.size()-2));
                player2name.setText(names.get(names.size()-1));
            }
            else
            {
                player1name.setText(names.get(index-2));
                player2name.setText(names.get(index-3));
            }
        }
    }



//    public void goRight()
//    {
//        p1H1.removeTextChangedListener(p1);
//        p1H2.removeTextChangedListener(p1);
//        p1H3.removeTextChangedListener(p1);
//
//        p2H1.removeTextChangedListener(p2);
//        p2H2.removeTextChangedListener(p2);
//        p2H3.removeTextChangedListener(p2);
//    }
//
//    public void goLeft()
//    {}
//
//
//     TextWatcher p1 = new TextWatcher()
//    {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable)
//        {
//            String[] holeStrings = new String[3];
//            int[] holeInts = new int[3];
//
//            holeStrings[0] = p1H1.getText().toString();
//            holeStrings[1] = p1H2.getText().toString();
//            holeStrings[2] = p1H3.getText().toString();
//            holeStrings[3] = p1H4.getText().toString();
//
//            int total = 0;
//
//            for(int i = 0; i < 4; ++i)
//            {
//                if(holeStrings[i].equals(""))
//                {
//                    holeInts[i] = 0;
//                }
//                else
//                {
//                    holeInts[i] = Integer.valueOf(holeStrings[i]);
//                }
//
//                total += holeInts[i];
//            }
//
//            intResults.set(0, total);
//            p1Total.setText(Integer.toString(total));
//        }
//    };
//
//    TextWatcher p2 = new TextWatcher()
//    {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable)
//        {
//            String[] holeStrings = new String[4];
//            int[] holeInts = new int[4];
//
//            holeStrings[0] = p2H1.getText().toString();
//            holeStrings[1] = p2H2.getText().toString();
//            holeStrings[2] = p2H3.getText().toString();
//            holeStrings[3] = p2H4.getText().toString();
//
//            int total = 0;
//
//            for(int i = 0; i < 4; ++i)
//            {
//                if(holeStrings[i].equals(""))
//                {
//                    holeInts[i] = 0;
//                }
//                else
//                {
//                    holeInts[i] = Integer.valueOf(holeStrings[i]);
//                }
//
//                total += holeInts[i];
//            }
//
//            intResults.set(1, total);
//            p2Total.setText(Integer.toString(total));
//        }
//    };
//
//    TextWatcher p3 = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable)
//        {
//            String[] holeStrings = new String[4];
//            int[] holeInts = new int[4];
//
//            holeStrings[0] = p3H1.getText().toString();
//            holeStrings[1] = p3H2.getText().toString();
//            holeStrings[2] = p3H3.getText().toString();
//            holeStrings[3] = p3H4.getText().toString();
//
//            int total = 0;
//
//            for(int i = 0; i < 4; ++i)
//            {
//                if(holeStrings[i].equals(""))
//                {
//                    holeInts[i] = 0;
//                }
//                else
//                {
//                    holeInts[i] = Integer.valueOf(holeStrings[i]);
//                }
//
//                total += holeInts[i];
//            }
//
//            intResults.set(2, total);
//            p3Total.setText(Integer.toString(total));
//        }
//    };
//
//    TextWatcher p4 = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable)
//        {
//            String[] holeStrings = new String[4];
//            int[] holeInts = new int[4];
//
//            holeStrings[0] = p4H1.getText().toString();
//            holeStrings[1] = p4H2.getText().toString();
//            holeStrings[2] = p4H3.getText().toString();
//            holeStrings[3] = p4H4.getText().toString();
//
//            int total = 0;
//
//            for(int i = 0; i < 4; ++i)
//            {
//                if(holeStrings[i].equals(""))
//                {
//                    holeInts[i] = 0;
//                }
//                else
//                {
//                    holeInts[i] = Integer.valueOf(holeStrings[i]);
//                }
//
//                total += holeInts[i];
//            }
//
//            intResults.set(3, total);
//            p4Total.setText(Integer.toString(total));
//        }
//    };




}