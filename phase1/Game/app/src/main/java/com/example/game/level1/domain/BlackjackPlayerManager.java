package com.example.game.level1.domain;

import static com.example.game.level1.domain.Rank.ACE;

/**
 * Acts as a wrapper class for a Player object, allowing the player to be interacted with through
 * this object
 */
public class BlackjackPlayerManager {
    /**
     * The player that this BlackjackPlayerManager object will be managing
     */
    private Player player;

    /**
     * Create a new BlackjackPlayerManager for a given player
     *
     * @param player - the player to be managed
     */
    public BlackjackPlayerManager(Player player) {
        this.player = player;
    }

    /**
     * Add a card to the player's hand
     *
     * @param card - the card to add to the player's hand
     */
    public void deal(Card card) {
        player.getHand().addCard(card);
    }

    /**
     * Adds the given array of Cards to the player's hand
     *
     * @param cards - the array of all the cards to be added to the player's hand
     */
    public void deal(Card[] cards) {
        for (Card card : cards) {
            deal(card);
        }
    }

    /**
     * Compute the value of the player's hand according to the rules of Blackjack
     *
     * @return - the value of this player's hand, according to the rules of Blackjack
     */
    public int computeBlackJackValue() {
        Hand hand = player.getHand();
        int numAces = 0;
        int value = 0;
        Rank rank;
        for (Card card : hand) {
            rank = card.getRank();
            if (rank == ACE) {
                numAces++;
            } else {
                if (2 <= rank.getValue() && rank.getValue() <= 10) {
                    value += rank.getValue();
                } else {
                    value += 10;
                }
            }
        }

        for (int i = 0; i < numAces; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }

        return value;
    }
}
