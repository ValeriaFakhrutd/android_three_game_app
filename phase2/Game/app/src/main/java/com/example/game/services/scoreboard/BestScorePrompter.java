package com.example.game.services.scoreboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.game.R;

/**
 * A class that will allow the user to add a new score to the list of best scores.
 */
public class BestScorePrompter {

    // The game that BestScorePrompter will be based on.
    private ScoreboardRepository.Game game;

    // The Intent to start after the user has decided whether to add their score.
    private final Intent intent;

    // The Context from which this class is being accessed.
    private Context context;

    // The score was made.
    private int score;

    // The username of the person playing the game.
    private String username;

    /**
     * @param score  - the score to prompt the user to save
     * @param intent - the intent to start after prompting the user
     * @param game   - the game that is being played
     */
    public BestScorePrompter(int score, String username, final Intent intent, Context context, ScoreboardRepository.Game game) {
        this.score = score;
        this.username = username;
        this.intent = intent;
        this.context = context;
        this.game = game;
    }

    /**
     * Prompt the user to save their score, and start the given intent after they've made a decision.
     */
    public void promptForScore() {

        final ScoreboardRepository scoreManager = new ScoreboardRepositoryFactory().build(game);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(R.layout.highscore_prompt_dialog)
                .setPositiveButton("YES", null)
                .setNegativeButton("NO", null)
                .create();

        String message;

        switch (game) {
            case BLACKJACK:
                message = "You just set a high score! Type your name below to save your score :  " + score;
                break;
            case COWS_AND_BULLS:
            case GUESS_THE_NUMBER:
                message = "Wow! That was a great score. Type your name below to save your score :  " + score;
                break;
            default:
                message = "You just set a top score! Type your name below to save your score :  " + score;
        }

        final String warning = "That is an invalid name! Please do not include a comma in your name.";

        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final Button yesButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                final Button noButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // Fill the text box with the player's username by default
                final EditText inputBox = dialog.findViewById(R.id.highscoreName);
                if (inputBox != null) {
                    inputBox.setText(username);
                }

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name;
                        if (inputBox != null) {
                            name = (inputBox).getText().toString();
                        } else {
                            name = username;
                        }
                        if (scoreManager.validName(name)) {
                            scoreManager.addScore(name, score);
                            context.startActivity(intent);
                        } else {
                            dialog.setMessage(warning);
                        }
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(intent);
                    }
                });
            }
        });

        dialog.show();
    }


}