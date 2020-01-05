package com.example.game.service_activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.services.scoreboard.ScoreboardRepository;
import com.example.game.services.scoreboard.ScoreboardRepositoryFactory;

public class BlackjackScoreboardActivity extends ScoreboardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScoreboardRepository scoreboardRepository = new ScoreboardRepositoryFactory().build(ScoreboardRepository.Game.BLACKJACK);

        initialize(scoreboardRepository.getHighScores(15), "No scores to show");
        Log.i("blah", scoreboardRepository.getHighScores().toString() + scoreboardRepository.getHighScores().size());

        String title = "Blackjack High Scores";
        ((TextView) findViewById(R.id.highscoreTitle)).setText(title);
    }
}
