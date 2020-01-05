package com.example.game.service_activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.services.scoreboard.ScoreboardRepository;
import com.example.game.services.scoreboard.ScoreboardRepositoryFactory;

public class CowsBullsScoreboardActivity extends ScoreboardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScoreboardRepository scoreboardRepository = new ScoreboardRepositoryFactory().build(ScoreboardRepository.Game.COWS_AND_BULLS);

        initialize(scoreboardRepository.getLowScores(15), "No scores to show");

        String title = "Cows and Bulls High Scores";
        ((TextView) findViewById(R.id.highscoreTitle)).setText(title);
    }
}
