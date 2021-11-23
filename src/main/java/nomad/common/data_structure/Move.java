package nomad.common.data_structure;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public abstract class Move implements Serializable {

    private Timestamp s;
    private UUID userId;
    private UUID gameId;

    public Move(){}

    public Move(Timestamp s, UUID userId, UUID gameId) {
        this.s = s;
        this.userId = userId;
        this.gameId = gameId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public Timestamp getS() {
        return s;
    }

    public void setS(Timestamp s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Move{" +
                "s=" + s +
                '}';
    }
}
