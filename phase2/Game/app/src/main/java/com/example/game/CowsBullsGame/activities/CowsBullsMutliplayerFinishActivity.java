package com.example.game.CowsBullsGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
 * Activity for the end of game display for multiplayer version of Cows and Bulls
 */
public class CowsBullsMutliplayerFinishActivity extends AppCompatActivity {

    /**
     * Method to initialize the layout when entering the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TextView display for player 1's time taken to guess the number
        TextView player1TimeTaken;

        // TextView display for player 1's number of guesses taken to guess the number
        TextView player1TurnsTaken;

        // TextView display for player 2's time taken to guess the number
        TextView player2TimeTaken;

        // TextView display for player 2's number of guesses taken to guess the number
        TextView player2TurnsTaken;

        // TextView display for player 2's number of guesses taken to guess the number
        TextView winMessage;

        // MultiplayerDataManager object to retrieve and set stats for user
        MultiplayerDataManager multiplayerDataManager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_mutliplayer_finish);

        multiplayerDataManager = new MultiplayerDataManagerFactory().build();

        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.COWS_BULLS_PLAYER_TURN, 1);

        winMessage = findViewById(R.id.winMessage);
        player1TimeTaken = findViewById(R.id.player1TimeTaken);
        player2TimeTaken = findViewById(R.id.player2TimeTaken);
        player1TurnsTaken = findViewById(R.id.player1TurnsTaken);
        player2TurnsTaken = findViewById(R.id.player2TurnsTaken);

        Integer player1Time = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.COWS_BULLS_PLAYER_1_TIME_TAKEN);
        Integer player2Time = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.COWS_BULLS_PLAYER_2_TIME_TAKEN);
        Integer player1Turns = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.COWS_BULLS_PLAYER_1_NUMBER_OF_GUESSES);
        Integer player2Turns = multiplayerDataManager.getMultiplayerData(MultiplayerIntData.COWS_BULLS_PLAYER_2_NUMBER_OF_GUESSES);

        player1TimeTaken.setText(String.format("%ss", player1Time.toString()));
        player2TimeTaken.setText(String.format("%ss", player2Time.toString()));
        player1TurnsTaken.setText(String.format("%s", player1Turns.toString()));
        player2TurnsTaken.setText(String.format("%s", player2Turns.toString()));

        //Formatting the username for better display
        String player1Username = formatName(MultiplayerGameData.getPlayer1Username());
        String player2Username = formatName(MultiplayerGameData.getPlayer2Username());

        //Displaying a custom win message based on all the outcomes that are possible
        if (player1Time.equals(player2Time) & player1Turns.equals(player2Turns)) {
            winMessage.setText(String.format("%s is equally as smart or equally as dumb as %s!", player1Username, player2Username));
        } else if (player1Time > player2Time & player1Turns > player2Turns) {
            winMessage.setText(String.format("Congratulations %s you are by and far better than %s!", player2Username, player1Username));
        } else if (player1Time < player2Time & player1Turns < player2Turns) {
            winMessage.setText(String.format("Congratulations %s you are by and far better than %s!", player1Username, player2Username));
        } else if (player1Time > player2Time & player1Turns < player2Turns) {
            winMessage.setText(String.format("%s reasoned faster, whereas %s was more efficient with his reasoning!", player2Username, player1Username));
        } else if (player1Time < player2Time & player1Turns > player2Turns) {
            winMessage.setText(String.format("%sreasoned faster, whereas %swas more efficient with his reasoning!", player1Username, player2Username));
        } else if (player1Time.equals(player2Time) & player1Turns > player2Turns) {
            winMessage.setText(String.format("%s wins because of better reasoning!", player2Username));
        } else if (player1Time.equals(player2Time) & player1Turns < player2Turns) {
            winMessage.setText(String.format("%s wins because of better reasoning!", player1Username));
        } else if (player1Time > player2Time) {
            winMessage.setText(String.format("%s wins because of faster reasoning!", player2Username));
        } else {
            winMessage.setText(String.format("%s wins because of faster reasoning!", player1Username));
        }
        winMessage.setGravity(Gravity.CENTER);
    }

    /**
     * Method for the back to menu button
     */
    public void menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method for the play Cows and Bulls again button
     */
    public void playAgain(View view) {
        Intent intent = new Intent(this, CowsBullsStartActivity.class);
        startActivity(intent);
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
     * Method to specify what to do when android back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
