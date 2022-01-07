package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

/**
 * Represents a session when connected to a server
 */
public class Session extends Observable {

    /**
     * List of users connected to the session
     */
    private ObservableList<UserLight> connectedUsers;

    /**
     * List of games pending for players
     */
    private ObservableList<GameLight> gamesInLobby;

    /**
     * List of games in play
     */
    private ObservableList<GameLight> gamesInPlay;

    /**
     * Session constructor
     * @param connectedUsers - List of users connected to the session
     * @param gamesInLobby - List of games pending for players
     * @param gamesInPlay - List of games in play
     */
    public Session(List<UserLight> connectedUsers, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay) {
        this.connectedUsers = FXCollections.observableArrayList(connectedUsers);
        this.gamesInLobby = FXCollections.observableArrayList(gamesInLobby);
        this.gamesInPlay = FXCollections.observableArrayList(gamesInPlay);
    }

    /**
     * Gets the list of session connected user
     * @return the list of session connected user as a list of UserLight
     */
    public ObservableList<UserLight> getConnectedUsers() {
        return connectedUsers;
    }

    /**
     * Gets the list of games pending for players
     * @return the list of games pending for players as a list of GameLight
     */
    public ObservableList<GameLight> getGamesInLobby() {
        return gamesInLobby;
    }

    /**
     * Gets the list of games in play
     * @return the list of games in play as a list of GameLight
     */
    public ObservableList<GameLight> getGamesInPlay() {
        return gamesInPlay;
    }


    /**
     * Checks if a certain game is present in the lobby
     * @param gameId - Game ID of the wanted game
     * @return null if the game is not present, a GameLight representation of the game if it is present in lobby
     */
    public GameLight getGameInLobbyById(UUID gameId){
        GameLight tempGameLight = null;
        for (int i = 0; i < gamesInLobby.size(); i++) {
            if (gamesInLobby.get(i).getGameId().equals(gameId)) tempGameLight = gamesInLobby.get(i);
        }
        return tempGameLight;
    }

    /**
     * Checks if a certain game is present in the list of games in play
     * @param gameId - Game ID of the wanted game
     * @return null if the game is not present, a GameLight representation of the game if it is in play in the session
     */
    public GameLight getGameInPlayById(UUID gameId){
        GameLight tempGameLight = null;
        for (int i = 0; i < gamesInPlay.size(); i++) {
            if (gamesInPlay.get(i).getGameId().equals(gameId)) tempGameLight = gamesInPlay.get(i);
        }
        return tempGameLight;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "model.Session{" +
                "connectedUsers=" + connectedUsers +
                ", gamesInLobby=" + gamesInLobby +
                ", gamesInPlay=" + gamesInPlay +
                '}';
    }
}