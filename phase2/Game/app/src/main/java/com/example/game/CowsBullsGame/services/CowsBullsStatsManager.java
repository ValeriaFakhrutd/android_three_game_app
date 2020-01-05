package com.example.game.CowsBullsGame.services;

import com.example.game.data.Statistic;
import com.example.game.services.stats.StatsManager;

/**
 * This class handles the updating of stats for every round and highscores for Cows and Bulls
 */
public class CowsBullsStatsManager {

    //UserStatsManager for this game
    private StatsManager statsManager;

    /**
     * Thus constructor call for the CowsBullsStatManager Class
     *
     * @param statsManager The statsManager for this game
     */
    public CowsBullsStatsManager(StatsManager statsManager) {

        this.statsManager = statsManager;

    }

    /**
     * Updates the users stats given the time <seconds> and the number of guesses <numberOfGuesses>
     * the user took this round
     *
     * @param seconds         - time take for user to guess correctly
     * @param numberOfGuesses - number of guess takes for user to guess correctly
     */
    public void update(int seconds, int numberOfGuesses) {

        statsManager.setStat(Statistic.TIME_TAKEN, seconds);
        statsManager.setStat(Statistic.NUMBER_OF_GUESSES, numberOfGuesses);

        int minTime = statsManager.getStat(Statistic.QUICKEST_TIME);
        if (seconds < minTime || minTime == 0) {
            statsManager.setStat(Statistic.QUICKEST_TIME, seconds);
        }
    }

    /**
     * Return the player's score. It will be calculated as the number of guesses the player makes
     * plus the number of minutes (rounded up) that the player took.
     *
     * @return the player's score
     */
    public int getScore() {
        int numGuesses = statsManager.getStat(Statistic.NUMBER_OF_GUESSES);
        int time = statsManager.getStat(Statistic.TIME_TAKEN);
        return numGuesses + (int) Math.ceil(time / 60.0);
    }

}

