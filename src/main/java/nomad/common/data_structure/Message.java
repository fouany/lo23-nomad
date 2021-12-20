package nomad.common.data_structure;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Observable;

/**
 * Represents a message in the chat during a game
 */
public class Message extends Observable implements Serializable {

    /**
     * Sender of the message
     */
    private UserLight sender;

    /**
     * Game ID
     */
    private UUID gameId;

    /**
     * Content of the message
     */
    private String content;

    /**
     * Timestamp of the message
     */
    private Timestamp time;

    /**
     * Message constructor
     * @param sender - sender of the message
     * @param gameId - game ID
     * @param content - Content of the message
     * @param time - Timestamp of the message
     */
    public Message(UserLight sender, UUID gameId, String content, Timestamp time) {
        this.sender = sender;
        this.gameId = gameId;
        this.content = content;
        this.time = time;
    }

    /**
     * Returns the sender of the message
     * @return the sender of the message as a UserLight
     */
    public UserLight getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message
     * @param sender - Sender of the message as a UserLight
     */
    public void setSender(UserLight sender) {
        this.sender = sender;
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
     * Gets the content of the message
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message
     * @param content - Content of the message as a String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the timestamp of the message
     * @return the timestamp of the message
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * Sets the timestamp of the message
     * @param time - Timestamp of the message as a Timestamp
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "model.Message{" +
                "sender=" + sender +
                ", gameId=" + gameId +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
