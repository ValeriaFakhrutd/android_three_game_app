package com.example.game.service_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.Statistic;
import com.example.game.services.stats.StatsManager;
import com.example.game.services.stats.StatsManagerFactory;

/**
 * The page displayed when the user is viewing their stats
 */
public class StatsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        String username = GameData.USERNAME;
        StatsManager statsManager = new StatsManagerFactory().build(this, username);

        TextView fewestGuesses = findViewById(R.id.fewestGuess);
        String fewestGuessesDisplay = String.valueOf(statsManager.getStat(Statistic.FEWEST_GUESSES));
        if (fewestGuessesDisplay.equals("9999")){
            fewestGuesses.setText("\u221e");
        }
        else{
            fewestGuesses.setText(fewestGuessesDisplay);
        }

        TextView quickestTime = findViewById(R.id.quickestTime);
        String quickTime = statsManager.getStat(Statistic.QUICKEST_TIME) + "s";
        if (quickTime.equals("9999s")){
            quickestTime.setText("\u221e");
        }
        else{
            quickestTime.setText(quickTime);
        }

        TextView longestStreak = findViewById(R.id.longestStreak);
        longestStreak.setText(String.valueOf(statsManager.getStat(Statistic.LONGEST_STREAK)));

        TextView whoseStats = findViewById(R.id.whoseStats);
        String whoseStatsText = "Displaying " + username + "'s statistics.";
        whoseStats.setText(whoseStatsText);
    }

    public void GuessNumStats(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", Statistic.FEWEST_GUESSES);

        Intent intent = new Intent(this, StatsLocalScoreBoardActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void CowsAndBullsStats(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", Statistic.QUICKEST_TIME);
        Intent intent = new Intent(this, StatsLocalScoreBoardActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void BlackJackStats(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", Statistic.LONGEST_STREAK);
        Intent intent = new Intent(this, StatsLocalScoreBoardActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
