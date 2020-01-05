package com.example.game.BlackjackGame.game_logic;

import com.example.game.BlackjackGame.domain.Card;
import com.example.game.BlackjackGame.domain.Hand;
import com.example.game.BlackjackGame.domain.Player;
import com.example.game.BlackjackGame.domain.Rank;
import com.example.game.BlackjackGame.domain.Suit;

/**
 * Contains a Player object and converts the information contained in that player object to a String
 * for displaying on the interface
 */
public class PlayerInterpreter {
    /**
     * The player whose hand this PlayerInterpreter is relaying to the interface
     */
    private Player player;

    /**
     * Create a new PlayerInterpreter with the given instance variables
     *
     * @param player - the player this PlayerInterpreter will be interpreting
     */
    public PlayerInterpreter(Player player) {
        this.player = player;
    }

    /**
     * Create and return the string representation of the player's hand, hiding the first card
     * <p>
     * Is necessary because until the player has taken their cards, the dealer's first card
     * is supposed to be unknown to the player
     *
     * @return - a string representation of the player's hand, with the first card of their hand hidden
     */
    public String playerHandHideFirstCardStringRep() {
        boolean first = true;
        Hand hand = this.player.getHand();
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            if (first) {
                // Unicode for a black rectangle that kind of resembles a card back
                handString.append("\u2588");
                handString.append("   ");

                first = false;
            } else {
                handString.append(stringRep(card));
                handString.append("  ");
            }

        }

        return handString.toString();
    }

    /**
     * Create and return the string representation of the player's hand
     *
     * @return - a string representation of the player's hand
     */
    public String playerHandStringRep() {
        Hand hand = player.getHand();
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(stringRep(card));
            handString.append("  ");
        }

        return handString.toString();
    }

    /**
     * Create a String representation of the given card
     *
     * @param card - the card to convert to a String
     * @return a String representation of the given card
     */
    private String stringRep(Card card) {
        return stringRep(card.getRank()) + stringRep(card.getSuit());
    }

    /**
     * Create a String representation of the given suit
     *
     * @param suit - the suit to convert to a String
     * @return a String representation of the given suit
     */
    private String stringRep(Suit suit) {
        switch (suit) {
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

    /**
     * Create a String representation of the given rank
     *
     * @param rank - the rank to convert to a String
     * @return a String representation of the given rank
     */
    private String stringRep(Rank rank) {
        switch (rank) {
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
