package com.example.game.GuessTheNumber.domain;

/**
 * Represents one round that users play in  GuessTheNumber game.
 */
public class GuessTheNumberRound {
    /**
     * Number of guesses, that this user has used.
     */
    private int numOfGuess;
    /**
     * Specify if round is finished.
     */
    private boolean finished;
    /**
     * Total number of point that user receives for the round.
     */
    private int points;

    /**
     * Number to guess.
     */
    private int number;

    /**
     * Create a new GuessTheNumberRound for a user.
     */
    public GuessTheNumberRound(int range) {
        this.numOfGuess = 0;
        this.finished = false;
        this.points = 0;
        this.number = (int) (Math.random() * range + 1);
    }

    /**
     * Finish the round.
     */
    public void finishGuessTheNumberRound(int guess) {
        this.finished = true;
        this.updateStats(guess);
    }

    /**
     * Check if the guess is the exact number the the seek.
     */
    public boolean checkTheRightGuess(int guess) {
        return this.number == guess;
    }

    /**
     * Precondition for checkGuess is the guess is not equal to the actual number.
     * Return true iff the guess is less than the number we are seeking.
     */
    public boolean checkGuess(int guess) {
        this.updateStats(guess);
        return this.number < guess;
    }

    /**
     * Update statistics for the round: points and number of guesses.
     */
    private void updateStats(int guess) {
        this.numOfGuess++;
        this.points += Math.abs(this.number - guess) * this.numOfGuess;
    }

    /**
     * Returns the number the user is to guess.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the points that this round currently has.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Returns the number of guesses the user has already taken so far in this round.
     */
    public int getNumOfGuess() {
        return this.numOfGuess;
    }

    /**
     * Returns true if this round is finished and false otherwise.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Call this method when the round is finished.
     */
    public void setIsFinished() {
        this.finished = true;
    }
}
