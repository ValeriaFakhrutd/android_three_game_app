package com.example.game.CowsBullsGame.game_logic;

import com.example.game.CowsBullsGame.domain.Answer;
import com.example.game.CowsBullsGame.domain.Guess;

import java.util.HashMap;
import java.util.Map;


/**
 * A class that deals with a guess that a player makes.
 */
class GuessInterpreter {

    // An array where each entry is the corresponding string guessed.
    private String[] guessArray;

    // An array where each entry is the corresponding string from the correct answer.
    private String[] answerArray;

    /**
     * Create a new GuessInterpreter object. A precondition is that guessArray and answerArray must
     * have the same size.
     *
     * @param guessArray   An array of the guess made by the player.
     * @param answerArray¬ An array of the answer that the player is trying to guess.
     */
    GuessInterpreter(Guess guessArray, Answer answerArray) {
        this.guessArray = guessArray.getGuessArray();
        this.answerArray = answerArray.getAnswerArray();
    }

    /**
     * A method that returns the number of bulls (the number of elements that were correctly guessed
     * and in the correct position).
     */
    int getBulls() {

        int numberBulls = 0;

        for (int i = 0; i < answerArray.length; i++) {
            if (this.guessArray[i].equals(this.answerArray[i])) {
                numberBulls += 1;
            }
        }

        return numberBulls;
    }

    /**
     * A method that returns a HashMap representation of an array where the keys are the elements in
     * the array and they values are the number of occurrences of this element in the the array.
     *
     * @param array The array to be processed.
     * @return The HashMap of element ot count pairs.
     */
    private HashMap<String, Integer> createMap(String[] array) {
        int arraySize = array.length;
        HashMap<String, Integer> arrayElements = new HashMap<>();

        for (String element : array) {
            if (!arrayElements.containsKey(element)) {
                arrayElements.put(element, 1);
            } else {
                arrayElements.put(element, arrayElements.get(element) + 1);
            }
        }

        return arrayElements;

    }

    /**
     * A method that returns the number of elements that were correctly guessed irrespective of
     * the order. Note that if the same element is guessed multiple times, then at most the number
     * of times this element appears in the answer is returned.
     */
    private int getCorrectElements() {
        HashMap<String, Integer> guessElements = createMap(this.guessArray);
        HashMap<String, Integer> answerElements = createMap(this.answerArray);

        int correctCounter = 0;
        for (Map.Entry mapElement : answerElements.entrySet()) {
            String key = (String) mapElement.getKey();

            if (guessElements.containsKey(key)) {
                correctCounter += Math.min(guessElements.get(key), answerElements.get(key));
            }
        }

        return correctCounter;

    }

    /**
     * A method that returns the number of cows (the number of elements that were correctly guessed
     * but in the wrong spot).
     *
     * @return The number of correct elements, but in the wrong spot.
     */
    int getCows() {

        //  The number of bulls is the total number of correct guessed regardless of their spot
        //  minus the number of correct guesses in the right spots.
        return getCorrectElements() - getBulls();

    }

}
