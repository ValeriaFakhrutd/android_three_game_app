package com.example.game.services.scoreboard;

import android.util.Pair;

import java.util.List;

/**
 * A class that selects the helps select the best scores for a game.
 */
public class BestScoreSelector {

    // The Game type for this game.
    private ScoreboardRepository.Game game;

    // The number of top scores to select.
    private final int numScores = 15;

    /**
     * A constructor for the BestScoreSelector class.
     *
     * @param game the game to select the best score from
     */
    public BestScoreSelector(ScoreboardRepository.Game game) {
        this.game = game;
    }

    /**
     * Returns whether or not the user should be prompted to save the given score.
     *
     * @param score - the score to check
     * @return whether or not the game should prompt the user to save the given score
     */
    public boolean shouldPrompt(int score) {

        switch (game) {
            case BLACKJACK:
                return shouldPromptHigh(score);
            case COWS_AND_BULLS:
                return shouldPromptLow(score);
            default:
                return false;
        }
    }

    // Helper methods to shouldPrompt.

    /**
     * Returns whether or not the user should be prompted to save the given score. This method
     * should be called when the highest scores are the best scores.
     *
     * @param score - the score to check
     * @return whether or not the game should prompt the user to save the given score
     */
    private boolean shouldPromptHigh(int score) {
        ScoreboardRepository highestScoreManager = new ScoreboardRepositoryFactory().build(game);

        List<Pair<String, Integer>> highestScores = highestScoreManager.getHighScores(numScores);

        // This score is one of the first few scores, so it should be a best score.
        if (highestScores.size() < numScores) {
            return true;
        }

        // If score is greater than any of the previous 15 highest scores, it should be a new best score.
        for (Pair<String, Integer> pair : highestScores) {
            if (pair.second < score) {
                return true;
            }
        }
        // If the top 15 scores are all >= score, then return false.
        return false;
    }

    /**
     * Returns whether or not the user should be prompted to save the given score. This method
     * should be called when the lowest scores are the best scores.
     *
     * @param score - the score to check
     * @return whether or not the game should prompt the user to save the given score
     */
    private boolean shouldPromptLow(int score) {
        ScoreboardRepository lowestScoreManager = new ScoreboardRepositoryFactory().build(game);

        List<Pair<String, Integer>> lowestScores = lowestScoreManager.getLowScores(numScores);

        // This score is one of the first few scores, so it should be a best score.
        if (lowestScores.size() < numScores) {
            return true;
        }

        // If score is less than any of the previous 15 lowest scores, it should be a new best score.
        for (Pair<String, Integer> pair : lowestScores) {
            if (pair.second > score) {
                return true;
            }
        }
        // If the top 15 scores are all <= score, then return false.
        return false;
    }
}