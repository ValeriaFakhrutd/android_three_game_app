package com.example.game.level2;

/**
 * Represents one game instance, or rather, a "round" of GuessTheNumber.
 */
public class Game {
    /**
     * Number of guesses, that this user has used.
     */
    private int numOfGuess;
    /**
     * Specify if game is finished.
     */
    private boolean finished;
    /**
     * Total number of point that user receives for the game.
     */
    private int points;

    /**
     * Number to guess.
     */
    private int number;

    /**
     * range where the number we seek might be.
     */
    private int range = 50;

    /**
     * Create a new Game for a user.
     */
    Game() {
        this.numOfGuess = 0;
        this.finished = false;
        this.points = 0;
        this.number = (int) (Math.random() * range + 1); //!!!!! CHANGE NEEDED.
    }

    /**
     * Finish the game.
     */
    void finishTheGame(int guess) {
        this.finished = true;
        this.updateStats(guess);
    }

    /**
     * Check if the guess is the exact number the the seek.
     */
    boolean checkTheRightGuess(int guess) {
        return this.number == guess;
    }

    /**
     * Precondition for checkGuess is the guess is not equal to the actual number.
     * Return true iff the guess is less than the number we are seeking.
     */
    boolean checkGuess(int guess) {
        this.updateStats(guess);
        return this.number < guess;
    }

    /**
     * Update statistics for the game: points and number of guesses.
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
     * Returns the points that this game currently has.
     */
    int getPoints() {
        return this.points;
    }

    /**
     * Returns the number of guesses the user has already taken so far in this game.
     */
    int getNumOfGuess() {
        return this.numOfGuess;
    }

    /**
     * Returns true if this game is finished and false otherwise.
     */
    boolean isFinished() {
        return this.finished;
    }

    /**
     * Call this method when the game is finished.
     */
    void setIsFinished() {
        this.finished = true;
    }
}
