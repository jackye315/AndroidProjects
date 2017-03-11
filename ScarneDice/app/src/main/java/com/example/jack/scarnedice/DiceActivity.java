package com.example.jack.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import java.lang.*;


import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    int UserOverall;
    int UserTurn;
    int OpponentOverall;
    int OpponentTurn;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }

    /** Called when the user clicks the Send button */
    public int rolldice() {
        int roll_val = random.nextInt(6) + 1;
        ImageView image = (ImageView) findViewById(R.id.imageView);
        if (roll_val == 1) {
            image.setImageResource(R.drawable.dice1);
        }
        if (roll_val == 2) {
            image.setImageResource(R.drawable.dice2);
        }
        if (roll_val == 3) {
            image.setImageResource(R.drawable.dice3);
        }
        if (roll_val == 4) {
            image.setImageResource(R.drawable.dice4);
        }
        if (roll_val == 5) {
            image.setImageResource(R.drawable.dice5);
        }
        if (roll_val == 6) {
            image.setImageResource(R.drawable.dice6);
        }
        return roll_val;
    }

    public void Roll(View view) {
        String winmessage = "";
        TextView wintext = (TextView) findViewById(R.id.win);
        wintext.setText(winmessage);
        int val = rolldice();
        if (val == 1) {
            UserTurn = 0;
            computerTurn();
        }
        UserTurn = UserTurn + val;
        String turnscore = "Turn Score: " + UserTurn;
        TextView yourtext = (TextView) findViewById(R.id.TurnScore);
        yourtext.setText(turnscore);


    }

    public void resetvals() {
        UserOverall = 0;
        UserTurn = 0;
        OpponentOverall = 0;
        OpponentTurn = 0;
        String yourscore = "Your Score: 0";
        String opponentscore = "Opponent Score: 0";
        String turnscore = "Turn Score: 0";
        TextView yourtext = (TextView) findViewById(R.id.YourScore);
        yourtext.setText(yourscore);
        TextView opponenttext = (TextView) findViewById(R.id.CompScore);
        opponenttext.setText(opponentscore);
        TextView turntext = (TextView) findViewById(R.id.TurnScore);
        turntext.setText(turnscore);
    }


    public void Reset(View view) {
        resetvals();
    }

    public void Hold(View view) {
        UserOverall = UserOverall + UserTurn;
        UserTurn = 0;
        String yourscore = "Your Score: " + UserOverall;
        TextView yourtext = (TextView) findViewById(R.id.YourScore);
        yourtext.setText(yourscore);
        computerTurn();
        if (UserOverall >= 100) {
            String winmessage = "You Won!!!";
            TextView wintext = (TextView) findViewById(R.id.win);
            wintext.setText(winmessage);
            resetvals();
        }
    }

    //Computer Turn
    public void computerTurn() {
        String turnscore;
        Button rollbutton = (Button) findViewById(R.id.button1);
        rollbutton.setEnabled(false);
        Button holdbutton = (Button) findViewById(R.id.button2);
        holdbutton.setEnabled(false);
        OpponentTurn = 0;
        while (OpponentTurn < 20 ) {

            int curr = rolldice();
            if (curr == 1) {
                OpponentTurn = 0;
                break;
            }
            OpponentTurn = OpponentTurn + curr;
            turnscore = "Computer Turn Score:" + OpponentTurn;
            TextView turntext = (TextView) findViewById(R.id.TurnScore);
            turntext.setText(turnscore);
            int counter = 0;
            while (counter < 10000000) {
                counter ++;
            }
            counter = 0;

        }
        turnscore = "Computer Holds at " + OpponentTurn;
        OpponentOverall = OpponentOverall + OpponentTurn;
        TextView turntext = (TextView) findViewById(R.id.TurnScore);
        turntext.setText(turnscore);
        String opponentscore = "Opponent Score: " + OpponentOverall;
        TextView opponenttext = (TextView) findViewById(R.id.CompScore);
        opponenttext.setText(opponentscore);
        rollbutton.setEnabled(true);
        holdbutton.setEnabled(true);
        if (OpponentOverall >= 100) {
            String winmessage = "You Lost!!!";
            TextView wintext = (TextView) findViewById(R.id.win);
            wintext.setText(winmessage);
            resetvals();
        }
    }




}
