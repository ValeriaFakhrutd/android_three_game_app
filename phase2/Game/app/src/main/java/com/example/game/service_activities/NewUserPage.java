package com.example.game.service_activities;

/**
 * Defines what a new user creation page should be able to do
 */
public interface NewUserPage {
    /**
     * Tells the page to login the new user
     */
    void login();

    /**
     * Tells the page that the new user creation failed and to display the given message
     *
     * @param message - the message to display to the user
     */
    void accountCreationError(String message);
}
