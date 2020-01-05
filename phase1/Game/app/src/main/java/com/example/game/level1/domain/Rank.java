package com.example.game.level1.domain;

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

    // TODO: consider getting rid of this field
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

    /**
     * Get a string representation of this Rank
     *
     * @return a string representation of this rank
     */
    public String toString() {
        switch (this) {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                return "";
        }
    }
}
