package com.example.game.level3;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.Setting;
import com.example.game.data.Statistic;
import com.example.game.services.GameData;
import com.example.game.services.SettingsManager;
import com.example.game.services.SettingsManagerBuilder;
import com.example.game.services.StatsManager;
import com.example.game.services.StatsManagerBuilder;

import java.util.ArrayList;


/*
 * Image for cows_and_bull received from http://benjdd.com/courses/cs110/fall-2018/pas/bulls_and_cows/
 */

/**
 * The activity that appears right before the user is about to start a game of Cows and Bulls.
 */
public class CowsBullsActivity extends AppCompatActivity {

    // The text view for user input.
    private EditText guess;

    // The last guess that was made.
    static String currentGuess;

    // The timer for this game.
    private Chronometer chronometer;

    // The layout for the past guesses in the game.
    private LinearLayout linLayout;

    // The GameManager for this game.
    private GameManager gameManager;

    // The time in milliseconds when the player started the game.
    long startTime;

    // The player's username.
    String username = GameData.USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls);
        startTime = System.currentTimeMillis();
        chronometer = findViewById(R.id.timer);
        chronometer.start();
        guess = findViewById(R.id.guessNumber);
        linLayout = findViewById(R.id.linLayout);
        SettingsManager settingsManager = new SettingsManagerBuilder().build(this, username);
        this.gameManager = new GameManager(5, settingsManager.getSetting(Setting.ALPHABET));
        if (settingsManager.getSetting(Setting.ALPHABET) == 1) {
            guess.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            guess.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    /**
     * @return - the guess input of user as a String if guess length matches GUESS_SIZE, otherwise
     * return null.
     */
    private String guessInput() {
        try {
            if (guess.getText().toString().length() > 0)
                return guess.getText().toString().replaceAll("\\s+","");
            return "null";
        } catch (Exception e) {
            return "null";
        }
    }

    /**
     * This method performs the tasks after user had made a guess through the interface.
     *
     * @param view - view of the Activity
     */
    public void checkGuess(View view) {
        currentGuess = guessInput();


        if (currentGuess.length() == 5 & !currentGuess.equals("null")) {
            String[] guessArray = new String[currentGuess.length()];
            for (int i = 0; i < currentGuess.length(); i++){
                guessArray[i] = String.valueOf(currentGuess.charAt(i));
            }
            this.gameManager.setGuess(guessArray);
            int bulls = this.gameManager.getResults()[1];
            int cows = this.gameManager.getResults()[0];

            if (bulls == 5) {
                long stopTime = System.currentTimeMillis();
                chronometer.stop();
                StatsManager statsManager = new StatsManagerBuilder().build(this, GameData.USERNAME);
                long elapsedTime = stopTime - startTime;
                int seconds = turnToSeconds(elapsedTime);
                statsManager.setStat(Statistic.TIME_TAKEN, seconds);
                int minTime = statsManager.getStat(Statistic.QUICKEST_TIME);
                if (seconds < minTime || minTime == 0) {
                    statsManager.setStat(Statistic.QUICKEST_TIME, seconds);
                }
                statsManager.setStat(Statistic.NUMBER_OF_GUESSES, getStatistics().size());
                Intent intent = new Intent(this, CowsBullsFinishActivity.class);
                startActivity(intent);
            }


            TextView currGuess = new TextView(CowsBullsActivity.this);
            String textToDisplay = currentGuess + "     Bulls: " + bulls + " Cows: " + cows;
            currGuess.setText(textToDisplay);
            currGuess.setGravity(Gravity.CENTER);
            linLayout.addView(currGuess);
        }
        guess.setText("");
    }

    /**
     * Returns elapsedTime in seconds in and int
     *
     * @param elapsedTime - the time elapsed in milliSeconds
     */
    private int turnToSeconds(long elapsedTime) {
        return (int) (elapsedTime / 1000);
    }


    public int getBulls() {
        return this.gameManager.getResults()[1];
    }

    /**
     * A method that returns all of the data / statistics collected so far in level 3.
     *
     * @return An array of TurnData objects which store the data for each turn.
     */
    public ArrayList<TurnData> getStatistics() {
        return this.gameManager.getStatistics();
    }
}
