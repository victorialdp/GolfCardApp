package com.testmvldp.golfcardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageButton;
import java.util.ArrayList;

public class ScoreCard extends AppCompatActivity {

    private ImageButton goRight;
    private ImageButton goLeft;

    private int numHoles;
    private int numPlayers;

    private ArrayList<EditText> holes;
    private ArrayList<Integer> intResults;
    private ArrayList<String> names;
    private ArrayList<ArrayList<Integer>> playerScores;

    private TextView player1name;
    private TextView player2name;

    private ArrayList<EditText> left;
    private ArrayList<EditText> right;

    private LinearLayout rowContainer;
    private LinearLayout firstRow;
    private TextView hole1;
    private EditText leftSide;
    private EditText rightSide;

    private ScrollView scroll;

    private TextView p1total;
    private TextView p2total;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        Bundle results = this.getIntent().getExtras();
        names = results.getStringArrayList("playerNames");
        numHoles = results.getInt("holes");
        numPlayers = results.getInt("players");
        intResults = new ArrayList<>(numPlayers);//4 players times 4 holes
        rowContainer = findViewById(R.id.rowContainer);
        firstRow = findViewById(R.id.firstHole);
        //scroll = findViewById(R.id.middleRow);

        playerScores = new ArrayList<>(numPlayers);

        hole1 = findViewById(R.id.Hole1_textView);
        leftSide = findViewById(R.id.Player1Hole1_EditText);
        rightSide = findViewById(R.id.Player2Hole1_EditText);
        p1total = findViewById(R.id.Player1RT_TextView);
        p2total = findViewById(R.id.Player2RT_TextView);

        ArrayList<String> tempArray = new ArrayList<>();
        for(int i = 0; i < numPlayers; ++i)
        {
            tempArray.add(names.get(i));
        }

        names = tempArray;

        left = new ArrayList<>(numHoles);
        right = new ArrayList<>(numHoles);

        EditText l1 = (EditText) findViewById(R.id.Player1Hole1_EditText);
        EditText l2 = (EditText) findViewById(R.id.Player1Hole2_EditText);
        EditText l3 = (EditText) findViewById(R.id.Player1Hole3_EditText);

        EditText r1 = (EditText) findViewById(R.id.Player2Hole1_EditText);
        EditText r2 = (EditText) findViewById(R.id.Player2Hole2_EditText);
        EditText r3 = (EditText) findViewById(R.id.Player2Hole3_EditText);

        left.add(l1);
        left.add(l2);
        left.add(l3);

        right.add(r1);
        right.add(r2);
        right.add(r3);


        goRight = (ImageButton) findViewById(R.id.right_players_scroll);
        goLeft = (ImageButton) findViewById(R.id.left_players_scroll);

        initialize();

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

        makePlayersArray(numPlayers);

