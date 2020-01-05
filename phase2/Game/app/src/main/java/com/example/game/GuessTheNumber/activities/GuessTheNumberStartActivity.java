package com.example.game.GuessTheNumber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.GuessTheNumber.domain.GuessTheNumberRound;
import com.example.game.GuessTheNumber.game_logic.GuessTheNumberGameManager;
import com.example.game.R;
import com.example.game.data.GameData;
import com.example.game.data.MultiplayerGameData;
import com.example.game.data.MultiplayerIntData;
import com.example.game.data.Setting;
import com.example.game.services.multiplayer_data.MultiplayerDataManager;
import com.example.game.services.multiplayer_data.MultiplayerDataManagerFactory;
import com.example.game.services.settings.SettingsManager;
import com.example.game.services.settings.SettingsManagerFactory;

/**
 * The activity that appears right before the user is about to start a game of GuessTheNumber. This
 * activity is needed to resume the old game/read the rules/start a new game.
 */
public class GuessTheNumberStartActivity extends AppCompatActivity {
    /**
     * gameManger is responsible for the whole logic of the game, passes values to GuessTheNumberPlayActivity
     * and GuessTheNumberFinishActivity.
     */
    static GuessTheNumberGameManager gameManager;
    /**
     * multiplayerDataManager for this game.
     */
    private MultiplayerDataManager multiplayerDataManager;
    /**
     * keeping username of the user playing this game.
     */
    String username = GameData.USERNAME;
    /**
     * Rules appear if user wants to see that.
     */
    boolean rulesAppear = false;
    /**
     * SettingManager for the user.
     */
    SettingsManager settingsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start_activity);
        settingsManager = new SettingsManagerFactory().build(this, username);
        int range = settingsManager.getSetting(Setting.GUESS_THE_NUMBER_RANGE);
        gameManager = new GuessTheNumberGameManager(range);
        multiplayerDataManager = new MultiplayerDataManagerFactory().build();
        boolean firstPlayersTurn = gameManager.getIsFirstPlayersTurn();

        if (GameData.MULTIPLAYER && firstPlayersTurn) {
            resetFewestGuesses();
            updateTurnText(MultiplayerGameData.getPlayer1Username());
            gameManager.resetGameManager();
        } else if (GameData.MULTIPLAYER && !firstPlayersTurn) {
            updateTurnText(MultiplayerGameData.getPlayer2Username());
            gameManager.startNewRound();
        }

        this.displayResumeButton();
        this.setNumRounds();
    }

    /**
     * When a user clicks the start button, the GuessTheNumberPlayActivity activity appears and the user
     * proceeds to start a new game of GuessTheNumber
     */
    public void startTheGame(View view) {
        gameManager.resetCurrentRounds();
        gameManager.startNewRound();
        Intent intent = new Intent(this, GuessTheNumberPlayActivity.class);
        startActivity(intent);
    }

    public void selectDifficultyClick(View view){
        Intent intent = new Intent(this, GuessTheNumberSelectDifficultyActivity.class);
        startActivity(intent);
    }
    /**
     * When a user clicks on the resume button, the GameActivity1 activity appears with the stats of
     * the game that the user has previously paused on. However, on multiplayer mode, the user does
     * not get to resume the game if they exited their session.
     */
    public void resumeGame(View view) {
        Intent intent = new Intent(this, GuessTheNumberPlayActivity.class);
        startActivity(intent);
    }

    /**
     * The user can click the rules button to view the rules of GuessTheNumber, or click it again to
     * hide the rules of the game.
     */
    public void rules(View view) {
        TextView rulesText = findViewById(R.id.rulesText);
        if (!rulesAppear) {
            rulesText.setVisibility(View.VISIBLE);
            rulesAppear = true;
        } else {
            rulesText.setVisibility(View.INVISIBLE);
            rulesAppear = false;
        }
    }

    /**
     * Check if the appearance of resume button is needed. Resume button appears iff the is a game
     * which is not finished or a user has already played at least one round but hasn't finished
     * all the rounds.
     */
    private void displayResumeButton() {
        Button btn = findViewById(R.id.resumeGame);
        GuessTheNumberRound game = gameManager.getCurrentRound();
        if (game.isFinished() || (game.getPoints() == 0 && gameManager.getCurrentRoundIndex() == 0)) {
            btn.setVisibility(View.INVISIBLE);
        } else {
            btn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set the number of rounds user wants to play based on customized settings.
     */
    private void setNumRounds() {
        SettingsManager manager = new SettingsManagerFactory().build(this, username);
        gameManager.setRoundsToPlay(manager.getSetting(Setting.NUM_ROUNDS));
    }

    /**
     * Update the text depending on whose turn it is. This is only applicable in multiplayer mode.
     */
    private void updateTurnText(String player) {
        String turnText;
        turnText = "It is " + formatName(player) + "'s turn.";
        ((TextView) findViewById(R.id.turnText)).setText(turnText);
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
     * Reset the player1's and player2's fewest guesses.
     */
    private void resetFewestGuesses() {
        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_1_FEWEST_GUESSES, 9999);
        multiplayerDataManager.setMultiplayerData(MultiplayerIntData.GUESS_THE_NUM_PLAYER_2_FEWEST_GUESSES, 9999);
    }

}
