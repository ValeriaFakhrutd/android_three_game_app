package com.example.game.level1.activities;

/**
 * An interface defining what must be true of an activity that is playing Blackjack with the player
 */
public interface BlackjackPlayPage {
    /**
     * Update the interface to reflect the fact that the game is over, displaying the given endGameText
     * and recording the fact that the player did or not win according to playerWin
     *
     * @param endGameText - the text to display as a result of the end of the game
     * @param playerWin   - a boolean representing whether or not the player won the game
     */
    void gameOver(String endGameText, boolean playerWin);
}
