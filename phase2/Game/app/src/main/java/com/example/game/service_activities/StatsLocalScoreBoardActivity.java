package com.example.game.service_activities;

import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.data.Statistic;
import com.example.game.services.accounts.AccountManager;
import com.example.game.services.accounts.AccountManagerFactory;
import com.example.game.services.stats.StatsManager;
import com.example.game.services.stats.StatsManagerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Activity that represent users' scores for different games in the top Rating order. It get's a
 * key of statistics that must be represented.
 */
public class StatsLocalScoreBoardActivity extends ScoreboardActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Pair<String, Integer>> topRating = new ArrayList<>();
        AccountManager GameAccountManager = new AccountManagerFactory().build(this);
        List<String> usernames = GameAccountManager.getUsers();
        //In a bundle we are saving the statistics that has been passed from StatsActivity.
        Bundle bundle = getIntent().getExtras();

        Statistic ourStats = (Statistic) bundle.getSerializable("key");
        for(String username: usernames){
            StatsManager usersStat = new StatsManagerFactory().build(this, username);
            int userGuesses = usersStat.getStat(ourStats);
            Pair<String, Integer> userTuple= new Pair<>(username, userGuesses);
            topRating.add(userTuple);
        }
        switch(ourStats){
            case QUICKEST_TIME:
            case FEWEST_GUESSES:
                Collections.sort(topRating, new Comparator<Pair<String, Integer>>() {
                @Override
                public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                    if (o1.second > o2.second){
                        return 1;
                    }
                    else if(o1.second.equals(o2.second)){
                        return 0;
                    }
                    return -1;
                }
                });
                break;
            case LONGEST_STREAK:
                Collections.sort(topRating, new Comparator<Pair<String, Integer>>() {
                    @Override
                    public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                        if (o1.second < o2.second){
                            return 1;
                        }
                        else if(o1.second.equals(o2.second)){
                            return 0;
                        }
                        return -1;
                    }
                });
                break;
        }
        String title = "";
        switch (ourStats){
            case QUICKEST_TIME: title = "Rating of Times";
            break;
            case FEWEST_GUESSES: title = "Rating of Guesses";
            break;
            case LONGEST_STREAK: title = "Longest Streak ScoreBoard";
            break;
        }

        initialize(topRating, "No scores to show");
        ((TextView) findViewById(R.id.highscoreTitle)).setText(title);
    }
}
