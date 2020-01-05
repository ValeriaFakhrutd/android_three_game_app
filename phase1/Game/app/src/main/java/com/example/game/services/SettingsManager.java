package com.example.game.services;

import com.example.game.data.Setting;

/**
 * Responsible for updating and getting settings for a given user
 * <p>
 * Implementing classes should provide a mechanism for informing the SettingsManager
 * of which user's settings are being accessed.
 */
public interface SettingsManager {
    /**
     * Record that the given setting has changed to newValue
     *
     * @param setting  - the setting that should be changed
     * @param newValue - the new value of the setting
     */
    void updateSetting(Setting setting, int newValue);

    /**
     * Retrieve the stored value of the given setting
     *
     * @param setting - the setting to query
     * @return - the stored value of the given setting
     */
    int getSetting(Setting setting);
}
