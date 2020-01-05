package com.example.game.data;

/**
 * A final class for centralizing the definition of important game constants
 */
public final class GameConstants {
    /**
     * The tag to be placed at the beginning of each key when putting extras in intents
     */
    public static final String TAG = "com.example.game";

    /**
     * The key to be used when passing the value of longestStreak between activities as an extra
     * in an intent
     */
    public static final String LONGEST_STREAK_KEY = "longestStreak";

    /**
     * The key to be used when passing the value of winRate between activities as an extra
     * in an intent
     */
    public static final String WIN_RATE_KEY = "winRate";

    /**
     * The name of the directory on the device containing the files for the app
     */
    public static final String USERS_DIR_NAME = "users";

    /**
     * The name of the users settings file
     */
    public static final String SETTINGS_FILE_NAME = "settings";

    /**
     * The name of the users statistics file
     */
    public static final String STATS_FILE_NAME = "stats";

    /**
     * The name of the users password file
     */
    public static final String PASSWORD_FILE_NAME = "password";

    /**
     * The key to use when passing a username between intents
     */
    public static final String USERNAME_KEY = "username";

    /**
     * A key to be used when passing the value of a boolean that represents whether or not the
     * game is in multiplayer mode between activities
     */
    public static final String MULTIPLAYER_KEY = "multiplayer";

    public static final String BLACKJACK_HIGHSCORE_FILE = "blackjack_highscores";

    public static final String GUESS_THE_NUMBER_HIGHSCORE_FILE = "cows_and_bulls_highscores";

    public static final String COWS_AND_BULLS_HIGHSCORE_FILE = "guess_the_number_highscores";
}
