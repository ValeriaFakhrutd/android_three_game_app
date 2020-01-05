package com.example.game.services;

import com.example.game.data.Statistic;

/**
 * An interface that defines what a reasonable statistics manager should do: query a user's stats
 * and update a user's stats
 * <p>
 * Implementing classes should provide a mechanism for determining what user's statistics are being
 * queried and updated
 */
public interface StatsManager {
    /**
     * A method that returns the value of a specific statistic.
     *
     * @param statistic The statistic to be queried.
     * @return The value of the statistic.
     */
    int getStat(Statistic statistic);

    /**
     * A method to set the value of a statistic.
     *
     * @param statistic The statistic to be changed.
     * @param value     The new value.
     */
    void setStat(Statistic statistic, int value);
}
