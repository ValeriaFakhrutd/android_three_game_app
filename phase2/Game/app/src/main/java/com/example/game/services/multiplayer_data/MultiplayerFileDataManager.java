package com.example.game.services.multiplayer_data;

import android.util.Log;

import com.example.game.data.MultiplayerDoubleData;
import com.example.game.data.MultiplayerIntData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiplayerFileDataManager implements MultiplayerDataManager {

    /**
     * The tag this class will use to log messages
     */
    private static final String TAG = "com.example.game.services.multiplayer_data.MultiplayerFileDataManager";

    /**
     * The File where multiplayer data is being stored
     */
    private File multiplayerDataFile;

    /**
     * Create a new MultiplayerFileDataManager, which will be using the given file to write data to
     * and read data from
     *
     * @param file - the file this object will be assuming contains multiplayer data
     */
    MultiplayerFileDataManager(File file) {
        multiplayerDataFile = file;
    }

    /**
     * Set the value of some kind of integer multiplayer data being tracked by the program.
     * <p>
     * Implements method in MultiplayerDataManager
     *
     * @param dataType - an enum value representing which statistic client code would like to update
     * @param newValue - the new value of the statistic
     */
    @Override
    public void setMultiplayerData(MultiplayerIntData dataType, int newValue) {
        editLine(dataType.getKey(), "" + newValue);
    }

    /**
     * Set the value of some kind of decimal multiplayer data being tracked by the program.
     * <p>
     * Implements method in MultiplayerDataManager
     *
     * @param dataType - an enum value representing which statistic client code would like to update
     * @param newValue - the new value of the statistic
     */
    @Override
    public void setMultiplayerData(MultiplayerDoubleData dataType, double newValue) {
        editLine(dataType.getKey(), "" + newValue);
    }

    /**
     * Tries to find a line in the file that starts with "key=", and replaces everything after the
     * equals sign with newValue
     *
     * @param key      - the key to search for in the file
     * @param newValue - the value to write to the file after "key="
     * @return A boolean representing whether or not the edit was successful
     */
    private boolean editLine(String key, String newValue) {
        try {
            List<String> lines = getLines();

            String lineKey;
            String line;
            int index = -1;

            // Find the index such that lines(i) holds the line of the file with key equal to that
            // of the given key
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                lineKey = getKey(line);

                if (lineKey.equals(key)) {
                    index = i;
                    break;
                }
            }

            // If we couldn't find a line with the given key, return false since we failed to edit the line
            if (index == -1) {
                return false;
            }

            lines.set(index, key + "=" + newValue);

            writeLines(lines);

            return true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Couldn't open file");
            return false;
        }
    }

    /**
     * Read all the lines from the multiplayerDataFile and return them as a List of Strings
     *
     * @return - the lines of the multiplayerDataFile as a List of Strings
     * @throws FileNotFoundException - if multiplayerDataFile is not a valid file, this method will
     *                               throw a FileNotFoundException
     */
    private List<String> getLines() throws FileNotFoundException {
        Scanner scanner = new Scanner(multiplayerDataFile);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();

        return lines;
    }

    /**
     * Writes the provided strings to the file, overwriting its existing contents
     *
     * @param lines - the lines to write to the file
     */
    private void writeLines(List<String> lines) {
        try {
            FileOutputStream stream = new FileOutputStream(multiplayerDataFile);
            for (String line : lines) {
                stream.write((line + "\n").getBytes());
            }
            stream.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed to writeLines to file");
        }
    }

    /**
     * Parses the key (everything preceding the equals sign) from the given line of the file
     *
     * @param line - the line of the file to parse
     */
    private String getKey(String line) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                break;
            } else {
                builder.append(line.charAt(i));
            }
        }

        // If the equals sign was the first character of the line, last character of the line,
        // or there wasn't one at all, the input line was invalid
        if (builder.length() == 0 || builder.length() >= line.length() - 1) {
            return "";
        }

        return builder.toString();

    }

    /**
     * Takes a line of the file and parses the value of that line from it
     * For example, parseDouble("winrate=8") -> 8
     *
     * @param line - the line of the file to parse
     * @return the value contained in line, or null if the file is invalidly formatted; that is,
     * it doesn't contain an equals sign, or contains one, but it is in the first or last index of
     * the string
     */
    private Integer parseInt(String line) {
        int index = -1;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                index = i;
                break;
            }
        }

        if (index == -1 || index == line.length() - 1) {
            return null;
        }

        return Integer.parseInt(line.substring(index + 1));
    }

    /**
     * Takes a line of the file and parses the value of that line from it
     * For example, parseDouble("winrate=7.5") -> 7.5
     *
     * @param line - the line of the file to parse
     * @return the value contained in line, or null if the file is invalidly formatted; that is,
     * it doesn't contain an equals sign, or contains one, but it is in the first or last index of
     * the string
     */
    private Double parseDouble(String line) {
        int index = -1;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                index = i;
                break;
            }
        }

        if (index == -1 || index == line.length() - 1) {
            return null;
        }

        return Double.parseDouble(line.substring(index + 1));
    }

    /**
     * Access the value of some kind of integer multiplayer data being tracked by the program.
     * <p>
     * Implements method in MultiplayerDataManager
     *
     * @param dataType - an enum value representing which statistic client code would like to access
     * @return - the value of the requested statistic
     */
    @Override
    public int getMultiplayerData(MultiplayerIntData dataType) {
        String key = dataType.getKey();

        try {
            List<String> lines = getLines();

            String line = "";
            for (String fileLine : lines) {
                if (getKey(fileLine).equals(key)) {
                    line = fileLine;
                }
            }

            Integer value = parseInt(line);

            if (value == null) {
                return -1;
            } else {
                return value;
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Couldn't read from multiplayerDataFile");
            return -1;
        }
    }

    /**
     * Access the value of some kind of decimal multiplayer data being tracked by the program.
     * <p>
     * Implements method in MultiplayerDataManager
     *
     * @param dataType - an enum value representing which statistic client code would like to access
     * @return - the value of the requested statistic
     */
    @Override
    public double getMultiplayerData(MultiplayerDoubleData dataType) {
        String key = dataType.getKey();

        try {
            List<String> lines = getLines();

            String line = "";
            for (String fileLine : lines) {
                if (getKey(fileLine).equals(key)) {
                    line = fileLine;
                }
            }

            Double value = parseDouble(line);

            if (value == null) {
                return -1.0;
            } else {
                return value;
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Couldn't read from multiplayerDataFile");
            return -1.0;
        }
    }
}