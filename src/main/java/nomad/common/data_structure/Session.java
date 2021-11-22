package nomad.common.data_structure;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Session extends Observable {

    private List<UserLight> connectedUsers;
    private List<GameLight> gamesInLobby;
    private List<GameLight> gamesInPlay;

    public Session(List<UserLight> connectedUsers, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay) {
        this.connectedUsers = connectedUsers;
        this.gamesInLobby = gamesInLobby;
        this.gamesInPlay = gamesInPlay;
    }

    public List<UserLight> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<UserLight> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public List<GameLight> getGamesInLobby() {
        return gamesInLobby;
    }

    public void setGamesInLobby(List<GameLight> gamesInLobby) {
        this.gamesInLobby = gamesInLobby;
    }

    public List<GameLight> getGamesInPlay() {
        return gamesInPlay;
    }

    public void setGamesInPlay(List<GameLight> gamesInPlay) {
        this.gamesInPlay = gamesInPlay;
    }

    public GameLight getGameInLobbyById(UUID gameId){
        GameLight tempGameLight = null;
        for (int i = 0; i < gamesInLobby.size(); i++) {
            if (gamesInLobby.get(i).getGameId() == gameId) tempGameLight = gamesInLobby.get(i);
        }
        return tempGameLight;
    }

    public GameLight getGameInPlayById(UUID gameId){
        GameLight tempGameLight = null;
        for (int i = 0; i < gamesInPlay.size(); i++) {
            if (gamesInPlay.get(i).getGameId() == gameId) tempGameLight = gamesInPlay.get(i);
        }
        return tempGameLight;
    }

    @Override
    public String toString() {
        return "model.Session{" +
                "connectedUsers=" + connectedUsers +
                ", gamesInLobby=" + gamesInLobby +
                ", gamesInPlay=" + gamesInPlay +
                '}';
    }
}
