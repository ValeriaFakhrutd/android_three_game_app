package com.example.game;

import com.example.game.services.AccountManager;

/**
 * Logs in existing users for a OldUserPage
 */
class OldAccountActivityPresenter {
    /**
     * The account manager being used by this object to manage user accounts
     */
    private AccountManager manager;

    /**
     * The OldUserPage that created this object
     */
    private OldUserPage callingPage;

    /**
     * Create a new OldAccountActivityPresenter
     *
     * @param manager     - the AccountManager to be used by this object to manage user accounts
     * @param callingPage - the OldUserPage that created this object
     */
    OldAccountActivityPresenter(AccountManager manager, OldUserPage callingPage) {
        this.manager = manager;
        this.callingPage = callingPage;
    }

    /**
     * Log in an existing user with the given username and password
     * <p>
     * Calls callingPage.loginError() if the given username and password
     *
     * @param username - the user's username
     * @param password - the user's password
     */
    void loginOldUser(String username, String password) {
        if (manager.validCredentials(username, password)) {
            callingPage.login();
        } else {
            callingPage.loginError("Your login information is incorrect");
        }
    }
}
