package com.example.game.services.settings;

import android.content.Context;
import android.util.Log;

import com.example.game.data.Setting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.game.data.GameConstants.SETTINGS_FILE_NAME;
import static com.example.game.data.GameConstants.USERS_DIR_NAME;

/**
 * An implementation of SettingsManager that accesses the users settings file stored on the device
 * for querying and updating settings
 */
class UserSettingsManager implements SettingsManager {
    /**
     * This class's tag for logging things
     */
    private String tag = "com.example.game.services.settings.UserSettingsManager";

    /**
     * The file containing the user's settings
     */
    private File settingsFile;

    /**
     * Create a new UserSettingsManager for the user with the given username
     *
     * @param context  - the calling context
     * @param username - the user's username
     */
    UserSettingsManager(Context context, String username) {
        // Open the user's settings file and store it in settingsFile
        settingsFile = userSettingsFile(context, username);
    }

    /**
     * Return a File object for the given user's settings file
     *
     * @param context  - the context that created this object
     * @param username - the username of the user
     * @return a File object that contains the given user's settings file
     */
    private File userSettingsFile(Context context, String username) {
        // Navigate to rootDirectoryOfApp/USERS_DIR_NAME/username/SETTINGS_FILE_NAME, opening
        // the user's settings file
        File rootDir = context.getDir(USERS_DIR_NAME, 0);
        Log.i(tag, "" + rootDir.getName());
        File usersDir = new File(rootDir, username);
        return new File(usersDir, SETTINGS_FILE_NAME);
    }

    /**
     * Takes in a setting category and returns the user's particular setting value for that category
     *
     * @param setting - the setting to be queried
     * @return an integer corresponding to the user's setting, or -1 if a value could not be found
     */
    public int getSetting(Setting setting) {
        try {
            Scanner scanner = new Scanner(settingsFile);
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (getSettingKey(line).equals(setting.getKey())) {
                    return getSettingValue(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            Log.e(tag, "Could not access users setting file");
            e.printStackTrace();
        }

        // Return -1 if there was an error or the setting could not be found
        return -1;
    }

    /**
     * Takes a setting and a new value for that setting and updates the user's settings file accordingly
     *
     * @param setting  - the setting whose value is to be updated
     * @param newValue - the new value for the given setting
     */
    public void updateSetting(Setting setting, int newValue) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(settingsFile);
            String curr;
            while (scanner.hasNext()) {
                curr = scanner.nextLine();
                if (!curr.equals("")) {
                    lines.add(curr);
                }

            }

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (getSettingKey(line).equals(setting.getKey())) {
                    lines.set(i, setting.getKey() + "=" + newValue + "\n");
                }
            }
            scanner.close();

            OutputStream outputStream = new FileOutputStream(settingsFile, false);
            for (String line : lines) {
                outputStream.write((line + "\n").getBytes());
            }

            outputStream.close();
        } catch (IOException e) {
            Log.e(tag, "Failed to open settingsFile for editing with scanner");
        }

    }

    /**
     * Takes a line from the settings file and extracts the setting correspond to that line from the string
     * For example:
     * getSettingKey("NumHands=4") -> "NumHands"
     * getSettingKey("DarkMode=1") -> "DarkMode"
     *
     * @param line - the string to be parsed for a setting
     * @return an empty string if this String does not contain an equals, a substring of all
     * characters preceding the equals otherwise (this is  the format of a line from the settings file)
     */
    private String getSettingKey(String line) {
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
     * Takes a line from the settings file and returns the value stored on that line
     * For example:
     * getSettingKey("NumHands=4") -> 4
     * getSettingKey("DarkMode=1") -> 1
     *
     * @param line - the line to be parsed for a value
     * @return returns -1 if a proper integer cannot be parsed from the given String, returns the
     * setting value from the given line otherwise
     */
    private int getSettingValue(String line) {
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
}
