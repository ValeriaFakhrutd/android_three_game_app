package com.example.game.GuessTheNumber.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.Setting;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;

/**
 * This activity allows the user to set their difficulty (ie. their range of numbers to guess)
 * for GuessTheNumber.
 */
public class GuessTheNumberSelectDifficultyActivity extends AppCompatActivity {
    /**
     * The settingsManager for this activity used to update the range setting.
     */
    SettingsManager settingsManager;

    /**
     * The text view used to display what difficulty the user is currently on.
     */
    TextView currDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number_select_difficulty);
        String username = GameData.USERNAME;
        settingsManager = new SettingsManagerFactory().build(this, username);
        currDiff = findViewById(R.id.currDiffLevel);

        int range = settingsManager.getSetting(Setting.GUESS_THE_NUMBER_RANGE);
        if (range == 50) {
            currDiff.setText("Easy");
        } else if (range == 100) {
            currDiff.setText("Medium");
        } else {
            currDiff.setText("Insane");
        }
    }

    /**
     * Update the range setting for GuessTheNumber.
     */
    public void easyDifficultyClick(View view) {
        settingsManager.updateSetting(Setting.GUESS_THE_NUMBER_RANGE, 50);
        currDiff.setText("Easy");
    }

    public void mediumDifficultyClick(View view) {
        settingsManager.updateSetting(Setting.GUESS_THE_NUMBER_RANGE, 100);
        currDiff.setText("Medium");
    }

    public void insaneDifficultyClick(View view) {
        settingsManager.updateSetting(Setting.GUESS_THE_NUMBER_RANGE, 1000);
        currDiff.setText("Insane");
    }

    public void backClick(View view) {
        Intent intent = new Intent(this, GuessTheNumberStartActivity.class);
        startActivity(intent);
    }
}
