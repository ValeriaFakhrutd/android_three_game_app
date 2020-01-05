package com.example.game.BlackjackGame.services;

import android.content.Context;

import com.example.game.data.Statistic;
import com.example.game.services.stats.StatsManager;
import com.example.game.services.stats.StatsManagerFactory;

/**
 * A class for managing a user stats while a game is running.
 * <p>
 * Can track and return win rate and the user's longest consecutive winning streak
 */
public class BlackjackStatsRecorder {
    /**
     * Keeps track of the player's longest win streak since this object was created
     */
    private int longestStreak = 0;

    /**
     * Keeps track of the length of the player's current win streak
     */
    private int currentStreak = 0;

    /**
     * How many times the player has won since this object was created
     */
    private int wins;

    /**
     * How many rounds this player has played since this object was created
     */
    private int roundsPlayed;

    /**
     * The player's all time longest winning streak at the moment this object was created
     */
    private final int allTimeLongestStreak;

    /**
     * A StatsManager for accessing and potentially updating the player's longestStreak statistic
     */
    private StatsManager statsManager;

    /**
     * The player's current score in the ongoing game
     */
    private int score;

    /**
     * The baseline amount to increase a player's score by after a win
     */
    private final int baseScore = 50;

    /**
     * The player's current multiplier
     */
    private int multiplier = 1;

    /**
     * Create a new StatsRecord from the specified context
     */
    public BlackjackStatsRecorder(Context context, String username) {
        statsManager = new StatsManagerFactory().build(context, username);
        allTimeLongestStreak = statsManager.getStat(Statistic.LONGEST_STREAK);
    }

    /**
     * Record that the player won a round
     */
    public void playerWin() {
        wins++;
        roundsPlayed++;
        score += baseScore * multiplier;
        multiplier++;

        currentStreak++;
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
        }
    }

    /**
     * Record that the player lost a round
     */
    public void playerLose() {
        roundsPlayed++;
        currentStreak = 0;
        multiplier = 1;
        score -= 2 * baseScore;
    }

    /**
     * Call this method when you want to check if the player has broken their record,
     * and if so you want the data associated with their account to be updated
     */
    public void update() {
        if (longestStreak > allTimeLongestStreak) {
            statsManager.setStat(Statistic.LONGEST_STREAK, longestStreak);
        }
    }

    /**
     * Calculate the player's win rate according to this StatRecorder's data
     *
     * @return the player's win rate if more than 0 rounds have been played, -1 otherwise
     */
    public double getWinRate() {
        if (roundsPlayed > 0) {
            return (double) wins / roundsPlayed;
        }

        return -1;
    }

    /**
     * Return the player's longest winning streak since this object was created
     *
     * @return - the player's longest winning streak since this object was created
     */
    public int getLongestStreak() {
        return longestStreak;
    }

    /**
     * Return the player's current score
     *
     * @return the player's current score
     */
    public int getScore() {
        return score;
    }
}
