package com.example.game.services.multiplayer_data;

import android.util.Log;

import com.example.game.data.GameData;
import com.example.game.data.MultiplayerDoubleData;
import com.example.game.data.MultiplayerIntData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplayerDataManagerFactory {
    /**
     * A boolean keeping track of whether or not this factory has been used to create an object yet,
     * during any given run of the game. This allows the Factory to know whether or not it should
     * initialize the file it is giving to the MultiplayerFileDataManager
     */
    private static boolean first = true;

    /**
     * Builds and returns a MultiplayerDataManager
     *
     * @return - a MultiplayerDataManager
     */
    public MultiplayerDataManager build() {
        File multiplayerDataFile = getDataFile();

        return new MultiplayerFileDataManager(multiplayerDataFile);
    }

    /**
     * Creates the File object corresponding to the file on disk that the MultiplayerFileDataManager
     * will be using
     *
     * @return - a File for the MultiplayerDataManager to use
     */
    private File getDataFile() {
        File filesDir = new File(GameData.filesDirPath);

        File dataFile = new File(filesDir, "multiplayer_data");

        // If the data file exists, we need too check if this is the first time this class has been
        // asked to make a MultiplayerDataManager; if so, dataFile represents the multiplayer data file
        // from a previous run of the game, and we need to overwrite it to get it ready for this game
        if (dataFile.exists()) {
            if (first) {
                initializeDataFile(dataFile);
                first = false;
            }
        }
        // If the dataFile does not exist, we need to create it and fill it with default values
        else {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                Log.e("MultiplayerDataManagerFactory", "Failed to create dataFile");
            }

            initializeDataFile(dataFile);
            first = false;
        }


        return dataFile;
    }

    /**
     * Takes in a file object and sets it up as an initial multiplayer data file.
     * <p>
     * That is, fills it with all the keys and default values of the data that the
     * MultiplayerFileDataManager is going to be storing
     *
     * @param dataFile - the File to initialize
     */
    private void initializeDataFile(File dataFile) {
        try {
            FileOutputStream stream = new FileOutputStream(dataFile);

            for (MultiplayerIntData dataType : MultiplayerIntData.values()) {
                stream.write((dataType.getKey() + "=" + dataType.getDefaultValue() + "\n").getBytes());
            }

            for (MultiplayerDoubleData dataType : MultiplayerDoubleData.values()) {
                stream.write((dataType.getKey() + "=" + dataType.getDefaultValue() + "\n").getBytes());
            }
            stream.close();
        } catch (IOException e) {
            Log.e("MultiplayerDataManagerFactory", "Failed to initialize data file");
        }
    }
}
