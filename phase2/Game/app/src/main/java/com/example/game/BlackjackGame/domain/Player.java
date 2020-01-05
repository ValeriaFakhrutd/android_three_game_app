package com.example.game.BlackjackGame.domain;

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

    /**
     * Give this player a new hand
     *
     * @param hand - the player's new hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
