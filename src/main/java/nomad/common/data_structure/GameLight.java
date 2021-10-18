package model;

import java.io.Serializable;
import java.util.UUID;

public class GameLight implements Serializable {

    private UUID gameId;
    private Player host;
    private int nbOfTowers;

    public GameLight(UUID gameId, Player host, int nbOfTowers) {
        this.gameId = gameId;
        this.host = host;
        this.nbOfTowers = nbOfTowers;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public int getNbOfTowers() {
        return nbOfTowers;
    }

    public void setNbOfTowers(int nbOfTowers) {
        this.nbOfTowers = nbOfTowers;
    }

    @Override
    public String toString() {
        return "GameLight{" +
                "gameId=" + gameId +
                ", host=" + host +
                ", nbOfTowers=" + nbOfTowers +
                '}';
    }
}
