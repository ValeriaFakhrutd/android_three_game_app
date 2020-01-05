package com.example.game.CowsBullsGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.Setting;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;

/**
 * Activity where user can select the difficulty of the Cows and Bulls game
 */
public class CowsBullsSelectDifficultyActivity extends AppCompatActivity {

    /**
     * SettingsManager for getting and setting Cows and Bulls difficulty
     */
    private SettingsManager settingsManager;

    /**
     * TextView to display current difficulty level
     */
    private TextView currDiffLevel;

    /**
     * Method to initialize the layout when entering the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_select_difficulty);

        String username = GameData.USERNAME;
        settingsManager = new SettingsManagerFactory().build(this, username);
        currDiffLevel = findViewById(R.id.currDiffLevel);

        int difficulty = settingsManager.getSetting(Setting.COWS_BULLS_DIFFICULTY);
        if (difficulty == 0) {
            currDiffLevel.setText(getResources().getString(R.string.easyString));
        } else if (difficulty == 1) {
            currDiffLevel.setText(getResources().getString(R.string.mediumString));
        } else if (difficulty == 2) {
            currDiffLevel.setText(getResources().getString(R.string.insaneString));
        }
    }

    /**
     * Method to allow Easy button to set difficulty to easy
     */
    public void easyDifficulty(View view) {
        settingsManager.updateSetting(Setting.COWS_BULLS_DIFFICULTY, 0);
        currDiffLevel.setText(getResources().getString(R.string.easyString));
    }

    /**
     * Method to allow Easy button to set difficulty to medium
     */
    public void mediumDifficulty(View view) {
        settingsManager.updateSetting(Setting.COWS_BULLS_DIFFICULTY, 1);
        currDiffLevel.setText(getResources().getString(R.string.mediumString));
    }

    /**
     * Method to allow Easy button to set difficulty to insane
     */
    public void insaneDifficulty(View view) {
        settingsManager.updateSetting(Setting.COWS_BULLS_DIFFICULTY, 2);
        currDiffLevel.setText(getResources().getString(R.string.insaneString));
    }

    /**
     * Method to go back to CowsBullsStartActivity when back is pressed
     */
    public void back(View view) {
        Intent intent = new Intent(this, CowsBullsStartActivity.class);
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
