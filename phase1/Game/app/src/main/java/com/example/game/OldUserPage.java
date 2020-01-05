package com.example.game;

/**
 * Defines what a login page for an existing user should do
 */
public interface OldUserPage {
    /**
     * Tells the page to login the new user
     */
    void login();

    /**
     * Tells the page that the login of the user failed and to display the given message
     *
     * @param message - the message to display to the user
     */
    void loginError(String message);
}
