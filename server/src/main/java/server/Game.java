package server;

import java.util.HashSet;

/**
 * This is a game class that works with everything
 * within a game
 */
public class Game {
    /**
     * The game id
     */
    private final int id;

    /**
     * Names of all players
     */
    private HashSet<String> playerNames;

    /**
     * Initialize the game with its id
     * @param _id
     */
    public Game(int _id) {
        this.id = _id;
        this.playerNames = new HashSet<>();
    }

    /**
     * Get the game id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Check whether the game room is already full
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return (playerNames.size() >= 2);
    }

    /**
     * Check whether a player is in this game
     * @param toCheck is the player name to check
     * @return true if in the game, false otherwise
     */
    public boolean containsPlayer(String toCheck) {
        return playerNames.contains(toCheck);
    }

    /**
     * Add a player into the game
     * @param playerName is the player to add
     * @return the result, null for success
     */
    public String acceptPlayer(String playerName) {
        if (isFull()) {
            return "The game room is already full";
        }
        else if (containsPlayer(playerName)) {
            return "The player is already in the game";
        }
        playerNames.add(playerName);
        return null;
    }
}
