package com.example.game.service_activities;

import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.services.accounts.AccountManager;

/**
 * Registers new users for a NewUserPage
 */
class NewAccountActivityPresenter {
    /**
     * The account manager being used by this object to manage user accounts
     */
    private AccountManager accountManager;

    /**
     * The NewUserPage that created this object
     */
    private NewUserPage callingPage;

    /**
     * Create a new NewAccountActivityPresenter
     *
     * @param accountManager - the AccountManager this object will use to manage user accounts
     * @param callingPage    - the page that created this object
     */
    NewAccountActivityPresenter(AccountManager accountManager, NewUserPage callingPage) {
        this.accountManager = accountManager;
        this.callingPage = callingPage;
    }


    /**
     * Register a new user with the given username and password
     * <p>
     * Calls callingPage.accountCreationError() if something is wrong with the given
     * credentials
     *
     * @param username - the username of the new user to be created
     * @param password - the password of the new user to be created
     */
    void registerNewUser(String username, String password) {
        if (accountManager.usernameExists(username)) {
            callingPage.accountCreationError("That username already exists!");
        }
        else if (!accountManager.validNewCredentials(username, password)){
            callingPage.accountCreationError("Your information is incorrect! Passwords must be non-empty, and usernames must not contain \"=\" or \",\"");
        }
        else {
            if (GameData.MULTIPLAYER) {
                MultiplayerGameData.setPlayer2Username(username);
            } else {
                GameData.setUsername(username);
            }
            accountManager.createNewUser(username, password);
            callingPage.login();
        }
    }
}
