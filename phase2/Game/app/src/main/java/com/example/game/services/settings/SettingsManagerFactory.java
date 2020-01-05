package com.example.game.services.settings;

import android.content.Context;

/**
 * A class whose sole responsibility is to build UserSettingsManager objects,
 * as a way of removing hard dependencies on UserSettingsManager (a concrete implementation
 * of the SettingsManager interface) from classes that need to interact with settings
 */
public class SettingsManagerFactory {
    /**
     * Build a SettingsManager for the user with the given username, from the given Context
     *
     * @param context  - the context that wants the SettingsManager
     * @param username - the username of the user whose settings the SettingsManager is going to
     *                 be working with
     * @return a SettingsManager for the given user
     */
    public SettingsManager build(Context context, String username) {
        return new UserSettingsManager(context, username);
    }
}
