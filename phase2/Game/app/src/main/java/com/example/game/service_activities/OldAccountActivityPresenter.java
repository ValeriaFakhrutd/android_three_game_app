package com.example.game.service_activities;

import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.services.accounts.AccountManager;

/**
 * Logs in existing users for a OldUserPage
 */
class OldAccountActivityPresenter {
    /**
     * The account accountManager being used by this object to manage user accounts
     */
    private AccountManager accountManager;

    /**
     * The OldUserPage that created this object
     */
    private OldUserPage callingPage;

    /**
     * Create a new OldAccountActivityPresenter
     *
     * @param accountManager - the AccountManager to be used by this object to manage user accounts
     * @param callingPage    - the OldUserPage that created this object
     */
    OldAccountActivityPresenter(AccountManager accountManager, OldUserPage callingPage) {
        this.accountManager = accountManager;
        this.callingPage = callingPage;
    }

    /**
     * Log in an existing user with the given username and password
     * <p>
     * Calls callingPage.loginError() if the given username and password are invalid; i.e. if the
     * username doesn't exist or the password is wrong
     *
     * @param username - the user's username
     * @param password - the user's password
     */
    void loginOldUser(String username, String password) {
        if (accountManager.validCredentials(username, password)) {
            if (GameData.MULTIPLAYER) {
                if (!username.equals(MultiplayerGameData.getPlayer1Username())) {
                    MultiplayerGameData.setPlayer2Username(username);
                    callingPage.login();
                } else {
                    callingPage.loginError("You can't log " + MultiplayerGameData.getPlayer1Username() + " in twice!");
                }
            } else {
                GameData.setUsername(username);
                callingPage.login();
            }

        } else {
            callingPage.loginError("Your login information is incorrect");
        }
    }
}
