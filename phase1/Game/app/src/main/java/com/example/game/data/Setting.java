package com.example.game.data;

/**
 * An enum defining what settings are tracked by the program and related information about each setting
 */
public enum Setting {
    DARK_MODE("DarkMode", 0),
    NUM_HANDS("NumHands", 5),
    NUM_ROUNDS("NumRounds", 5),
    ALPHABET("Alphabet", 0);

    /**
     * The key that will be used to store this setting in the settings file
     * For example:
     * NUM_HANDS.key = "NumHands" means that the settings file will contain
     * <p>
     * NumHands=
     * <p>
     * followed by the value for that setting
     */
    private String key;

    /**
     * The default value for this setting
     */
    private int defaultValue;

    /**
     * Create a new Setting with the given key and defaultValue
     *
     * @param key          - the key for storing the setting in a file
     * @param defaultValue - the default value of the setting
     */
    Setting(String key, int defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * Get this Setting's key
     *
     * @return the key of this Setting
     */
    public String getKey() {
        return key;
    }

    /**
     * Get this Setting's default value
     *
     * @return the default value of this Setting
     */
    public int getDefaultValue() {
        return defaultValue;
    }
}
