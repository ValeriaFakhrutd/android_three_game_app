package com.example.game.GuessTheNumber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.service_activities.MainActivity;
import com.example.game.R;
import com.example.game.data.MultiplayerGameData;
import com.example.game.data.MultiplayerIntData;
import com.example.game.services.multiplayer_data.MultiplayerDataManager;
import com.example.game.services.multiplayer_data.MultiplayerDataManagerFactory;

/**
 * The activity that appears if the user clicks on the button "See winner" in GuessTheNumberFinishActivity.
 * This activity shows player1's and player2's fewest guesses and displays who is the winner between
 * the two, or displays if there is a tie.
 */
public class GameMultiplayerFinishActivity extends AppCompatActivity {
    private MultiplayerDataManager multiplayerDataManager;

    private String player1Username = MultiplayerGameData.getPlayer1Username();
    private String player2Username = MultiplayerGameData.getPlayer2Username();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_multiplayer_finish);
        multiplayerDataManager = new MultiplayerDataManagerFactory().build();
        int player1Guesses = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_1_FEWEST_GUESSES);
        int player2Guesses = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_2_FEWEST_GUESSES);

        String player1GuessesText = formatName(player1Username) + "'s fewest number of guesses: " + player1Guesses;
        ((TextView) findViewById(R.id.guesses1)).setText(player1GuessesText);

        String player2GuessesText = formatName(player2Username) + "'s fewest number of guesses: " + player2Guesses;
        ((TextView) findViewById(R.id.guesses2)).setText(player2GuessesText);

        ((TextView) findViewById(R.id.winner)).setText(getWinner());

    }

    /**
     * Return the string message of who is the winner in multiplayer mode, or "Tie!" if there is a
     * tie between the two players.
     */
    private String getWinner() {
        int player1Guesses = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_1_FEWEST_GUESSES);
        int player2Guesses = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_2_FEWEST_GUESSES);
        if (player1Guesses > player2Guesses) {
            return formatName(player2Username) + " is the winner!";
        } else if (player2Guesses > player1Guesses) {
            return formatName(player1Username) + " is the winner!";
        } else {
            return "Tie!";
        }
    }

    /**
     * When the player clicks on this button, the user gets redirected to MainActivity.
     */
    public void mainMenuClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Format the username so that the first character of the username is capitalized.
     */
    private String formatName(String name) {
        if (Character.isAlphabetic(name.charAt(0))) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return name;
    }

    /**
     * Prevents the user from being able to return to the game screen by pressing the back button
     */
    @Override
    public void onBackPressed() {
    }
}
