package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.game.data.Setting;
import com.example.game.level1.activities.BlackjackStartActivity;
import com.example.game.level2.GameStartActivity;
import com.example.game.level3.CowsBullsStartActivity;
import com.example.game.services.GameData;
import com.example.game.services.SettingsManager;
import com.example.game.services.SettingsManagerBuilder;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = GameData.USERNAME;
        String name = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        String welcomeText = "Welcome, " + name + "!";
        ((TextView) findViewById(R.id.welcomeText)).setText(welcomeText);

        //DarkMode Setting
        SettingsManager manager = new SettingsManagerBuilder().build(this, username);
        int temp = manager.getSetting(Setting.DARK_MODE);
        if (temp == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void playBlackjack(View view) {
        Intent intent = new Intent(this, BlackjackStartActivity.class);
        startActivity(intent);
    }

    public void playCowsAndBulls(View view) {
        Intent intent = new Intent(this, CowsBullsStartActivity.class);
        startActivity(intent);
    }

    public void chooseTheNum(View view) {
        Intent intent = new Intent(this, GameStartActivity.class);
        startActivity(intent);
    }

    public void setting(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void stats(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
}
