package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameSerializable extends Observable implements Serializable {
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
    private List<Move> moves;
    private List<UserLight> spect;
    private List<Message> chat;
    private List<Tower> towers;

    public GameSerializable(Game g) {
        this.gameId = g.getGameId();
        this.host = g.getHost();
        this.opponent =g.getOpponent();
        this.nbOfTowers= g.getNbOfTowers();
        this.name = g.getName();
        this.gameParameters = g.getGameParameters();
        this.board = g.getBoard();
        this.currentPlayer=g.getCurrentPlayerUUID();
        this.gameLaunched = g.isGameLaunched();
        this.gameEnded = g.isGameEnded();
        this.nbOfTilesPlayed = g.getNbOfTilesPlayed();
        this.moves = g.getMoves().stream().collect(Collectors.toList());
        this.spect = g.getSpect().stream().collect(Collectors.toList());
        this.chat = g.getChat().stream().collect(Collectors.toList());
        this.towers = g.getTowers().stream().collect(Collectors.toList());
    }

    public Game getGame(){
        Game g = new Game(this.host, this.nbOfTowers,this.name, this.gameParameters);
        g.setGameId(this.gameId);
        g.setOpponent(this.opponent);
        g.setBoard(this.board);
        g.setCurrentPlayer(this.currentPlayer);
        g.setNbOfTilesPlayed(this.nbOfTilesPlayed);
        g.setMoves(FXCollections.observableArrayList(this.moves));
        g.setSpect(FXCollections.observableArrayList(this.spect));
        g.setChat(FXCollections.observableArrayList(this.chat));
        g.setTowers(FXCollections.observableArrayList(this.towers));
        return g;
    }

    public UUID getGameId() {
        return gameId;
    }

    public int getNbOfTowers() {
        return nbOfTowers;
    }

    public Player getHost() {
        return host;
    }

    public Player getOpponent() {
        return opponent;
    }
}



