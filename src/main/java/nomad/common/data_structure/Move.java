package nomad.common.data_structure;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Observable;

/**
 * Represents a move
 * Tower, Skip and Tile inherits from this class
 */
public abstract class Move extends Observable implements Serializable {

    /**
     * Timestamp of the move
     */
    private Timestamp s;

    /**
     * ID of the user who played the move
     */
    private UUID userId;

    /**
     * Game ID
     */
    private UUID gameId;

    /**
     * Restricts the creation of a move without arguments
     */
    protected Move(){}

    /**
     * Move constructor
     * @param s - Timestamp
     * @param userId - User ID
     * @param gameId - Game ID
     */
    protected Move(Timestamp s, UUID userId, UUID gameId) {
        this.s = s;
        this.userId = userId;
        this.gameId = gameId;
    }

    /**
     * Returns user ID
     * @return user ID as a UUID
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets user ID
     * @param userId - User ID as a UUID
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns the associated game ID
     * @return game ID as a UUID
     */
    public UUID getGameId() {
        return gameId;
    }

    /**
     * Sets the associated game ID
     * @param gameId - Game ID as a UUID
     */
    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    /**
     * Returns the timestamp of the message
     * @return the timestamp of the message
     */
    public Timestamp getS() {
        return s;
    }

    /**
     * Sets the timestamp of the message
     * @param s - Timestamp of the message as a Timestamp
     */
    public void setS(Timestamp s) {
        this.s = s;
    }

    abstract public String idMove();

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Move{" +
                "s=" + s +
                '}';
    }
}
