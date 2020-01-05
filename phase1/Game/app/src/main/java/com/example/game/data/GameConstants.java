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
}
