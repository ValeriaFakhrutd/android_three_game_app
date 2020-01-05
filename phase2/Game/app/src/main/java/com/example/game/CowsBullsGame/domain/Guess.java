package com.example.game.CowsBullsGame.domain;


/**
 * An object that stores the user's guessArray.
 */
public class Guess {

    // String array for the guess.
    private String[] guessArray;

    /**
     * Constructor method for the Guess class.
     *
     * @param guess The guess string.
     */
    public Guess(String guess) {
        guessArray = new String[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            guessArray[i] = String.valueOf(guess.charAt(i)).toLowerCase();
        }
    }

    /**
     * Getter for the guessArray.
     *
     * @return Returns guessArray.
     */
    public String[] getGuessArray() {
        return guessArray;
    }
}
