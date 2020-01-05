package com.example.game.BlackjackGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.GameConstants;

/**
 * The activity displayed after the user finishes a round of Blackjack
 */
public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        TextView longestStreakDisplay = findViewById(R.id.longestStreakDisplay);
        String longestStreak = "" + getIntent().getIntExtra(GameConstants.TAG + GameConstants.LONGEST_STREAK_KEY, 0);
        longestStreakDisplay.setText(longestStreak);
        ((TextView) findViewById(R.id.winRateDisplay)).setText(getIntent().getStringExtra(GameConstants.TAG + GameConstants.WIN_RATE_KEY));
    }

    /**
     * Starts BlackjackPlayActivity to play another round of Blackjack
     *
     * @param view - the View that called this method
     */
    public void playAnotherRound(View view) {
        Intent intent = new Intent(this, BlackjackPlayActivity.class);
        startActivity(intent);
    }

    /**
     * Starts MainActivity; goes back to main menu
     *
     * @param view - the View that called this method
     */
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Prevents the user from being able to return to the game screen by pressing the back button
     */
    @Override
    public void onBackPressed() {
    }
}
