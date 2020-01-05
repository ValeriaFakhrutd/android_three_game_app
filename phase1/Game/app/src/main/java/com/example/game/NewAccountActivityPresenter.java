package com.example.game;

import com.example.game.services.AccountManager;

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
        } else {
            accountManager.createNewUser(username, password);
            callingPage.login();
        }
    }
}
