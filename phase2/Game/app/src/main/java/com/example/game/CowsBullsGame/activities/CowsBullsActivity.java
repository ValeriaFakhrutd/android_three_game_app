package com.example.game.CowsBullsGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.CowsBullsGame.domain.Guess;
import com.example.game.CowsBullsGame.game_logic.GameManager;
import com.example.game.CowsBullsGame.game_logic.TurnData;
import com.example.game.CowsBullsGame.services.CowsBullsStatsManager;
import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.data.Setting;
import com.example.game.services.multiplayer_data.MultiplayerDataManager;
import com.example.game.services.multiplayer_data.MultiplayerDataManagerFactory;
import com.example.game.services.scoreboard.BestScorePrompter;
import com.example.game.services.scoreboard.BestScoreSelector;
import com.example.game.services.scoreboard.ScoreboardRepository;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;
import com.example.game.services.stats.StatsManager;
import com.example.game.services.stats.StatsManagerFactory;

import java.util.ArrayList;

import static com.example.game.data.MultiplayerIntData.COWS_BULLS_PLAYER_1_NUMBER_OF_GUESSES;
import static com.example.game.data.MultiplayerIntData.COWS_BULLS_PLAYER_1_TIME_TAKEN;
import static com.example.game.data.MultiplayerIntData.COWS_BULLS_PLAYER_2_NUMBER_OF_GUESSES;
import static com.example.game.data.MultiplayerIntData.COWS_BULLS_PLAYER_2_TIME_TAKEN;
import static com.example.game.data.MultiplayerIntData.COWS_BULLS_PLAYER_TURN;


/*
 * Image for cows_and_bull received from http://benjdd.com/courses/cs110/fall-2018/pas/bulls_and_cows/
 */

/**
 * The activity where the user is playing the Cows and Bulls game
 */
public class CowsBullsActivity extends AppCompatActivity {


    //The object this class will use to manage multiplayer data if necessary
    private MultiplayerDataManager multiplayerDataManager;

    //If multiplayer = true, tells this activity whether the game being played is for player 1
    //or player 2
    private boolean player1Turn;

    // The text view for user input.
    private EditText guess;

    // The scroll view to display results.
    private ScrollView scrollView;

    // The last guess that was made.
    static String currentGuess;

    // The timer for this game.
    private Chronometer chronometer;

    // The layout for the past guesses in the game.
    private LinearLayout linLayout;

    // The GuessTheNumberGameManager for this game.
    private GameManager gameManager;

    //The CowsBullsStatsManager for this game.
    private CowsBullsStatsManager cowsBullsStatsManager;

    // The time in milliseconds when the player started the game.
    private long startTime;

    //Indicates whether multiplayer mode is on
    private boolean multiplayer;

    // The player's username.
    String username = GameData.USERNAME;

    /**
     * Method to initialize the layout and variables when entering the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls);

        multiplayerDataManager = new MultiplayerDataManagerFactory().build();
        player1Turn = multiplayerDataManager.getMultiplayerData(COWS_BULLS_PLAYER_TURN) == 1;

        scrollView = findViewById(R.id.scrollView);
        startTime = System.currentTimeMillis();
        chronometer = findViewById(R.id.timer);
        chronometer.start();
        guess = findViewById(R.id.guessNumber);
        linLayout = findViewById(R.id.linLayout);

        SettingsManager settingsManager;
        StatsManager statsManager;

        settingsManager = new SettingsManagerFactory().build(this, username);


        int difficulty = settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY);

        if (difficulty == 0) {
            gameManager = new GameManager(5, settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY));
            guess.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        } else if (difficulty == 1) {
            gameManager = new GameManager(6, settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY));
            guess.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        } else {
            gameManager = new GameManager(7, settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY));
            guess.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        }


        multiplayer = GameData.MULTIPLAYER;

        if (multiplayer) {
            if (player1Turn) {
                statsManager = new StatsManagerFactory().build(this, MultiplayerGameData.getPlayer1Username());
            } else {
                statsManager = new StatsManagerFactory().build(this, MultiplayerGameData.getPlayer2Username());
            }
        } else {
            statsManager = new StatsManagerFactory().build(this, GameData.USERNAME);
        }

        cowsBullsStatsManager = new CowsBullsStatsManager(statsManager);

        // This is one area where we would like to improve the code if we had more time.
        if (settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY) == 0) {
            guess.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        } else if (settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY) == 1) {
            guess.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        } else if (settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY) == 2) {
            guess.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#_-+=()*&%"));
        }
    }


    /**
     * @return - the guess input of user as a String if guess length matches GUESS_SIZE, otherwise
     * return null.
     */
    private String guessInput() {
        try {
            if (guess.getText().toString().length() > 0)
                return guess.getText().toString().replaceAll("\\s+", "");
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
    public void checkUserGuess(View view) {
        currentGuess = guessInput();
        Intent intent;

        if (gameManager.checkGuess(currentGuess)) {
            Guess guessArray = new Guess(currentGuess);


            this.gameManager.setGuess(guessArray);
            int bulls = this.gameManager.getResults()[1];
            int cows = this.gameManager.getResults()[0];
            System.out.println(gameManager.gameEnd());
            if (gameManager.gameEnd()) {

                long stopTime = System.currentTimeMillis();
                chronometer.stop();
                long elapsedTime = stopTime - startTime;
                int seconds = turnToSeconds(elapsedTime);
                int numberOfGuesses = getStatistics().size();
                cowsBullsStatsManager.update(seconds, numberOfGuesses);

                if (multiplayer) {
                    if (player1Turn) {
                        intent = new Intent(this, CowsBullsMidMultiplayerActivity.class);
                        multiplayerDataManager.setMultiplayerData(COWS_BULLS_PLAYER_1_NUMBER_OF_GUESSES, numberOfGuesses);
                        multiplayerDataManager.setMultiplayerData(COWS_BULLS_PLAYER_1_TIME_TAKEN, seconds);
                    } else {
                        intent = new Intent(this, CowsBullsMutliplayerFinishActivity.class);
                        multiplayerDataManager.setMultiplayerData(COWS_BULLS_PLAYER_2_NUMBER_OF_GUESSES, numberOfGuesses);
                        multiplayerDataManager.setMultiplayerData(COWS_BULLS_PLAYER_2_TIME_TAKEN, seconds);
                    }
                } else {
                    intent = new Intent(this, CowsBullsFinishActivity.class);
                }

                BestScoreSelector lowScoreSelector = new BestScoreSelector(ScoreboardRepository.Game.COWS_AND_BULLS);

                int score = cowsBullsStatsManager.getScore();

                if (lowScoreSelector.shouldPrompt(score)) {
                    BestScorePrompter lowScorePrompter = new BestScorePrompter(score, username, intent, this, ScoreboardRepository.Game.COWS_AND_BULLS);
                    lowScorePrompter.promptForScore();
                } else {
                    startActivity(intent);
                }
            }

            TextView currGuess = new TextView(CowsBullsActivity.this);
            String textToDisplay = currentGuess + "     \uD83D\uDC02: " + bulls + " \uD83D\uDC04: " + cows;
            currGuess.setText(textToDisplay);
            currGuess.setTextSize(18);
            currGuess.setGravity(Gravity.CENTER);
            linLayout.addView(currGuess);

            //Code taken from stack exchange
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });

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

    /**
     * A method that returns all of the data / statistics collected so far in level 3.
     *
     * @return An array of TurnData objects which store the data for each turn.
     */
    public ArrayList<TurnData> getStatistics() {
        return this.gameManager.getStatistics();
    }

    /**
     * Method to specify what to do when android back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
