package com.example.game.CowsBullsGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;

import java.text.MessageFormat;

/**
 * The activity before the game of Cows and Bulls commences
 */
public class CowsBullsStartActivity extends AppCompatActivity {

    /**
     * The start button in this activity
     */
    Button startButton;

    /**
     * True if the current game mode is in multiplayer mode
     */
    boolean multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_start);
        startButton = findViewById(R.id.toGame);
        multiplayer = GameData.MULTIPLAYER;
        if (multiplayer) {
            startButton.setText(MessageFormat.format("{0}''s Turn", MultiplayerGameData.getPlayer1Username()));
        }
    }


    /**
     * Method for button toGame to proceed to CowsBullsActivity
     */
    public void toGame(View view) {
        Intent intent = new Intent(this, CowsBullsActivity.class);
        startActivity(intent);
    }

    /**
     * Method for button toDiff to proceed to CowsBullsSelectDifficultyActivity
     */
    public void toDifficultySetting(View view) {
        Intent intent = new Intent(this, CowsBullsSelectDifficultyActivity.class);
        startActivity(intent);
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
