package com.example.game.level1.game_logic;

import com.example.game.level1.activities.BlackjackPlayPage;
import com.example.game.level1.domain.BlackjackPlayerManager;
import com.example.game.level1.domain.Deck;

/**
 * Manages a game of Blackjack with a user and a dealer
 */
public class BlackjackLevelManager {
    /**
     * Boolean representing whether it is the player's turn in the game
     */
    static boolean playerTurn = false;

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

    // TODO: Figure out how to shorten this constructor, maybe using setters instead of constructor

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
        interfaceManager.update();
    }

    /**
     * Tells this object that the player requested a hit
     */
    public void playerHit() {
        if (playerTurn) {
            user.deal(deck.deal());
            if (user.computeBlackJackValue() > 21) {
                interfaceManager.update();
                endGame();
                return;
            }
            interfaceManager.update();
        }
    }

    /**
     * Tells this object that the player stood
     */
    public void playerStand() {
        endGame();
    }

    /**
     * End the game. Calculate who won, update the interface, and tell this BlackjackLevelManager's
     * activity that the game is over
     */
    private void endGame() {
        playerTurn = false;
        interfaceManager.update();
        while (dealerHit()) {
            dealer.deal(deck.deal());
        }
        interfaceManager.update();

        int userHand = user.computeBlackJackValue();
        int dealerHand = dealer.computeBlackJackValue();

        if (userHand > 21) {
            callingActivity.gameOver("You busted!", false);
        } else {
            if (dealerHand > 21) {
                callingActivity.gameOver("You won! The dealer busted!", true);
            } else {
                if (dealerHand == userHand) {
                    callingActivity.gameOver("You tied! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, false);
                } else if (dealerHand < userHand) {
                    callingActivity.gameOver("You won! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, true);
                } else {
                    callingActivity.gameOver("You lost! Your hand was a " + userHand + " and the dealer's was a " + dealerHand, false);
                }
            }
        }
    }

    /**
     * Compute whether or not the dealer should take another card
     *
     * @return true if the dealer's hand total is less than or equal to 16
     * false otherwise
     */
    private boolean dealerHit() {
        return dealer.computeBlackJackValue() <= 16;
    }
}
