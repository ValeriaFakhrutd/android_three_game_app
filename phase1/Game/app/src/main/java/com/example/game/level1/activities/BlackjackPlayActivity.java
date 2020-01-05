package com.example.game.level1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.Setting;
import com.example.game.data.Statistic;
import com.example.game.level1.display.ButtonManager;
import com.example.game.level1.game_logic.BlackjackLevelManager;
import com.example.game.level1.services.BlackjackLevelManagerBuilder;
import com.example.game.services.GameData;
import com.example.game.services.SettingsManagerBuilder;
import com.example.game.services.StatsManager;
import com.example.game.services.StatsManagerBuilder;

import java.text.DecimalFormat;

import static com.example.game.data.GameConstants.LONGEST_STREAK_KEY;
import static com.example.game.data.GameConstants.TAG;
import static com.example.game.data.GameConstants.WIN_RATE_KEY;

/**
 * The page displayed when the user is actually playing a round of Blackjack
 */
public class BlackjackPlayActivity extends AppCompatActivity implements BlackjackPlayPage {
    /**
     * Constants that record the IDs of the various UI elements
     * To be used throughout this level as objects interact with UI elements
     */
    public static final int PLAYER_HAND_ID = R.id.playerHand;
    public static final int DEALER_HAND_ID = R.id.dealerHand;
    public static final int END_GAME_TEXT_ID = R.id.endGameText;
    public static final int HIT_BUTTON_ID = R.id.hitButton;
    public static final int STAND_BUTTON_ID = R.id.standButton;
    public static final int END_GAME_BUTTON_ID = R.id.endGameButton;
    public static final int PLAY_AGAIN_BUTTON_ID = R.id.playAgainButton;

    /**
     * The note to be displayed at the top of the screen
     */
    private static final String note = "Note: A \u2588 represents a card the dealer has that you can't see";

    /**
     * The LevelManager that will play the game taking place in this activity
     */
    public static BlackjackLevelManager levelManager;

    /**
     * The class managing the buttons in this Activity
     */
    private ButtonManager buttonManager;

    /**
     * The number of wins the player has
     */
    private int wins = 0;

    /**
     * The number of hands this player chose to play in their settings
     */
    private int numHands;

    /**
     * The number of hands this player has played so far
     */
    private int numHandsPlayed = 0;

    /**
     * The maximum number of hands this user has won in a row this round
     */
    private int longestStreak = 0;

    /**
     * The current number of hands the user has won in a row
     */
    private int currentStreak = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackjack_play);

        buttonManager = new ButtonManager(this);

        BlackjackLevelManagerBuilder builder = new BlackjackLevelManagerBuilder();
        levelManager = builder.build(this);

        levelManager.setup();
        levelManager.play();

        numHands = new SettingsManagerBuilder().build(this, GameData.USERNAME).getSetting(Setting.NUM_HANDS);

        ((TextView) findViewById(R.id.blackjackNote)).setText(note);
    }

    /**
     * Pass on to the levelManager that a button has been clicked
     *
     * @param view - the button that was clicked
     */
    public void buttonClick(View view) {
        if (view.getId() == HIT_BUTTON_ID) {
            levelManager.playerHit();
        } else if (view.getId() == STAND_BUTTON_ID) {
            levelManager.playerStand();
        }
    }

    /**
     * Refresh the game. Erase the end game text, "play again" button, and "next" button,
     * and initialize a new LevelManager and therefore a new game
     *
     * @param view - the button that was clicked
     */
    public void playAgain(View view) {
        BlackjackLevelManagerBuilder builder = new BlackjackLevelManagerBuilder();
        levelManager = builder.build(this);

        buttonManager.makeButtonInvisible(END_GAME_BUTTON_ID);
        buttonManager.makeButtonInvisible(PLAY_AGAIN_BUTTON_ID);
        buttonManager.enableButton(HIT_BUTTON_ID);
        buttonManager.enableButton(STAND_BUTTON_ID);

        findViewById(END_GAME_TEXT_ID).setVisibility(View.INVISIBLE);

        levelManager.setup();
        levelManager.play();
    }

    /**
     * Move to the EndGame screen
     *
     * @param view - the button that was clicked
     */
    public void endGame(View view) {
        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra(TAG + WIN_RATE_KEY, new DecimalFormat("##.##").format(100 * ((float) (wins) / (float) numHandsPlayed)) + "%");
        intent.putExtra(TAG + LONGEST_STREAK_KEY, longestStreak);
        startActivity(intent);
    }

    /**
     * Record that the round ended. Make the "play again" button and the "next" button visible, and display the given end of game
     * text
     *
     * @param endGameText - the text to display as a result of the game ending
     */
    public void gameOver(String endGameText, boolean playerWin) {
        buttonManager.disableButton(BlackjackPlayActivity.HIT_BUTTON_ID);
        buttonManager.disableButton(BlackjackPlayActivity.STAND_BUTTON_ID);
        numHandsPlayed++;
        if (numHandsPlayed == numHands) {
            buttonManager.makeVisible(END_GAME_BUTTON_ID);
        } else {
            buttonManager.makeVisible(PLAY_AGAIN_BUTTON_ID);
        }
        TextView endGameTextView = findViewById(END_GAME_TEXT_ID);
        endGameTextView.setText(endGameText);
        endGameTextView.setVisibility(View.VISIBLE);

        if (playerWin) {
            currentStreak += 1;
            if (currentStreak > longestStreak) {
                longestStreak = currentStreak;
            }
            wins++;
        } else {
            currentStreak = 0;
        }

        StatsManager statsManager = new StatsManagerBuilder().build(this, GameData.USERNAME);

        int savedLongestStreak = statsManager.getStat(Statistic.LONGEST_STREAK);

        if (longestStreak > savedLongestStreak) {
            statsManager.setStat(Statistic.LONGEST_STREAK, longestStreak);
        }
    }
}
