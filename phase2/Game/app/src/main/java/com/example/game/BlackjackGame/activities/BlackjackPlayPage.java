package com.example.game.BlackjackGame.activities;

/**
 * An interface defining what must be true of an activity that is playing Blackjack with the player
 */
public interface BlackjackPlayPage {
    /**
     * Update the interface to reflect the fact that the hand is over, displaying the given endGameText
     * and recording the fact that the player did or not win according to playerWin
     *
     * @param endGameText - the text to display as a result of the end of the hand
     * @param playerWin   - a boolean representing whether or not the player won the hand
     */
    void handOver(String endGameText, boolean playerWin);
}
