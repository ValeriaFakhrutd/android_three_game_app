package com.example.game.services.scoreboard;

import com.example.game.data.GameConstants;
import com.example.game.data.GameData;

import java.io.File;

public class ScoreboardRepositoryFactory {
    /**
     * Creates and returns a ScoreboardRepository object to manage scoreboard data for the given
     * game.
     *
     * @param game - the game whose scoreboard data client code wants to edit/access
     * @return a ScoreboardRepository object prepared to manage scoreboard data for the given game
     */
    public ScoreboardRepository build(ScoreboardRepository.Game game) {
        File filesDir = new File(GameData.filesDirPath);
        File scoreFile = new File(filesDir, filename(game));

        ScoreboardFileManager repository = new ScoreboardFileManager();
        repository.setFile(scoreFile);

        return repository;
    }

    /**
     * Return the name of the file that is being used to store scoreboard data for the given game
     *
     * @param game - the game whose scoreboard data file should be returned
     * @return the name of the file used to store scoreboard data for the given game
     */
    private String filename(ScoreboardRepository.Game game) {
        switch (game) {
            case BLACKJACK:
                return GameConstants.BLACKJACK_HIGHSCORE_FILE;
            case COWS_AND_BULLS:
                return GameConstants.COWS_AND_BULLS_HIGHSCORE_FILE;
            case GUESS_THE_NUMBER:
                return GameConstants.GUESS_THE_NUMBER_HIGHSCORE_FILE;
            default:
                return "";
        }
    }
}

