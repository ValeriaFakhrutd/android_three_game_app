package com.example.game.level3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.MainActivity;
import com.example.game.R;
import com.example.game.data.Statistic;
import com.example.game.services.GameData;
import com.example.game.services.StatsManager;
import com.example.game.services.StatsManagerBuilder;

/**
 * This activity appears when the user successfully completes a round of Cows and Bulls.
 */
public class CowsBullsFinishActivity extends AppCompatActivity {

    /**
     * TextView display for user's time taken to guess the number
     */
    TextView time;

    /**
     * TextView display for user's number of guesses taken to guess the number
     */
    TextView numGuesses;

    /**
     * StatsManager object to retrieve and set stats for user
     */
    StatsManager statsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        statsManager = new StatsManagerBuilder().build(this, GameData.USERNAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_finish);

        time = findViewById(R.id.time);
        time.setText(((Integer) statsManager.getStat(Statistic.TIME_TAKEN)).toString() + " seconds");

        numGuesses = findViewById(R.id.numGuesses);
        numGuesses.setText(((Integer) statsManager.getStat(Statistic.NUMBER_OF_GUESSES)).toString());
    }

    /**
     * Method to allow button to take user to the MainActivity
     *
     * @param view
     */
    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
