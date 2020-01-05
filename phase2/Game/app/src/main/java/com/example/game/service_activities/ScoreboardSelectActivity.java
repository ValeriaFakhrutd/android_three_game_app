package com.example.game.service_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;

/**
 * This Activity allows the user to view the scoreboard for any of the three games in our app
 */
public class ScoreboardSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_select);
    }

    /**
     * Move to the Cows and Bulls Scoreboard page
     *
     * @param view - the View that called this method
     */
    public void cowsAndBullsScoreboard(View view) {
        Intent intent = new Intent(this, CowsBullsScoreboardActivity.class);
        startActivity(intent);
    }

    /**
     * Move to the Blackjack Scoreboard page
     *
     * @param view - the View that called this method
     */
    public void blackjackScoreboard(View view) {
        Intent intent = new Intent(this, BlackjackScoreboardActivity.class);
        startActivity(intent);
    }

    /**
     * Move to the Guess the Number Scoreboard page
     *
     * @param view - the View that called this method
     */
    public void guessTheNumberScoreboard(View view) {
        Intent intent = new Intent(this, GuessTheNumberScoreBoardActivity.class);
        startActivity(intent);
    }
}
