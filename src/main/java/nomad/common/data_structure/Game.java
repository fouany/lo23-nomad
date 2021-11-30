package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Game extends Observable implements Serializable  {

    private UUID gameId;
    private Player host;
    private Player opponent;
    private int nbOfTowers;
    private String name;
    private GameParameters gameParameters;
    private Board board;
    private UUID currentPlayer;
    private boolean gameLaunched;
    private boolean gameEnded;
    private ObservableList<Move> moves;
    private ObservableList<UserLight> spect;
    private ObservableList<Message> chat;

    public Game(Player host, int nbOfTowers, String name, GameParameters gameParameters) {
        this.gameId = UUID.randomUUID();
        this.host = host;
        this.nbOfTowers = nbOfTowers;
        this.name = name;
        this.board = new Board();
        this.currentPlayer = host.getId();
        this.gameParameters = gameParameters;
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

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public int getNbOfTowers() {
        return nbOfTowers;
    }

    public void setNbOfTowers(int nbOfTowers) {
        this.nbOfTowers = nbOfTowers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpectAllowed() {
        return gameParameters.isSpectAllowed();
    }

    public void setSpectAllowed(boolean spectAllowed) {
        this.gameParameters.setSpectAllowed(spectAllowed);
    }

    public boolean isSpectChatAllowed() {
        return gameParameters.isSpectChatAllowed();
    }

    public void setSpectChatAllowed(boolean spectChatAllowed) {
        this.gameParameters.setSpectChatAllowed(spectChatAllowed);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UUID getCurrentPlayerUUID() {
        return currentPlayer;
    }

    public void setCurrentPlayer(UUID currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void changeCurrentPlayer(){
        UUID currentPlayerUUID = getCurrentPlayerUUID();
        UUID opponentUUID = getOpponent().getId();
        UUID hostUUID = getHost().getId();

        if (hostUUID == currentPlayerUUID) {
            setCurrentPlayer(opponentUUID);
        } else {
            setCurrentPlayer(hostUUID);
        }
    }


    public boolean isHostColor() {
        return gameParameters.isHostColor();
    }

    public void setHostColor(boolean hostColor) {
        this.gameParameters.setHostColor(hostColor);
    }

    public boolean isGameLaunched() {
        return gameLaunched;
    }

    public void setGameLaunched(boolean gameLaunched) {
        this.gameLaunched = gameLaunched;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public ObservableList<Move> getMoves() {
        return moves;
    }

    public ObservableList<UserLight> getSpect() {
        return spect;
    }

    public ObservableList<Message> getChat() {
        return chat;
    }

    public void addMessage(Message message) {
        chat.add(message);
    }

    public void addSpec(UserLight spect) {
        this.spect.add(spect);
    }

    public GameLight createGameLight(){
        return new GameLight(gameId, host, nbOfTowers);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", host=" + host +
                ", opponent=" + opponent +
                ", nbOfTowers=" + nbOfTowers +
                ", name='" + name + '\'' +
                ", spectAllowed=" + gameParameters.isSpectAllowed() +
                ", spectChatAllowed=" + gameParameters.isSpectChatAllowed() +
                ", board=" + board +
                ", currentPlayer=" + currentPlayer +
                ", hostColor=" + gameParameters.isHostColor() +
                ", gameLaunched=" + gameLaunched +
                ", gameEnded=" + gameEnded +
                ", moves=" + moves +
                ", spect=" + spect +
                ", chat=" + chat +
                '}';
    }

    public void removeOpponent() { this.opponent = null; }

    public List<UUID> getListOther() {
        List<UUID> listOther = null;
        listOther.add(this.opponent.getId());
        for (UserLight ul : this.spect){
            listOther.add(ul.getId());
        }
        return listOther;
    }
}
