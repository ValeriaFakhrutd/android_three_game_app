package com.example.game.BlackjackGame.domain;

/**
 * Represents a card in a deck of cards
 */
public class Card {
    /**
     * The rank of this playing card
     */
    private Rank rank;

    /**
     * The suit of this playing card
     */
    private Suit suit;

    /**
     * Get the rank of this card
     *
     * @return - the rank of this card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Get the suit of this card
     *
     * @return the suit of this card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Create a new card with the given rank and suit
     *
     * @param rank - the rank of the card
     * @param suit - the suit of the card
     */
    Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }
}
