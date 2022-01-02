package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Session extends Observable {

    private ObservableList<UserLight> connectedUsers;
    private ObservableList<GameLight> gamesInLobby;
    private ObservableList<GameLight> gamesInPlay;

    public Session(List<UserLight> connectedUsers, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay) {
        this.connectedUsers = FXCollections.observableArrayList(connectedUsers);
        this.gamesInLobby = FXCollections.observableArrayList(gamesInLobby);
        this.gamesInPlay = FXCollections.observableArrayList(gamesInPlay);
    }

    public ObservableList<UserLight> getConnectedUsers() {
        return connectedUsers;
    }

    public ObservableList<GameLight> getGamesInLobby() {
        return gamesInLobby;
    }

    public ObservableList<GameLight> getGamesInPlay() {
        return gamesInPlay;
    }

    public GameLight getGameInLobbyById(UUID gameId){
        GameLight tempGameLight = null;
        for (int i = 0; i < gamesInLobby.size(); i++) {
            if (gamesInLobby.get(i).getGameId().equals(gameId)) tempGameLight = gamesInLobby.get(i);
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
