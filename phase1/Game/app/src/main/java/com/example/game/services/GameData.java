package com.example.game.services;

/**
 * A class for storing important game data at runtime
 *
 * At the moment, the title of "important" is bestowed only on the player's username
 */
public class GameData {
    /**
     * The username of the player; to be set when the user logs in/creates a new account
     */
    public static String USERNAME;

    /**
     * Change what value is being stored in USERNAME; to be called when a
     * user logs in/creates a new account
     *
     * @param username - the new username
     */
    public static void setUsername(String username) {
        USERNAME = username;
    }
}
