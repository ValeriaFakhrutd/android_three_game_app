package com.example.game.services;

import android.content.Context;
import android.util.Log;

import com.example.game.data.Statistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.game.data.GameConstants.STATS_FILE_NAME;
import static com.example.game.data.GameConstants.USERS_DIR_NAME;

/**
 * An implementation of StatsManager that accesses the users stats file stored on the device
 * for querying and updating stats
 */
public class UserStatsManager implements StatsManager {

    /**
     * This class's tag for logging things
     */
    private String tag = "com.example.game.services.UserStatsManager";

    /**
     * The file containing the user's settings
     */
    private File statsFile;

    /**
     * Create a new UserStatsManager for the user with the given username
     *
     * @param context  - the calling context
     * @param username - the user's username
     */
    UserStatsManager(Context context, String username) {
        // Open the user's settings file and store it in settingsFile
        statsFile = statsFile(context, username);
    }

    /**
     * @param statistic - Statistic enum
     * @return - returns the value associated with the Statistic enum
     */
    @Override
    public int getStat(Statistic statistic) {
        try {
            Scanner scanner = new Scanner(statsFile);
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (getStatsKey(line).equals(statistic.getKey())) {
                    return getStatsValue(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            Log.e(tag, "Could not access users stats file");
            e.printStackTrace();
        }
        return -1;

    }

    /**
     * @param statistic - Statistic enum
     * @param value     - the value to replace current statistic value
     */
    @Override
    public void setStat(Statistic statistic, int value) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(statsFile);
            String curr;
            while (scanner.hasNext()) {
                curr = scanner.nextLine();
                if (!curr.equals("")) {
                    lines.add(curr);
                }
            }

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (getStatsKey(line).equals(statistic.getKey())) {
                    lines.set(i, statistic.getKey() + "=" + value + "\n");
                }
            }
            scanner.close();

            OutputStream outputStream = new FileOutputStream(statsFile, false);
            for (String line : lines) {
                outputStream.write((line + "\n").getBytes());
            }
            outputStream.close();
        } catch (IOException e) {
            Log.e(tag, "Failed to open statsFile");
        }


    }

    /**
     * Takes a line from the stats file and extracts the stats that corresponds to that line from
     * the string
     * For example:
     * getStatsKey("FewestGuesses=4") -> "FewestGuesses"
     * getSettingKey("QuickestTime=1") -> "QuickestTime"
     *
     * @param line - the string to be parsed for a stat
     * @return an empty string if this String does not contain an equals, a substring of all
     * characters preceding the equals otherwise (this is  the format of a line from the settings file)
     */
    private String getStatsKey(String line) {
        Log.i(tag, "Line: \"" + line + "\"");
        int equalsIndex = -1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                equalsIndex = i;
                break;
            }
        }
        if (equalsIndex >= 0) {
            return line.substring(0, equalsIndex);
        }

        return "";

    }

    /**
     * Takes a line from the stats file and returns the value stored on that line
     * For example:
     * getSettingKey("FewestGuesses=4") -> 4
     * getSettingKey("QuickestTime=1") -> 1
     *
     * @param line - the line to be parsed for a value
     * @return returns -1 if a proper integer cannot be parsed from the given String, returns the
     * stats value from the given line otherwise
     */
    private int getStatsValue(String line) {
        int equalsIndex = -1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '=') {
                equalsIndex = i + 1;
                break;
            }
        }
        try {
            return Integer.parseInt(line.substring(equalsIndex));
        } catch (NumberFormatException e) {
            Log.e(tag, "Could not parse setting value from line");

            return -1;
        }
    }

    /**
     * Return a File object of the user's stats file
     *
     * @param context  - the context that created this object
     * @param username - the username of the user
     * @return - File object with user's stats
     */
    private File statsFile(Context context, String username) {
        File rootDir = context.getDir(USERS_DIR_NAME, 0);
        Log.i(tag, "" + rootDir.getName());
        File userDir = new File(rootDir, username);
        return new File(userDir, STATS_FILE_NAME);
    }

}
