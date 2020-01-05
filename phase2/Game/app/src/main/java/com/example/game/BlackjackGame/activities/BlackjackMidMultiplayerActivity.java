package com.example.game.BlackjackGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.MultiplayerGameData;
import com.example.game.services.multiplayer_data.MultiplayerDataManager;
import com.example.game.services.multiplayer_data.MultiplayerDataManagerFactory;

import java.text.DecimalFormat;

import static com.example.game.data.MultiplayerDoubleData.BLACKJACK_PLAYER_1_WIN_RATE;
import static com.example.game.data.MultiplayerIntData.BLACKJACK_PLAYER1_LONGEST_STREAK;
import static com.example.game.data.MultiplayerIntData.BLACKJACK_PLAYER_TURN;

/**
 * The page displayed in between player 1's and player 2's turns in Blackjack multiplayer rounds
 */
public class BlackjackMidMultiplayerActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_multiplayer);

        initializeText();
    }

    public void player2Turn(View view) {
        Intent intent = new Intent(this, BlackjackPlayActivity.class);

        // Record that it is now player 2's turn
        new MultiplayerDataManagerFactory().build().setMultiplayerData(BLACKJACK_PLAYER_TURN, 2);
        startActivity(intent);
    }

    /**
     * Initialize the title text view to say "*Player 1 name*'s statistics", the stats display
     * text views to display player 1's statistics, and the button to say "*Player 2 name*'s turn"
     */
    private void initializeText() {
        MultiplayerDataManager manager = new MultiplayerDataManagerFactory().build();
        String player1WinRate = new DecimalFormat("##.##").format(manager.getMultiplayerData(BLACKJACK_PLAYER_1_WIN_RATE)) + "%";
        String player1LongestStreak = "" + manager.getMultiplayerData(BLACKJACK_PLAYER1_LONGEST_STREAK);
        String title = MultiplayerGameData.getPlayer1Username() + "'s Statistics";
        String player2Button = MultiplayerGameData.getPlayer2Username() + "'s turn!";

        ((TextView) findViewById(R.id.longestStreakDisplay)).setText(player1LongestStreak);
        ((TextView) findViewById(R.id.winRateDisplay)).setText(player1WinRate);
        ((TextView) findViewById(R.id.blackjackMidMultiplayerTitle)).setText(title);
        ((Button) findViewById(R.id.player2PlayButton)).setText(player2Button);
    }

    /**
     * Override the back button functionality, preventing the user from pressing back in a multiplayer
     * game and returning to the screen where player 1 was playing, which would be weird
     */
    @Override
    public void onBackPressed(){}
}
