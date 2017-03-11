/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream input = getAssets().open("words.txt");
            dictionary = new FastDictionary(input);
        } catch (IOException e) {

        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = false;//random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView winmessage = (TextView) findViewById(R.id.WinMessage);
        winmessage.setText("");
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        label.setText(COMPUTER_TURN);
        TextView winmessage = (TextView) findViewById(R.id.WinMessage);
        TextView currword = (TextView) findViewById(R.id.ghostText);
        String current = "" + currword.getText();
        if (dictionary.isWord(current)) {
            winmessage.setText("Computer Won!");
            return;
        }
        String new_word = dictionary.getAnyWordStartingWith(current);
        System.out.println(new_word);
        if (new_word == null) {
            winmessage.setText("Computer Won!hhh");
            return;
        } else {
            current = current + "" + new_word.charAt(current.length());
            currword.setText(current);
        }

        // Do computer turn stuff then make it the user's turn again
        userTurn = true;
        label.setText(USER_TURN);
    }

    public void challengeaction() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView currword = (TextView) findViewById(R.id.ghostText);
        TextView winmessage = (TextView) findViewById(R.id.WinMessage);
        String word = "" + currword.getText();
        if (word.length() >= 4 && dictionary.isWord(word)) {
            winmessage.setText("You Won");
            return;
        }
        String newword = dictionary.getAnyWordStartingWith(word);
        if (newword == null) {
            winmessage.setText("You Won");
            return;
        } else {
            label.setText("Word: " + newword);
            winmessage.setText("Computer Wins!");
        }
    }

    public void challenge(View view) {
        challengeaction();
    }

    /**
     * Handler for user key presses.
     * @param keyCode
     * @param event
     * @return whether the key stroke was handled.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TextView currword = (TextView) findViewById(R.id.ghostText);
        TextView currstatus = (TextView) findViewById(R.id.gameStatus);
        if (keyCode >=29 && keyCode <=54) {
            String word = currword.getText() + "" + (char) event.getUnicodeChar();
            currword.setText(word);
            if (dictionary.isWord(word)) {
                currstatus.setText("Valid Word");
            } else {
                currstatus.setText("Not Valid");
            }
            computerTurn();
            return true;
        }

        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        return super.onKeyUp(keyCode, event);
    }
}
