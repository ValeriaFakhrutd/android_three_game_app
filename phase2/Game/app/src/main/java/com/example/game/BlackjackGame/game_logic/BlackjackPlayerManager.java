package com.example.game.BlackjackGame.game_logic;

import com.example.game.BlackjackGame.domain.Card;
import com.example.game.BlackjackGame.domain.Hand;
import com.example.game.BlackjackGame.domain.Player;
import com.example.game.BlackjackGame.domain.Rank;

import static com.example.game.BlackjackGame.domain.Rank.ACE;

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
    void deal(Card card) {
        player.getHand().addCard(card);
    }

    /**
     * Adds the given array of Cards to the player's hand
     *
     * @param cards - the array of all the cards to be added to the player's hand
     */
    void deal(Card[] cards) {
        for (Card card : cards) {
            deal(card);
        }
    }

    /**
     * Give the player an empty hand
     */
    void newHand() {
        player.setHand(new Hand());
    }

    /**
     * Compute the value of the player's hand according to the rules of Blackjack
     *
     * @return - the value of this player's hand, according to the rules of Blackjack
     */
    int computeBlackJackValue() {
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

        // If there is only one ace then it is either counted as an 11 or 1, whichever is best
        if (numAces == 1) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }

        // If there's more than one ace, either one of them is an 11 and the rest are 1's, if this
        // doesn't make the player bust, or they are all 1's, if the former makes the player bust
        else if (numAces >= 2) {
            if (value + 11 + numAces - 1 <= 21) {
                value += 11 + numAces - 1;
            } else {
                value += numAces;
            }
        }


        return value;
    }
}
