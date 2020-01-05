package com.example.game.service_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.data.Setting;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;

/**
 * The page displayed when a user is viewing or changing their settings
 */
public class SettingsActivity extends AppCompatActivity {
    private SettingsManager settingsManager;
    private SeekBar numHandsBar;
    private SeekBar numRoundsBar;
    private Switch darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String username;
        if (GameData.MULTIPLAYER) {
            String title = MultiplayerGameData.getPlayer1Username() + "'s Settings";
            ((TextView) findViewById(R.id.settingsTitle)).setText(title);
            username = MultiplayerGameData.getPlayer1Username();
        } else {
            username = GameData.USERNAME;
        }

        settingsManager = new SettingsManagerFactory().build(this, username);

        numHandsBar = findViewById(R.id.numHandsSeekBar);
        // Read user's setting for number of hands and set it as progress on the seek bar
        numHandsBar.setProgress(settingsManager.getSetting(Setting.NUM_HANDS));

        String progressText = "" + settingsManager.getSetting(Setting.NUM_HANDS);
        ((TextView) findViewById(R.id.numHandsLabel)).setText(progressText);

        numRoundsBar = findViewById(R.id.numRoundsSeekBar);
        // Read user's setting for number of rounds and set it as progress on the seek bar
        numRoundsBar.setProgress(settingsManager.getSetting(Setting.NUM_ROUNDS));

        progressText = "" + settingsManager.getSetting(Setting.NUM_ROUNDS);
        ((TextView) findViewById(R.id.numRoundsLabel)).setText(progressText);

        numHandsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String progressString = "" + progress;
                ((TextView) findViewById(R.id.numHandsLabel)).setText(progressString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        numRoundsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String progressString = "" + progress;
                ((TextView) findViewById(R.id.numRoundsLabel)).setText(progressString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        darkMode = findViewById(R.id.darkModeSwitch);
        if (settingsManager.getSetting(Setting.DARK_MODE) == 0) {
            darkMode.setChecked(false);
        } else {
            darkMode.setChecked(true);
        }

        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean toSwitch) {
                if (toSwitch) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });

    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        settingsManager.updateSetting(Setting.NUM_HANDS, numHandsBar.getProgress());
        settingsManager.updateSetting(Setting.NUM_ROUNDS, numRoundsBar.getProgress());
        settingsManager.updateSetting(Setting.DARK_MODE, darkMode.isChecked() ? 1 : 0);
    }

    /**
     * Do nothing when the back button is pressed, because otherwise the DarkMode setting does
     * not get properly applied. The user can exit the activity via the "Main Menu" button at the
     * top of the screen
     */
    @Override
    public void onBackPressed(){}
}
