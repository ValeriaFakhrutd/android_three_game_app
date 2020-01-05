package com.example.game.data;

/**
 * A class for storing critical multiplayer data while the game is running
 * Allows player 1 and player 2's usernames to be easily updated and accessed at runtime
 */
public class MultiplayerGameData {
    private static String player1Username = null;
    private static String player2Username = null;

    public static void setPlayer1Username(String newName) {
        player1Username = newName;
    }

    public static void setPlayer2Username(String newName) {
        player2Username = newName;
    }

    public static String getPlayer1Username() {
        return player1Username;
    }

    public static String getPlayer2Username() {
        return player2Username;
    }
}
