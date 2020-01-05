package com.example.game.BlackjackGame.domain;

/**
 * An enum defining all the possible Ranks that a Card can have and some satellite information
 */
public enum Rank {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    /**
     * The value of this rank in a typical card game
     */
    private int value;

    /**
     * Create a new Rank value
     *
     * @param value - the value of the rank in a typical card game
     */
    Rank(int value) {
        this.value = value;
    }

    /**
     * Get the value of this Rank
     *
     * @return the value of this Rank
     */
    public int getValue() {
        return value;
    }
}
