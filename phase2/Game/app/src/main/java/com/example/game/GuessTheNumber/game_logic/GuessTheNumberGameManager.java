package com.example.game.GuessTheNumber.game_logic;

import com.example.game.GuessTheNumber.domain.GuessTheNumberRound;

/**
 * This class handles all the logic of the whole GAME and all the rounds user want to play.
 */
public class GuessTheNumberGameManager {
    /**
     * The current round this round manager holds and User plays.
     */
    private GuessTheNumberRound round;
    /**
     * True iff there is at least one round left to play
     */
    private boolean keepPlaying;
    /**
     * Rounds left to play for a user.
     */
    private int roundsToPlay;
    /**
     * Index of the current round user is playing.
     */
    private int currentRoundIndex;

    /**
     * True iff on single mode or if it is the first player's turn on multiplayer mode.
     */
    private boolean isFirstPlayersTurn;

    /**
     * The range of the number to be guessed.
     */
    private int range;

    /**
     * Create a gameManager
     */
    public GuessTheNumberGameManager(int range) {
        this.range = range;
        resetGameManager();

        this.roundsToPlay = 5;
    }

    /**
     * Reset the GuessTheNumberGameManager values.
     */
    public void resetGameManager() {
        startNewRound();
        this.keepPlaying = true;
        this.currentRoundIndex = 0;
        this.isFirstPlayersTurn = true;
    }

    /**
     * Creates a new round user is about to play.
     */
    public void startNewRound() {
        round = new GuessTheNumberRound(this.range);
    }

    /**
     * @return the current round user is playing.
     */
    public GuessTheNumberRound getCurrentRound() {
        return this.round;
    }

    /**
     * @return the boolean that indicates whether there is still GuessTheNumber rounds to play.
     */
    public boolean getKeepPlaying() {
        return this.keepPlaying;
    }

    /**
     * Set the number of GuessTheNumberRound to play.
     */
    public void setRoundsToPlay(int rounds) {
        this.roundsToPlay = rounds;
    }

    /**
     * Updates the number of rounds are left to play.
     */
    public void checkRounds() {
        this.currentRoundIndex++;
        this.keepPlaying = this.currentRoundIndex != this.roundsToPlay;
    }

    /**
     * Return the index of GuessTheNumberRound the user is currently on.
     */
    public int getCurrentRoundIndex() {
        return this.currentRoundIndex;
    }

    /**
     * Return the number of GuessTheNumberRound there is to play.
     */
    public int getRoundsToPlay() {
        return this.roundsToPlay;
    }

    /**
     * Reset the index of GuessTheNumberRound the user is currently on.
     */
    public void resetCurrentRounds() {
        this.currentRoundIndex = 0;
    }


    /**
     * Returns true if the user is currently on multiplayer mode and false otherwise.
     * <p>
     * /**
     * Return true if it is currently on multiplayer mode and it is the first user's turn, and
     * false otherwise.
     */
    public boolean getIsFirstPlayersTurn() {
        return this.isFirstPlayersTurn;
    }

    /**
     * Changes the boolean isFirstPlayersTurn.
     */
    public void changeIsFirstPlayersTurn() {
        this.isFirstPlayersTurn = !this.isFirstPlayersTurn;
    }

}
