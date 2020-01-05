package com.example.game.BlackjackGame.activities;

import com.example.game.data.MultiplayerDoubleData;
import com.example.game.data.MultiplayerIntData;
import com.example.game.services.multiplayer_data.MultiplayerDataManager;

/**
 * This class takes care of technical, use-case related tasks for activities involved in playing
 * multiplayer Blackjack.
 *
 * At the moment it is only used by BlackjackMidMultiplayerActivity
 * <p>
 * Currently, this class's responsibilities exclusively comprise resetting blackjack multiplayer data
 */
class BlackjackMultiplayerActivityPresenter {
    /**
     * The MultiplayerDataManager that this class will use to manage multiplayer data
     */
    private MultiplayerDataManager multiplayerDataManager;

    /**
     * Create a new BlackjackMultiplayerActivityPresenter
     *
     * @param multiplayerDataManager - the object that this class should use to edit and access
     *                               multiplayer data
     */
    BlackjackMultiplayerActivityPresenter(MultiplayerDataManager multiplayerDataManager) {
        this.multiplayerDataManager = multiplayerDataManager;
    }

    /**
     * Reset the multiplayer data being stored so that another game of multiplayer Blackjack can be
     * played from a fresh start
     */
    void resetMultiplayerData() {
        // Reset the player turn value to 1 so that if another game is played, we start with player 1
        // again
        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.BLACKJACK_PLAYER_TURN, 1);

        // Reset the win rate values to 0.0
        multiplayerDataManager.setMultiplayerData(MultiplayerDoubleData.BLACKJACK_PLAYER_1_WIN_RATE, 0.0);
        multiplayerDataManager.setMultiplayerData(MultiplayerDoubleData.BLACKJACK_PLAYER_2_WIN_RATE, 0.0);

        // Reset the longest win streak values to 0
        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.BLACKJACK_PLAYER1_LONGEST_STREAK, 0);
        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.BLACKJACK_PLAYER2_LONGEST_STREAK, 0);
    }
}
