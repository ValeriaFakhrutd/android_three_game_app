package com.example.game.data;

/**
 * An enum defining what kinds of data take on decimal values and will need to be stored during the
 * course of a multiplayer game
 */
public enum MultiplayerDoubleData {
    /**
     * A statistic that stores player 1's win rate in the game of Blackjack they played
     */
    BLACKJACK_PLAYER_1_WIN_RATE(0.0, "player1WinRate"),

    /**
     * A statistic that stores player 2's win rate in the game of Blackjack they played
     */
    BLACKJACK_PLAYER_2_WIN_RATE(0.0, "player2WinRate");

    /**
     * The initial, default value of this particular statistic. What the statistic
     * should be initialized to at runtime when a multiplayer game starts
     */
    private double defaultValue;

    /**
     * The key to be used when storing the value. For example. If key="key1", then if the
     * multiplayer data is being stored in a file we would have:
     * ...
     * ...
     * ...
     * ...
     * key1=0.5
     * ...
     * ...
     * ...
     * or something similar in that file, meaning that the value of the statistic with key "key1"
     * is 0.5
     */
    private String key;

    /**
     * Initialize a new MultiplayerData object
     *
     * @param defaultValue - the default value of this statistic
     * @param key          - the key to be used when storing this statistic
     */
    MultiplayerDoubleData(double defaultValue, String key) {
        this.defaultValue = defaultValue;
        this.key = key;
    }

    /**
     * Access the default value of this MultiplayerData object
     *
     * @return - the default value of this MultiplayerData object
     */
    public double getDefaultValue() {
        return defaultValue;
    }

    /**
     * Access the key of this MultiplayerData object
     *
     * @return - the key of this MultiplayerData object
     */
    public String getKey() {
        return key;
    }
}
