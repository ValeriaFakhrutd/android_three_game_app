package com.example.game.data;

/**
 * An enum defining what statistics are tracked by the program and related information about each
 * statistic
 */
public enum Statistic {
    FEWEST_GUESSES("FewestGuesses", 9999),
    LONGEST_STREAK("LongestStreak", 0),
    QUICKEST_TIME("QuickestTime", 9999),
    GUESS_POINT("PointsGuessTheNumber", 0),
    NUMBER_OF_GUESSES("NumberOfGuesses", 0),
    TIME_TAKEN("TimeTaken", 0);

    /**
     * The key that will be used to store this statistic in the stats file
     * For example:
     * <p>
     * FEWEST_GUESSES.key = "FewestGuesses" means that in the stats file there will be the line
     * <p>
     * FewestGuesses=
     * <p>
     * followed by the value for this statistic
     */
    private String key;

    /**
     * The initial value of this statistic
     */
    private int Value;

    /**
     * Create a new Statistic with the given key and initial value
     *
     * @param key   - the key of this Statistic
     * @param Value - the value of this Statistic
     */
    Statistic(String key, int Value) {
        this.key = key;
        this.Value = Value;
    }

    /**
     * Get the key of this Statistic
     *
     * @return - the key of this Statistic
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the initial value of this Statistic
     *
     * @return - the initial value of this statistic
     */
    public int getValue() {
        return Value;
    }
}