        for(int i = 0; i < 3; ++i)
        {
           left.get(i).setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });
           right.get(i).setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });

        }


        Button endGame = (Button) findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent results = new Intent(view.getContext(), FinalScore.class);
                Bundle scores = new Bundle();

                for(int i = 0; i < numPlayers; ++i)
                {
                    intResults.add(playerScores.get(i).get(left.size()));
                }

                scores.putStringArrayList("names", names);
                scores.putIntegerArrayList("results", intResults);
                results.putExtra("players", numPlayers);
                results.putExtras(scores);

                startActivity(results);
            }
        });

        ImageButton menu = findViewById(R.id.menubutton);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent values = new Intent(view.getContext(), MenuPage.class);
                Bundle data = new Bundle();
                data.putInt("holes", numHoles);
                data.putInt("players", numPlayers);
                values.putExtras(data);
                startActivity(values);
            }
        });

        if(numPlayers == 2){

            goLeft.setVisibility(View.GONE);
            goRight.setVisibility(View.GONE);
        }

    }

    TextWatcher leftWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            update();
        }
    };

    TextWatcher rightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            update();
        }
    };


    public void initialize()
    {
        for(int i = 3; i < numHoles; ++i)
        {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(firstRow.getOrientation());
            layout.setLayoutParams(firstRow.getLayoutParams());

            TextView label = new TextView(getApplicationContext());
            label.setGravity(Gravity.CENTER);
            label.setBackgroundColor(Color.WHITE);
            label.setTypeface(Typeface.createFromAsset(getAssets(), "Comfortaa-VariableFont_wght.ttf"), Typeface.BOLD);
            label.setText("hole " + (i+1));
            label.setTextSize(19);
            label.setTextColor(Color.BLACK);


            EditText newLeft = new EditText(getApplicationContext());
            newLeft.setGravity(Gravity.CENTER);
            newLeft.setBackgroundColor(Color.WHITE);
            //newLeft.setTypeface(Typeface.DEFAULT_BOLD);
            newLeft.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            newLeft.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });
            newLeft.setSelectAllOnFocus(true);
            left.add(newLeft);

            EditText newRight = new EditText(getApplicationContext());
            newRight.setGravity(Gravity.CENTER);
            newRight.setBackgroundColor(Color.WHITE);
            //newRight.setTypeface(Typeface.DEFAULT_BOLD);
            newRight.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            newRight.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2) });
            newRight.setSelectAllOnFocus(true);
            right.add(newRight);

            rowContainer.addView(layout);

            layout.addView(label, hole1.getLayoutParams());
            layout.addView(newLeft, leftSide.getLayoutParams());
            layout.addView(newRight, rightSide.getLayoutParams());
        }

        for(int i = 0; i < numHoles; ++i)
        {
            left.get(i).setText("0");
            left.get(i).addTextChangedListener(leftWatcher);

            right.get(i).setText("0");
            right.get(i).addTextChangedListener(rightWatcher);
        }



    }

    public void makePlayersArray(int numPlayers)
    {
        for(int i = 0; i < numPlayers; ++i)
        {

            ArrayList<Integer> temp = new ArrayList<>(numHoles);
            for(int j = 0; j < numHoles+1; ++j)
            {
                temp.add(0);
            }
            playerScores.add(temp);
        }
    }


    public void changePage(String direction)
    {
        for(int i = 0; i < right.size(); ++i)
        {
            right.get(i).setFocusable(true);
            right.get(i).setFocusableInTouchMode(true);
            right.get(i).setClickable(true);
            right.get(i).setLongClickable(true);
            right.get(i).setCursorVisible(true);
        }


        String leftPlayer = player1name.getText().toString();
        int firstIndex = -100;

        for(int i = 0; i < numPlayers; ++i)
        {
            if(leftPlayer.equals(names.get(i)))
            {
                firstIndex = i;
            }
        }

        Log.i("Info", "index " + firstIndex);
        for(int i = 0; i < names.size(); ++i)
        {
            Log.i("moreInfo", names.get(i));
        }

        Log.i("Size", String.valueOf(names.size()));

        if(direction.equals("Right"))
        {
            if(firstIndex == names.size()-1)//Odd so second column blank
            {
                Log.i("Odd", "At end of odd index, so loop to beginning");
                player1name.setText(names.get(0));
                player2name.setText(names.get(1));
            }
            else if(firstIndex == names.size()-2)//Even so set both to first and second
            {
                Log.i("Even", "At end of even, so loop to beginning");
                player1name.setText(names.get(0));
                player2name.setText(names.get(1));
            }
            else if(firstIndex == names.size()-3)
            {
                Log.i("Even", "Going to end of odd, set only first column");
                player1name.setText(names.get(firstIndex+2));
                player2name.setText("");

                for(int i = 0; i < right.size(); ++i)
                {
                    right.get(i).setFocusable(false);
                    right.get(i).setFocusableInTouchMode(false);
                    right.get(i).setClickable(false);
                    right.get(i).setLongClickable(false);
                    right.get(i).setCursorVisible(false);
                }
            }
            else
            {
                player1name.setText(names.get(firstIndex+2));
                player2name.setText(names.get(firstIndex+3));
            }
        }
        else if(direction.equals("Left"))
        {
            int evenOdd = names.size() % 2;//Sees if array is even or odd
            if (firstIndex == 0)//If at beginning
            {
                if(evenOdd == 0)//Even array
                {
                    player1name.setText(names.get(names.size()-2));
                    player2name.setText(names.get(names.size()-1));
                }
                else if(evenOdd == 1)//Odd so only set first column
                {
                    player1name.setText(names.get(names.size()-1));
                    player2name.setText("");

                    for(int i = 0; i < right.size(); ++i)
                    {
                        right.get(i).setFocusable(false);
                        right.get(i).setFocusableInTouchMode(false);
                        right.get(i).setClickable(false);
                        right.get(i).setLongClickable(false);
                        right.get(i).setCursorVisible(false);
                    }
                }
            }
            else//regular function
            {
                player1name.setText(names.get(firstIndex - 2));
                player2name.setText(names.get(firstIndex-1));
            }
        }

       updateScoreboard();
    }

    public void update()
    {
        String leftPlayer = player1name.getText().toString();
        String rightPlayer = player2name.getText().toString();

        int leftIndex = -1;
        int rightIndex = -1;

        for(int i = 0; i < names.size(); ++i)
        {
            if(names.get(i).equals(leftPlayer))
            {
                leftIndex = i;
            }

            if(names.get(i).equals(rightPlayer))
            {
                rightIndex = i;
            }
        }

        if(leftIndex != -1)//Cant ever not happen i think so yeah
        {
            int total = 0;
            for(int i = 0; i < left.size(); ++i)
            {
                if(left.get(i).getText().toString().equals(""))
                {
                    playerScores.get(leftIndex).set(i, 0);
                }
                else
                {
                    int number = Integer.parseInt(left.get(i).getText().toString());
                    total = total + number;

                    playerScores.get(leftIndex).set(i, number);
                }
            }

            p1total.setText(String.valueOf(total));
            playerScores.get(leftIndex).set(left.size(), total);//Size of line is essentially numholes
        }

        if(rightIndex != -1)
        {
            int total = 0;
            for(int i = 0; i < right.size(); ++i)
            {
                if(right.get(i).getText().toString().equals(""))
                {
                    playerScores.get(rightIndex).set(i, 0);
                }
                else
                {
                    int number = Integer.parseInt(right.get(i).getText().toString());
                    total = total + number;

                    playerScores.get(rightIndex).set(i, number);
                }
            }

            p2total.setText(String.valueOf(total));
            playerScores.get(rightIndex).set(right.size(), total);
        }

    }

    public void updateScoreboard()
    {
        String leftPlayer = player1name.getText().toString();
        String rightPlayer = player2name.getText().toString();

        int leftIndex = -1;
        int rightIndex = -1;

        for(int i = 0; i < names.size(); ++i)
        {
            if(names.get(i).equals(leftPlayer))
            {
                leftIndex = i;
            }

            if(names.get(i).equals(rightPlayer))
            {
                rightIndex = i;
            }
        }

        for(int i = 0; i < playerScores.size(); ++i)
        {
            Log.i("Player", "Player " + i);
            System.out.print("Player " + i);
            ArrayList<Integer> temp = playerScores.get(i);
            for(int j = 0; j < temp.size(); ++j)
            {
                System.out.println(temp.get(j));
                Log.i("score", "" + temp.get(j));
            }
        }

        for(int i = 0; i < left.size(); ++i)
        {
            left.get(i).removeTextChangedListener(leftWatcher);
            left.get(i).setText(String.valueOf(playerScores.get(leftIndex).get(i)));
            left.get(i).addTextChangedListener(leftWatcher);
        }

        p1total.setText(String.valueOf(playerScores.get(leftIndex).get(playerScores.get(leftIndex).size()-1)));

        if(rightIndex == -1)
        {
            for(int i = 0; i < right.size(); ++i)
            {
                right.get(i).setText("");
            }
            p2total.setText("");
        }
        else
        {
            for(int i = 0; i < right.size(); ++i)
            {
                right.get(i).removeTextChangedListener(rightWatcher);
                right.get(i).setText(String.valueOf(playerScores.get(rightIndex).get(i)));
                right.get(i).addTextChangedListener(rightWatcher);
            }
            p2total.setText(String.valueOf(playerScores.get(rightIndex).get(playerScores.get(rightIndex).size()-1)));
        }

//        for(int i = 0; i < left.size(); ++i)
//        {
//            left.get(i).setText(playerScores.get(leftIndex).get(i));
//        }
//        p1total.setText(playerScores.get(leftIndex).get(left.size()));
//
//        if(rightIndex != -1)
//        {
//            for(int i = 0; i < right.size(); ++i)
//            {
//                right.get(i).setText(playerScores.get(rightIndex).get(i));
//            }
//            p2total.setText(playerScores.get(rightIndex).get(right.size()));
//        }
    }
}