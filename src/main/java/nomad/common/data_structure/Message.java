package nomad.common.data_structure;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Observable;

public class Message extends Observable implements Serializable {

    private UserLight sender;
    private UUID gameId;
    private String content;
    private Timestamp time;

    public Message(UserLight sender, UUID gameId, String content, Timestamp time) {
        this.sender = sender;
        this.gameId = gameId;
        this.content = content;
        this.time = time;
    }

    public UserLight getSender() {
        return sender;
    }

    public void setSender(UserLight sender) {
        this.sender = sender;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

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
