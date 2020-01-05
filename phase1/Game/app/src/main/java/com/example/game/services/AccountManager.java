package com.example.game.services;

/**
 * Defines what a reasonable account manager should be able to do: query existing user data
 * and create new users
 */
public interface AccountManager {
    /**
     * Check if the given username is already being used
     *
     * @param username - the username to check
     * @return true if a user exists associated with the given username, false otherwise
     */
    boolean usernameExists(String username);

    /**
     * Check if the given username and password are valid login information
     *
     * @param username - the username to check
     * @param password - the password to check
     * @return - true if the given username and password match an account; false otherwise
     */
    boolean validCredentials(String username, String password);

    /**
     * Add a new user with the given username and password
     * <p>
     * Precondition: the given username is not already being used;
     * usernameExists(username) has been called and returned false
     *
     * @param username - the username of the new user
     * @param password - the password of the new user
     */
    void createNewUser(String username, String password);
}
