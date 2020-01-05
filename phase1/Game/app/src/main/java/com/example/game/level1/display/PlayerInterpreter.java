package com.example.game.level1.display;

import com.example.game.level1.domain.Card;
import com.example.game.level1.domain.Hand;
import com.example.game.level1.domain.Player;

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
     * The PlayerHandView that this PlayerInterpreter is using to
     * update the interface
     */
    protected PlayerHandView view;

    /**
     * Create a new PlayerInterpreter with the given instance variables
     *
     * @param player - the player this PlayerInterpreter will be interpreting
     * @param view   - the PlayerHandView this PlayerInterpreter will be using to
     *               update the interface
     */
    public PlayerInterpreter(Player player, PlayerHandView view) {
        this.player = player;
        this.view = view;
    }

    /**
     * Gets called when the player's hand has changed and the interface needs to be updated
     * <p>
     * Generates the new String representing the player's hand and tells view to update
     * the interface accordingly
     */
    public void updatePlayerHand() {
        view.updateView(this.playerHandStringRep());
    }

    /**
     * Update the interface to display the string representation of the player's hand with the first
     * card hidden
     */
    public void updatePlayerHandHideFirstCard() {
        this.view.updateView(this.playerHandHideFirstCardStringRep());
    }

    /**
     * Create and return the string representation of the player's hand, hiding the first card
     * <p>
     * Is necessary because until the player has taken their cards, the dealer's first card
     * is supposed to be unknown to the player
     *
     * @return - a string representation of the player's hand, with the first card of their hand hidden
     */
    private String playerHandHideFirstCardStringRep() {
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
                handString.append(card.toString());
                handString.append("  ");
            }

        }

        return handString.toString();
    }

    // TODO: Consider moving the String representation of each Card out of the Card class and into this one
    // TODO: Separation of concerns - Card shouldn't care how its information is relayed to the interface

    /**
     * Create and return the string representation of the player's hand
     *
     * @return - a string representation of the player's hand
     */
    private String playerHandStringRep() {
        Hand hand = player.getHand();
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString());
            handString.append("  ");
        }

        return handString.toString();
    }
}
