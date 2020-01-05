package com.example.game.level3;

import java.util.ArrayList;

/**
 * A GameManager class that handles all of the game logic.
 */
class GameManager {
    // An array of the data for each turn.
    private ArrayList<TurnData> data = new ArrayList<>();

    // An array of the answer that the player is trying to guess.
    private String[] answerArray;

    // A TurnData object to store the information for the current turn.
    private TurnData turnData;

    /**
     * A constructor for the GameManager class.
     *
     * @param answerSize       The size of the guess / answer.
     * @param alphabetSelector The possible strings that can appear in the answer / guess.
     */
    GameManager(int answerSize, int alphabetSelector) {
        String[] alphabet;
        if (alphabetSelector == 0) {
            alphabet = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        } else {
            alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        }

        this.answerArray = new String[answerSize];
        // Generate a random string given the alphabet with size answerSize.
        for (int i = 0; i < answerSize; i++) {

            // Generate a random integer from 0 to answerSize - 1 with equal probabilities.
            int rand = (int) (Math.random() * alphabet.length);

            this.answerArray[i] = alphabet[rand];
        }

    }

    /**
     * A method that sets the current guess.
     *
     * @param guessArray The new guess.
     */
    void setGuess(String[] guessArray) {
        // Double check that aliasing is okay. Otherwise make copy.
        for (int i = 0; i < guessArray.length; i++) {
            guessArray[i] = guessArray[i].toLowerCase();
        }
        this.turnData = new TurnData(guessArray, this.answerArray);
        this.data.add(this.turnData);
    }

    /**
     * A method that returns the number of cows and bulls for the current guess.
     *
     * @return An integer array where the first element is the number of cows and the second element
     * is the number of bulls.
     */
    int[] getResults() {
        return turnData.getResults();
    }

    /**
     * A method that returns all of the data / statistics collected so far in level 3.
     *
     * @return An array of TurnData objects which store the data for each turn.
     */
    ArrayList<TurnData> getStatistics() {
        return this.data;
    }

}