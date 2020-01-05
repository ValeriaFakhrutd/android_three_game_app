package com.example.game.level1.domain;

/**
 * An enum defining all the possible Suits that a Card can have
 */
public enum Suit {
    SPADES,
    HEARTS,
    CLUBS,
    DIAMONDS;

    /**
     * Return a String representation of this Suit, using unicode characters
     *
     * @return - the String representation of this Suit, using unicode
     */
    public String toString() {
        switch (this) {
            case SPADES:
                return "\u2660";
            case HEARTS:
                return "\u2665";
            case CLUBS:
                return "\u2663";
            case DIAMONDS:
                return "\u2666";
            default:
                return "";
        }
    }
}
