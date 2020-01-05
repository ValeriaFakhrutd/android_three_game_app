package com.example.game.services;

import android.content.Context;

/**
 * Class for building UserStatsManager's and removing hard dependencies from classes that
 * need a StatsManager
 */
public class StatsManagerBuilder {

    /**
     * Build a SettingsManager for the user with the given username, from the given Context
     *
     * @param context  - the context that wants the SettingsManager
     * @param username - the username of the user whose settings the SettingsManager is going to
     *                 be working with
     * @return a SettingsManager for the given user
     */
    public StatsManager build(Context context, String username) {
        return new UserStatsManager(context, username);
    }
}
