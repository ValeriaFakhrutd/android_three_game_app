package com.example.game.service_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.game.BlackjackGame.activities.BlackjackStartActivity;
import com.example.game.CowsBullsGame.activities.CowsBullsStartActivity;
import com.example.game.GuessTheNumber.activities.GuessTheNumberStartActivity;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.data.Setting;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = GameData.USERNAME;

        updateWelcomeText();

        updateMultiplayerButtons();

        //DarkMode Setting
        SettingsManager manager = new SettingsManagerFactory().build(this, username);
        int temp = manager.getSetting(Setting.DARK_MODE);
        if (temp == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * Update the buttons whose visibility/text depends on whether or not the game is in multiplayer
     * mode; namely the button to go into multiplayer mode and the "Sign Out"/"Exit Multiplayer"
     * button, whose text obviously changes with the game mode
     */
    private void updateMultiplayerButtons() {
        if (GameData.MULTIPLAYER) {
            findViewById(R.id.multiplayerButton).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.logoutButton)).setText(R.string.signOutTextMultiplayer);
        } else {
            findViewById(R.id.multiplayerButton).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.logoutButton)).setText(R.string.signOutTextSinglePlayer);
        }
    }

    /**
     * Update the welcome text according to whether it's single player, in which case we greet the user,
     * or it's multiplayer, in which case we welcome both users
     */
    private void updateWelcomeText() {
        String welcomeText;

        if (GameData.MULTIPLAYER) {
            welcomeText = "Welcome, " + formatName(MultiplayerGameData.getPlayer1Username()) + " and " + formatName(MultiplayerGameData.getPlayer2Username());
        } else {
            welcomeText = "Welcome, " + formatName(GameData.USERNAME);
        }

        ((TextView) findViewById(R.id.welcomeText)).setText(welcomeText);
    }

    /**
     * Take in a name String and capitalize the first letter
     *
     * @param name - the name to format
     * @return the input, with the first letter capitalized
     */
    private String formatName(String name) {
        if (Character.isAlphabetic(name.charAt(0))) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }

        return name;
    }

    /**
     * The method called when the "Sign Out" / "Exit Multiplayer" button is clicked
     *
     * @param view - the calling View
     */
    public void signOut(View view) {
        if (GameData.MULTIPLAYER) {
            GameData.setMultiplayer(false);
            toSinglePlayer();
        } else {
            GameData.setUsername("");
            Intent intent = new Intent(this, StartActivity.class);

            startActivity(intent);
        }
    }

    /**
     * Restore the interface to what it looks like in single player mode
     * <p>
     * That is, set the logout button to say "Sign Out", make the "Multiplayer" button visible again,
     * and change the welcome text back to "Welcome, name"
     */
    private void toSinglePlayer() {
        updateMultiplayerButtons();
        updateWelcomeText();
    }

    public void playBlackjack(View view) {
        Intent intent = new Intent(this, BlackjackStartActivity.class);
        startActivity(intent);
    }

    public void playCowsAndBulls(View view) {
        Intent intent = new Intent(this, CowsBullsStartActivity.class);
        startActivity(intent);
    }

    public void chooseTheNum(View view) {
        Intent intent = new Intent(this, GuessTheNumberStartActivity.class);
        startActivity(intent);
    }

    public void setting(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void stats(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void multiplayer(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        GameData.setMultiplayer(true);

        // Initialize the MultiplayerDataManager and record the username of player 1
        MultiplayerGameData.setPlayer1Username(GameData.USERNAME);

        startActivity(intent);
    }

    public void scoreboard(View view){
        Intent intent = new Intent(this, ScoreboardSelectActivity.class);
        startActivity(intent);
    }

    /**
     * Override the default onBackPressed method to do nothing
     * <p>
     * This prevents undesired behaviour from occurring. For example, if the player finishes a game
     * of blackjack and then goes to the main menu, they can press back multiple times to go back to
     * the game screen
     */
    @Override
    public void onBackPressed() {
    }
}
