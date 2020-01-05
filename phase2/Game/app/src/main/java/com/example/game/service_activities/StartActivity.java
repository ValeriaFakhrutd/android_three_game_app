package com.example.game.service_activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.GameConstants;
import com.example.game.data.GameData;

import java.io.File;
import java.io.IOException;

/**
 * The page displayed when the app initially starts up
 */
public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (GameData.MULTIPLAYER) {
            findViewById(R.id.multiplayerLoginTitle).setVisibility(View.VISIBLE);
        }

        initializeGame();
    }

    /**
     * Launch the page to create a new account for the user
     * @param view - the View that called this method
     */
    public void newAccount(View view) {
        Intent intent = new Intent(this, NewAccountActivity.class);
        startActivity(intent);
    }

    /**
     * Launch the page to log in an existing account
     *
     * @param view - the View that called this method
     */
    public void oldAccount(View view) {
        Intent intent = new Intent(this, OldAccountActivity.class);
        startActivity(intent);
    }

    /**
     * A method for any code that needs to be run when the game starts
     */
    private void initializeGame() {
        try {
            GameData.setFilesDirPath(this.getFilesDir().getCanonicalPath());
            File blackjackHighscoreFile = new File(this.getFilesDir(), GameConstants.BLACKJACK_HIGHSCORE_FILE);
            File cowsAndBullsHighscoreFile = new File(this.getFilesDir(), GameConstants.COWS_AND_BULLS_HIGHSCORE_FILE);
            File guessTheNumberHighScoreFile = new File(this.getFilesDir(), GameConstants.GUESS_THE_NUMBER_HIGHSCORE_FILE);

            if(!blackjackHighscoreFile.exists()){
                blackjackHighscoreFile.createNewFile();
            }

            if(!cowsAndBullsHighscoreFile.exists()){
                cowsAndBullsHighscoreFile.createNewFile();
            }

            if(!guessTheNumberHighScoreFile.exists()){
                guessTheNumberHighScoreFile.createNewFile();
            }
        } catch (IOException e) {
            Log.e("StartActivity", "Failed to set root directory path");
        }
    }

    /**
     * Override back button behaviour in this activity to prevent unwanted things from happening
     */
    @Override
    public void onBackPressed(){
        // If the user presses back on this activity when GameData.MULTIPLAYER is true, we will allow
        // them to undo multiplayer mode and return to the MainActivity. Otherwise, pressing the
        // back button does nothing because this would allow behaviour such as signing out, pressing
        // the back button, and returning to the MainActivity without logging in
        if(GameData.MULTIPLAYER){
            Intent intent = new Intent(this, MainActivity.class);
            GameData.setMultiplayer(false);
            startActivity(intent);
        }
    }
}
