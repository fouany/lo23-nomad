package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

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
    private int nbOfTilesPlayed;
    private int nbOfTowersPlayed;
    private ObservableList<Move> moves;
    private ObservableList<UserLight> spect;
    private ObservableList<Message> chat;
    private ObservableList<Tower> towers;
    private int nbRounds = 0;

    public Game(Player host, int nbOfTowers, String name, GameParameters gameParameters) {
        this.gameId = UUID.randomUUID();
        this.host = host;
        this.nbOfTowers = nbOfTowers;
        this.name = name;
        this.board = new Board();
        this.currentPlayer = host.getId();
        this.gameParameters = gameParameters;
        this.nbOfTilesPlayed=0;
        this.nbOfTowersPlayed=0;
        this.spect = FXCollections.observableArrayList(new ArrayList<>());
        this.moves = FXCollections.observableArrayList(new ArrayList<>());
        this.chat = FXCollections.observableArrayList(new ArrayList<>());
        this.towers = FXCollections.observableArrayList(new ArrayList<>());
    }

    public int getNbOfTilesPlayed() {
        return nbOfTilesPlayed;
    }

    public void addMove(Move m){
        moves.add(m);
        if ( m instanceof Tower ){
            towers.add((Tower) m);
            board.updateBoard((Tower) m);
        } else if (m instanceof Tile){
            nbOfTilesPlayed ++;
            board.updateBoard((Tile) m);
        }
        this.incrementNbRounds();
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public GameParameters getGameParameters() {
        return gameParameters;
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

        if (hostUUID.equals(currentPlayerUUID)){
            System.out.println("Opponent turn");
            setCurrentPlayer(opponentUUID);
        } else {
            System.out.println("Host turn");
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
        // Those force updates should not be here (could be link to a sync problem from the modal)
        setChanged();
        notifyObservers();
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

    public void setMoves(ObservableList<Move> moves) {
        this.moves = moves;
    }

    public ObservableList<UserLight> getSpect() {
        return this.spect;
    }

    public void setSpect(ObservableList<UserLight> spect) {
        this.spect = spect;
    }

    public ObservableList<Message> getChat() {
        return this.chat;
    }

    public void setChat(ObservableList<Message> chat) {
        this.chat = chat;
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

    public boolean areAllTowersConnected(){
        return false;
    }

    public int getNbRounds(){
        return this.nbRounds;
    }

    public void incrementNbRounds(){
        this.nbRounds++;
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
        List<UUID> listOther = new ArrayList<>();
        listOther.add(this.opponent.getId());
        for (UserLight ul : this.spect){
            listOther.add(ul.getId());
        }
        return listOther;
    }

    public GameSerializable getGameSerializable() {
        return new GameSerializable(this);
    }

    public ObservableList<Tower> getTowers() { return this.towers;
    }

    public void setTowers(ObservableList<Tower> towers) {
        this.towers = towers;
    }

    public void setNbOfTilesPlayed(int nbOfTilesPlayed) { this.nbOfTilesPlayed = nbOfTilesPlayed; }

    public int getNbOfTowersPlayed () { return this.nbOfTowersPlayed;}

    public void setNbOfTowersPlayed(int nbOfTowersPlayed) { this.nbOfTowersPlayed = nbOfTowersPlayed; }

    public void addTowerPlayed() {
        this.nbOfTowersPlayed = this.nbOfTowersPlayed + 1;
    }

    public boolean getCurrentPlayerColor() {
        boolean color;
        if (host.getId().equals(currentPlayer)) {
            color = isHostColor();
        } else {
            color = !isHostColor();
        }

        return color;
    }

    public boolean switchTowerTile(){
        return getNbOfTowersPlayed()==getNbOfTowers();
    }
}
