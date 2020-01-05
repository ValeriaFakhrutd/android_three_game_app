package com.example.game.CowsBullsGame.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.Statistic;
import com.example.game.services.stats.StatsManager;
import com.example.game.services.stats.StatsManagerFactory;

/**
 * This activity appears when the user successfully completes a round of Cows and Bulls.
 */
public class CowsBullsFinishActivity extends AppCompatActivity {

    /**
     * Method to initialize the layout when entering the activity
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TextView display for user's time taken to guess the number.
        TextView time;

        // TextView display for user's number of guesses taken to guess the number.
        TextView numGuesses;

        // StatsManager object to retrieve and set stats for user.
        StatsManager statsManager;

        statsManager = new StatsManagerFactory().build(this, GameData.USERNAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_finish);

        time = findViewById(R.id.time);
        time.setText(String.format("%s seconds", ((Integer) statsManager.getStat(Statistic.TIME_TAKEN)).toString()));

        numGuesses = findViewById(R.id.numGuesses);
        numGuesses.setText(((Integer) statsManager.getStat(Statistic.NUMBER_OF_GUESSES)).toString());
    }

    /**
     * Method to allow button to take user to the MainActivity.
     */
    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method to specify what to do when android back button is pressed.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
