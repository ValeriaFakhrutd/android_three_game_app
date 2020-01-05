package com.example.game.level1.game_logic;

import android.widget.TextView;

import com.example.game.level1.display.PlayerHandView;
import com.example.game.level1.display.PlayerInterpreter;
import com.example.game.level1.domain.Player;

/**
 * InterfaceManager for a game of Blackjack
 */
public class BlackjackInterfaceManager implements InterfaceManager {
    /**
     * The object acting as interpreter for the user of the app
     */
    private PlayerInterpreter userInterpreter;

    /**
     * The object acting as interpreter for the dealer in the game
     */
    private PlayerInterpreter dealerInterpreter;

    /**
     * Creates a new BlackjackInterfaceManager with the given information
     *
     * @param user       - a Player object representing the user of the game
     * @param dealer     - a Player object representing the dealer in the game
     * @param userHand   - the TextView being used to display the user's hand on the screen
     * @param dealerHand - the TextView being used to display the dealer's hand on the screen
     */
    public BlackjackInterfaceManager(Player user, Player dealer, TextView userHand, TextView dealerHand) {
        userInterpreter = new PlayerInterpreter(user, new PlayerHandView(userHand));
        dealerInterpreter = new PlayerInterpreter(dealer, new PlayerHandView(dealerHand));
    }

    /**
     * Update the interface by delegating to userInterpreter and dealerInterpreter
     */
    public void update() {
        userInterpreter.updatePlayerHand();

        if (BlackjackLevelManager.playerTurn) {
            dealerInterpreter.updatePlayerHandHideFirstCard();
        } else {
            dealerInterpreter.updatePlayerHand();
        }
    }
}
