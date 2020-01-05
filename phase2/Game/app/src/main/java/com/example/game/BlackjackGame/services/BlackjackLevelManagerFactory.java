package com.example.game.BlackjackGame.services;

import android.widget.TextView;

import com.example.game.BlackjackGame.activities.BlackjackPlayActivity;
import com.example.game.BlackjackGame.display.BlackjackInterfaceManager;
import com.example.game.BlackjackGame.domain.Deck;
import com.example.game.BlackjackGame.domain.Player;
import com.example.game.BlackjackGame.game_logic.BlackjackLevelManager;
import com.example.game.BlackjackGame.game_logic.BlackjackPlayerManager;
import com.example.game.BlackjackGame.game_logic.InterfaceManager;
import com.example.game.data.Setting;
import com.example.game.services.settings.SettingsManagerFactory;

/**
 * A class that exists solely to build complex BlackjackLevelManagers
 */
public class BlackjackLevelManagerFactory {
    /**
     * Build a new LevelManager for the given activity
     *
     * @param activity - the activity that wants the BlackjackManager
     * @param username - the username of the player whose numHands setting should be used to
     *                 play the game. This will normally also be the username of the player
     *                 who is playing the game, but may not be (for example in the event that a
     *                 multiplayer game is being played and the game has decided to use player 1's
     *                 numHands settings for both players to standardize).
     * @return a BlackjackLevelManager object
     */
    public BlackjackLevelManager build(BlackjackPlayActivity activity, String username) {
        TextView userHand = activity.findViewById(BlackjackPlayActivity.PLAYER_HAND_ID);
        TextView dealerHand = activity.findViewById(BlackjackPlayActivity.DEALER_HAND_ID);

        Player user = new Player();
        Player dealer = new Player();

        BlackjackPlayerManager userManager = new BlackjackPlayerManager(user);
        BlackjackPlayerManager dealerManager = new BlackjackPlayerManager(dealer);
        Deck deck = new Deck();

        InterfaceManager interfaceManager = new BlackjackInterfaceManager(user, dealer, userHand, dealerHand);
        BlackjackLevelManager levelManager = new BlackjackLevelManager(userManager, dealerManager, deck, interfaceManager, activity);
        levelManager.setNumHands(new SettingsManagerFactory().build(activity, username).getSetting(Setting.NUM_HANDS));

        return levelManager;
    }
}
