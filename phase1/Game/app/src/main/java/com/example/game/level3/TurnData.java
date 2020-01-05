package com.example.game.level3;

/**
 * A class that deals with the data for one turn of the game.
 */
public class TurnData {

    // An array that store the guess for this turn.
    private String[] guessArray;

    // The number of cows for this guess.
    private int numberCows;

    // Thu number of bulls for this turn.
    private int numberBulls;

    /**
     * Create a TurnData object. A precdndition is that guessArray and answerArray must have the
     * same sizes.
     *
     * @param guessArray  An array of the elements guessed by the player.
     * @param answerArray An array of the elements for the correct answer.
     */
    TurnData(String[] guessArray, String[] answerArray) {

        Guess guess = new Guess(guessArray, answerArray);
        this.guessArray = guessArray;
        this.numberBulls = guess.getBulls();
        this.numberCows = guess.getCows();
    }

    /**
     * A method that returns the data for this turn. Note that this method may be needed in phase 2.
     *
     * @return This TurnData object.
     */
    public TurnData getTurnData() {
        return this;
    }


    /**
     * A method that returns the number of cows and bulls for the current guess.
     *
     * @return An integer array where the first element is the number of cows and the second element
     * is the number of bulls.
     */
    int[] getResults() {
        return new int[]{this.numberCows, this.numberBulls};
    }


}
