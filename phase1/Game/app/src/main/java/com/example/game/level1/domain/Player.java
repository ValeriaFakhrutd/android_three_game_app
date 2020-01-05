package com.example.game.level1.domain;

/**
 * Represents a Player in a card game
 */
public class Player {
    /**
     * This player's hand
     */
    private Hand hand;

    /**
     * Get this player's hand
     *
     * @return this player's hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Create a new player with no hand
     */
    public Player() {
        hand = new Hand();
    }
}
