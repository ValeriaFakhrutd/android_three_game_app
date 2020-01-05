package com.example.game.services.accounts;

import android.content.Context;
import android.util.Log;

import com.example.game.data.Setting;
import com.example.game.data.Statistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.game.data.GameConstants.PASSWORD_FILE_NAME;
import static com.example.game.data.GameConstants.SETTINGS_FILE_NAME;
import static com.example.game.data.GameConstants.STATS_FILE_NAME;
import static com.example.game.data.GameConstants.USERS_DIR_NAME;

/**
 * An implementation of AccountManager that uses a folder structures like the following:
 * app_root_directory\
 *  users\
 *      lukas
 *          settings
 *          stats
 *          password
 *      peter
 *          settings
 *          stats
 *          password
 * to query and create users and their information.
 * Please note that we know its bad to store passwords in plain text, we will work on fixing this
 * for phase 2.
 */
public class UserAccountManager implements AccountManager {
    /**
     * This class's tag for logging events
     */
    private static String tag = "com.example.game.services.accounts.UserAccountManager";

    /**
     * File object representing the "users" directory containing all the user accounts and
     * their data
     */
    private File usersDir;

    /**
     * Create a new UserAccountManager from the given context
     *
     * @param context - the context in which this AccountManager is being created
     */
    public UserAccountManager(Context context) {
        usersDir = context.getDir(USERS_DIR_NAME, 0);
    }

    /**
     * Check if the given username is associated with an account
     *
     * @param username - the username to be checked
     * @return true if the the username is already associated with an account
     * false otherwise
     */
    public boolean usernameExists(String username) {
        File[] users = usersDir.listFiles();
        for (File userDir : users) {
            if (userDir.getName().equals(username)) {
                return true;
            }
        }

        Log.i(tag, "Username does not exist");
        return false;
    }

    /**
     * Records that a new user with the given username and password has
     * been created by creating a folder for them and filling their
     * files with the default values
     * <p>
     * Precondition: the given username is not already associated with an account;
     * userNamExists(username) has been called and returned false
     *
     * @param username - the username of the account to be created
     * @param password - the password of the account to be created
     */
    public void createNewUser(String username, String password) {
        File newUserDir = new File(usersDir, username);
        newUserDir.mkdir();

        File settings = new File(newUserDir, SETTINGS_FILE_NAME);
        File stats = new File(newUserDir, STATS_FILE_NAME);
        File passwordFile = new File(newUserDir, PASSWORD_FILE_NAME);

        fillDefaultValues(settings, stats, passwordFile, password);
    }

    /**
     * Populate the given files with the default values for a new user
     *
     * @param settingsFile - the file to fill with the default settings
     * @param statsFile    - the file to fill with the default stats
     * @param passwordFile - the file to put the user's password in
     * @param password     - the password of the new user
     */
    private void fillDefaultValues(File settingsFile, File statsFile, File passwordFile, String password) {
        OutputStream settingStream = null;
        OutputStream statsStream = null;
        OutputStream passwordStream = null;
        try {
            settingStream = new FileOutputStream(settingsFile);
            statsStream = new FileOutputStream(statsFile);
            passwordStream = new FileOutputStream(passwordFile);

            for (Setting setting : Setting.values()) {
                settingStream.write((setting.getKey() + "=" + setting.getDefaultValue() + "\n").getBytes());
            }
            for (Statistic stat : Statistic.values()) {
                statsStream.write((stat.getKey() + "=" + stat.getValue() + "\n").getBytes());
            }
            passwordStream.write(password.getBytes());
        } catch (IOException e) {
            Log.e(tag, "Failed to create default files for user");
        } finally {
            try {
                if (settingStream != null) {
                    settingStream.close();
                }
                if (statsStream != null) {
                    statsStream.close();
                }
                if (passwordStream != null) {
                    passwordStream.close();
                }
            } catch (IOException e) {
                Log.e(tag, "Failed to close FileOutputStream");
            }
        }
    }

    /**
     * Check if the given username and name are, together, associated with an account
     * i.e. check if the given username and password are a valid login for an account
     *
     * @param username - the username to be checked
     * @param password - the name to be checked
     * @return true if there is an account under the given username with the given name
     * false otherwise
     */
    public boolean validCredentials(String username, String password) {
        return usernameExists(username) && passwordIsValid(username, password);
    }

    @Override
    public boolean validNewCredentials(String username, String password) {
        // Return true only if the password is non-empty and the username does not contain "="
        // or "," as this has the potential to mess up the various file systems in the game
        return password.length() > 0 && !username.contains("=") && !username.contains(",");
    }

    /**
     * Check if the account associated with the given username has password as given by the other
     * parameter
     * <p>
     * Precondition: there is an account associated with the given username. usernameExists(username)
     * has been called and returned true
     *
     * @param username - the username to check
     * @param password - the password to check
     * @return true if the password associated with the given username is the same as the parameter password
     * false otherwise
     */
    private boolean passwordIsValid(String username, String password) {
        File userFolder = new File(usersDir, username);
        File passwordFile = new File(userFolder, PASSWORD_FILE_NAME);

        boolean validPassword = false;
        try {
            Scanner scanner = new Scanner(passwordFile);
            String passwordFromFile = scanner.nextLine();
            if (password.equals(passwordFromFile)) {
                validPassword = true;
                Log.i(tag, "Password is valid!");
            }
        } catch (IOException e) {
            Log.e(tag, "Failed to read user's password file");
        }

        return validPassword;
    }

    /**
     * Get a list of all the usernames that have accounts registered under them
     *
     * Implements method in AccountManager
     *
     * @return - a list of all usernames that have accounts registered under them
     */
    public List<String> getUsers(){
        List<String> users = new ArrayList<>();

        for(File file : usersDir.listFiles()){
            users.add(file.getName());
        }

        return users;
    }
}
