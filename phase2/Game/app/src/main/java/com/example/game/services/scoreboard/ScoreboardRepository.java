package com.example.game.services.scoreboard;

import android.util.Pair;

import java.util.List;

/**
 * A ScoreboardRepository provides the services of allowing high or low scores to be recorded
 * and accessed.
 */
public interface ScoreboardRepository {
    /**
     * An enum defining what Games ScoreboardRepository objects can manage scoreboards for.
     */
    enum Game {BLACKJACK, COWS_AND_BULLS, GUESS_THE_NUMBER}

    /**
     * Record that a player with given name has achieved the given score
     *
     * @param name  - the name of the player
     * @param score - the player's score
     */
    void addScore(String name, int score);

    /**
     * Retrieves the *numberHighScores* highest scores, or all of the high scores if there are fewer
     * than *numberHighScores* in total, and returns them as an array of pairs of the form
     * [name, score], in decreasing order of score.
     * <p>
     * Precondition: numberHighScores > 0
     *
     * @param numberHighScores - the number of highScores to fetch
     * @return the *numberHighScores* highest scores, in the form of a [name,score] tuple, or all
     * the high scores if numberHighScores exceeds the total
     */
    List<Pair<String, Integer>> getHighScores(int numberHighScores);

    /**
     * Retrieves all of the high scores that this object has access to
     *
     * @return a list of all of the high scores in the form of a [name, score] tuple in decreasing
     * order of score
     */
    List<Pair<String, Integer>> getHighScores();

    /**
     * Retrieves the *numberLowScores* lowest scores, or all the low scores if there are fewer
     * than *numberLowScores* in total, and returns them as an array of pairs of the form
     * [name, score], in increasing order of score
     * <p>
     * Precondition: numberLowScores > 0
     *
     * @param numberLowScores - the number of lowScores to fetch
     * @return the *numberLowScores* lowest scores, in the form of a [name,score] tuple, or all
     * the low scores if numberLowScores exceeds the total
     */
    List<Pair<String, Integer>> getLowScores(int numberLowScores);

    /**
     * Retrieves all of the low scores that this object has access to
     *
     * @return a list of all the low scores in the form of a [name, score] tuple in increasing
     * order of score
     */
    List<Pair<String, Integer>> getLowScores();

    /**
     * Checks if the given name is valid and can be used to record a high or low score
     *
     * @return - a boolean representing whether or not the given name is valid
     */
    boolean validName(String name);
}
