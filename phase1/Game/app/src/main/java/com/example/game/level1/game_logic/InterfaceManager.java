package com.example.game.level1.game_logic;

/**
 * An interface is essentially a singleton observer of a game of some kind. Whenever some of the game
 * data has changed, update() should be called so that the InterfaceManager can relay any changes to
 * the interface
 */
public interface InterfaceManager {
    /**
     * Tells the InterfaceManager that the interface may need to be changed
     */
    void update();
}
