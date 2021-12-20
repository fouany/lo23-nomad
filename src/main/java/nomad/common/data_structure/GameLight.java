package nomad.common.data_structure;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a lighter version of game
 * Useful to limit data usage during client-server communication
 */
public class GameLight implements Serializable {

    /**
     * ID of the game
     */
    private UUID gameId;

    /**
     * Host of the game as a Player Object
     */
    private Player host;

    /**
     * Number of towers in the game
     */
    private int nbOfTowers;

    /**
     * GameLight constructor
     * @param gameId - Game ID
     * @param host - Game host
     * @param nbOfTowers - Number of towers in game (default is 5)
     */
    public GameLight(UUID gameId, Player host, int nbOfTowers) {
        this.gameId = gameId;
        this.host = host;
        this.nbOfTowers = nbOfTowers;
    }

    /**
     * Returns the game ID
     * @return game ID as a UUID
     */
    public UUID getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID
     * @param gameId - Game ID as a UUID
     */
    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    /**
     * Returns game host
     * @return game host as a Player object
     */
    public Player getHost() {
        return host;
    }

    /**
     * Sets the game host
     * @param host - game host as a Player object
     */
    public void setHost(Player host) {
        this.host = host;
    }

    /**
     * Returns the number of towers in total
     * @return number of towers in total as an int
     */
    public int getNbOfTowers() {
        return nbOfTowers;
    }

    /**
     * Sets the number of towers in total
     * @param nbOfTowers - number of towers in total as an int
     */
    public void setNbOfTowers(int nbOfTowers) {
        this.nbOfTowers = nbOfTowers;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "GameLight{" +
                "gameId=" + gameId +
                ", host=" + host +
                ", nbOfTowers=" + nbOfTowers +
                '}';
    }
}
