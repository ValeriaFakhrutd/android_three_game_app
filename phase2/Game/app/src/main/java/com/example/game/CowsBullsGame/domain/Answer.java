package com.example.game.CowsBullsGame.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Class for the answer to the cows and bulls game.
 */
public class Answer {

    // The array storing the answer string.
    private String[] answerArray;

    // The valid alphabet for this game.
    private List<String> alphabetList;

    // Constant array of digits that is used to create the alphabet.
    private static final String[] DIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    // Constant array of letters that is used to create the alphabet.
    private static final String[] LETTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    // Constant array of symbols that is used to create the alphabet.
    private static final String[] SYMBOLS = new String[]{"!", "@", "#", "_", "-", "+", "=", "(", ")", "*", "&", "%"};

    /**
     * Constructor call for the Answer object.
     *
     * @param answerSize       The size of the answer.
     * @param alphabetSelector An int to define the possible strings that can appear in the answer.
     */
    public Answer(int answerSize, int alphabetSelector) {
        String[] alphabet;
        this.answerArray = new String[answerSize];

        switch (alphabetSelector) {
            case 0:
                alphabet = DIGITS;
                break;
            case 1:
                alphabet = concatenateArrays(DIGITS, LETTERS);
                break;
            case 2:
                alphabet = concatenateArrays(DIGITS, concatenateArrays(LETTERS, SYMBOLS));
                break;
            case 3:
            default:
                alphabet = LETTERS;
        }

        for (int i = 0; i < answerSize; i++) {
            // Generate a random integer from 0 to alphabet.length - 1 with equal probabilities.
            int rand = (int) (Math.random() * alphabet.length);
            this.answerArray[i] = alphabet[rand];
        }

        alphabetList = Arrays.asList(alphabet);
    }

    /**
     * A method that returns the answer array for this answer object.
     *
     * @return The answer array for this answer object.
     */
    public String[] getAnswerArray() {
        return answerArray;
    }

    /**
     * A helper method to concatenate two arrays.
     *
     * @param array1 The first array to concatenate.
     * @param array2 The second array to concatenate.
     * @return The concatenation of array1 and array2.
     */
    private String[] concatenateArrays(String[] array1, String[] array2) {
        String[] concat = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, concat, 0, array1.length);
        System.arraycopy(array2, 0, concat, array1.length, array2.length);

        return concat;
    }

    /**
     * A method that returns whether the guess only contains strings from the alphabet.
     *
     * @param guess The string to check.
     * @return True iff all strings in guess are in the alphabet for this answer.
     */
    public boolean checkValidGuess(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            if (!checkInAlphabet(String.valueOf(guess.charAt(i)).toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * A helper method that determines if a string is in the alphabet for this answer.
     *
     * @param string The string to check for in the alphabet.
     * @return True iff string is in the alphabet for this answer object.
     */
    private boolean checkInAlphabet(String string) {
        return alphabetList.contains(string);
    }
}
