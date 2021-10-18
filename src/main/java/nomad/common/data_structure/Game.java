package model;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Game extends Observable {

    private UUID gameId;
    private Player host;
    private Player opponent;
    private int nbOfTowers;
    private String name;
    private boolean spectAllowed;
    private boolean spectChatAllowed;
    private Board board;
    private UUID currentPlayer;
    private boolean hostColor;
    private boolean gameLaunched;
    private List<Move> moves;
    private List<UserLight> spect;
    private List<Message> chat;

    public Game(UUID gameId, Player host, Player opponent, int nbOfTowers, String name, boolean spectAllowed, boolean spectChatAllowed, Board board, UUID currentPlayer, boolean hostColor, boolean gameLaunched, List<Move> moves, List<UserLight> spect, List<Message> chat) {
        this.gameId = gameId;
        this.host = host;
        this.opponent = opponent;
        this.nbOfTowers = nbOfTowers;
        this.name = name;
        this.spectAllowed = spectAllowed;
        this.spectChatAllowed = spectChatAllowed;
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.hostColor = hostColor;
        this.gameLaunched = gameLaunched;
        this.moves = moves;
        this.spect = spect;
        this.chat = chat;
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
        return spectAllowed;
    }

    public void setSpectAllowed(boolean spectAllowed) {
        this.spectAllowed = spectAllowed;
    }

    public boolean isSpectChatAllowed() {
        return spectChatAllowed;
    }

    public void setSpectChatAllowed(boolean spectChatAllowed) {
        this.spectChatAllowed = spectChatAllowed;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UUID getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(UUID currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isHostColor() {
        return hostColor;
    }

    public void setHostColor(boolean hostColor) {
        this.hostColor = hostColor;
    }

    public boolean isGameLaunched() {
        return gameLaunched;
    }

    public void setGameLaunched(boolean gameLaunched) {
        this.gameLaunched = gameLaunched;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<UserLight> getSpect() {
        return spect;
    }

    public void setSpect(List<UserLight> spect) {
        this.spect = spect;
    }

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "model.Game{" +
                "gameId=" + gameId +
                ", host=" + host +
                ", opponent=" + opponent +
                ", nbOfTowers=" + nbOfTowers +
                ", name='" + name + '\'' +
                ", spectAllowed=" + spectAllowed +
                ", spectChatAllowed=" + spectChatAllowed +
                ", board=" + board +
                ", currentPlayer=" + currentPlayer +
                ", hostColor=" + hostColor +
                ", gameLaunched=" + gameLaunched +
                ", moves=" + moves +
                ", spect=" + spect +
                ", chat=" + chat +
                '}';
    }
}
