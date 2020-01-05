package com.example.game.BlackjackGame.display;

import android.widget.TextView;

import com.example.game.BlackjackGame.domain.Player;
import com.example.game.BlackjackGame.game_logic.InterfaceManager;
import com.example.game.BlackjackGame.game_logic.PlayerInterpreter;

/**
 * InterfaceManager for a game of Blackjack
 */
public class BlackjackInterfaceManager implements InterfaceManager {
    /**
     * The object acting as interpreter for the user of the app
     */
    private PlayerInterpreter userInterpreter;

    /**
     * The object that will be used to update the entity on the interface displaying the user's hand
     */
    private PlayerHandView userView;

    /**
     * The object acting as interpreter for the dealer in the game
     */
    private PlayerInterpreter dealerInterpreter;

    /**
     * The object that will be used to update the entity on the interface displaying the dealer's hand
     */
    private PlayerHandView dealerView;

    /**
     * Creates a new BlackjackInterfaceManager with the given information
     *
     * @param user       - a Player object representing the user of the game
     * @param dealer     - a Player object representing the dealer in the game
     * @param userHand   - the TextView being used to display the user's hand on the screen
     * @param dealerHand - the TextView being used to display the dealer's hand on the screen
     */
    public BlackjackInterfaceManager(Player user, Player dealer, TextView userHand, TextView dealerHand) {
        userInterpreter = new PlayerInterpreter(user);
        userView = new PlayerHandView(userHand);
        dealerInterpreter = new PlayerInterpreter(dealer);
        dealerView = new PlayerHandView(dealerHand);
    }

    /**
     * Update the interface by delegating to userInterpreter and dealerInterpreter
     *
     * @param playerTurn - a boolean representing whether or not it's the player's turn (if it is,
     *                   then the dealer's first card needs to be hidden by the rules of Blackjack)
     */
    public void update(boolean playerTurn) {
        userView.updateView(userInterpreter.playerHandStringRep());

        if (playerTurn) {
            dealerView.updateView(dealerInterpreter.playerHandHideFirstCardStringRep());
        } else {
            dealerView.updateView(dealerInterpreter.playerHandStringRep());
        }
    }
}
