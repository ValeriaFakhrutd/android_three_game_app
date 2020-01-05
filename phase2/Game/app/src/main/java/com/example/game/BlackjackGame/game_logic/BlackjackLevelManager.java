package com.example.game.BlackjackGame.game_logic;

import com.example.game.BlackjackGame.activities.BlackjackPlayPage;
import com.example.game.BlackjackGame.domain.Deck;

/**
 * Manages a game of Blackjack with a user and a dealer
 */
public class BlackjackLevelManager {
    /**
     * Boolean representing whether it is the player's turn in the game
     */
    private boolean playerTurn = false;

    /**
     * A PlayerManager object representing the user of the app
     */
    private BlackjackPlayerManager user;

    /**
     * A PlayerManager object representing the dealer in the game
     */
    private BlackjackPlayerManager dealer;

    /**
     * The deck being played with in this game
     */
    private Deck deck;

    /**
     * The InterfaceManager managing the interface
     */
    private InterfaceManager interfaceManager;

    /**
     * The BlackjackPlayPage that created this instance of BlackjackLevelManager
     */
    private BlackjackPlayPage callingActivity;

    /**
     * The maximum number of hands this BlackjackLevelManager will play
     */
    private int numHands = -1;

    /**
     * The number of hands played so far
     */
    private int numHandsPlayed;

    /**
     * Create a new BlackjackLevelManager
     *
     * @param user             - the user of the app
     * @param dealer           - the dealer in the game
     * @param deck             - the deck to be played with
     * @param interfaceManager - the InterfaceManager to be used by this object
     */
    public BlackjackLevelManager(BlackjackPlayerManager user, BlackjackPlayerManager dealer, Deck deck, InterfaceManager interfaceManager, BlackjackPlayPage callingActivity) {
        this.user = user;
        this.dealer = dealer;
        this.deck = deck;
        this.interfaceManager = interfaceManager;
        this.callingActivity = callingActivity;
    }

    /**
     * Set the number of hands to be played by this BlackjackLevelManager.
     * <p>
     * Once the field has been set it cannot be changed.
     * <p>
     * Precondition: numHands is a positive integer
     *
     * @param numHands - the number of hands of Blackjack to be played by this BlackjackLevelManager
     */
    public void setNumHands(int numHands) {
        if (this.numHands == -1) {
            this.numHands = numHands;
        }
    }

    /**
     * Setup the game that this BlackjackLevelManager is going to be managing
     */
    public void setup() {
        deck.shuffle();

        user.deal(deck.deal(2));
        dealer.deal(deck.deal(2));
    }

    /**
     * Tell the BlackjackLevelManager that the game is going to be played
     */
    public void play() {
        playerTurn = true;
        interfaceManager.update(playerTurn);
    }

    /**
     * Get this BlackjackLevelManager ready to play another hand
     */
    public void playAgain() {
        deck = new Deck();
        user.newHand();
        dealer.newHand();
        setup();
        play();
    }

    /**
     * Return whether or not this BlackjackLevelManager is ready to play another hand
     */
    public boolean anotherHand() {
        return numHandsPlayed < numHands;
    }

    /**
     * Tells this object that the player requested a hit
     */
    public void playerHit() {
        if (playerTurn) {
            user.deal(deck.deal());
            if (user.computeBlackJackValue() > 21) {
                interfaceManager.update(playerTurn);
                endHand();
                return;
            }
            interfaceManager.update(playerTurn);
        }
    }

    /**
     * Tells this object that the player stood
     */
    public void playerStand() {
        endHand();
    }

    /**
     * End the hand. Calculate who won, record that another hand has been played, update the interface,
     * and tell this BlackjackLevelManager's activity that this hand is over
     */
    private void endHand() {
        numHandsPlayed++;
        playerTurn = false;
        while (dealerHit()) {
            dealer.deal(deck.deal());
        }
        interfaceManager.update(playerTurn);

        int userHand = user.computeBlackJackValue();
        int dealerHand = dealer.computeBlackJackValue();

        if (userHand > 21) {
            callingActivity.handOver("You busted!", false);
        } else {
            if (dealerHand > 21) {
                callingActivity.handOver("You won! The dealer busted!", true);
            } else {
                if (dealerHand == userHand) {
                    callingActivity.handOver("You tied! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, false);
                } else if (dealerHand < userHand) {
                    callingActivity.handOver("You won! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, true);
                } else {
                    callingActivity.handOver("You lost! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, false);
                }
            }
        }
    }

    /**
     * Compute whether or not the dealer should take another card
     *
     * @return true if the dealer's hand total is less than or equal to 16
     * false otherwise (Casino rules)
     */
    private boolean dealerHit() {
        return dealer.computeBlackJackValue() <= 16;
    }
}
