package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Serializable version of a game
 */
public class GameSerializable extends Observable implements Serializable {

    /**
     * ID of the game
     */
    private UUID gameId;

    /**
     * Host of the game as a Player Object
     */
    private Player host;

    /**
     * Opponent of the game as a Player Object
     */
    private Player opponent;

    /**
     * Number of towers in the game
     */
    private int nbOfTowers;

    /**
     * Name of the game
     */
    private String name;

    /**
     * Game settings
     */
    private GameParameters gameParameters;

    /**
     * Representation of the board of the game
     */
    private Board board;

    /**
     * UUID of the current active player
     */
    private UUID currentPlayer;

    /**
     * Boolean to check if the game is launched
     */
    private boolean gameLaunched;

    /**
     * Boolean to check if the game is finished
     */
    private boolean gameEnded;

    /**
     * Number of tiles already played
     */
    private int nbOfTilesPlayed;
    private int nbOfTowersPlayed;

    /**
     * List of moves already played in the game
     */
    private List<Move> moves;

    /**
     * List of spectators
     */
    private List<UserLight> spect;

    /**
     * List of the messages sent in the chat
     */
    private List<Message> chat;

    /**
     * List of played towers - useful to access their position
     */
    private List<Tower> towers;

    /**
     * GameSerializable constructor
     * @param g - Game object to serialize
     */
    public GameSerializable(Game g) {
        this.gameId = g.getGameId();
        this.host = g.getHost();
        this.opponent = g.getOpponent();
        this.nbOfTowers = g.getNbOfTowers();
        this.name = g.getName();
        this.gameParameters = g.getGameParameters();
        this.board = g.getBoard();
        this.currentPlayer = g.getCurrentPlayerUUID();
        this.gameLaunched = g.isGameLaunched();
        this.gameEnded = g.isGameEnded();
        this.nbOfTilesPlayed = g.getNbOfTilesPlayed();
        this.nbOfTowersPlayed = g.getNbOfTowersPlayed();
        this.moves = g.getMoves().stream().collect(Collectors.toList());
        this.spect = g.getSpect().stream().collect(Collectors.toList());
        this.chat = g.getChat().stream().collect(Collectors.toList());
        this.towers = g.getTowers().stream().collect(Collectors.toList());
    }

    /**
     * Generate a Game object from a GameSerializable object
     * @return a Game object
     */
    public Game getGame(){
        Game g = new Game(this.host, this.nbOfTowers,this.name, this.gameParameters);
        g.setGameId(this.gameId);
        g.setOpponent(this.opponent);
        g.setBoard(this.board);
        g.setCurrentPlayer(this.currentPlayer);
        g.setNbOfTilesPlayed(this.nbOfTilesPlayed);
        g.setNbOfTowersPlayed(this.nbOfTilesPlayed);
        g.setGameLaunched(this.gameLaunched);
        g.setGameEnded(this.gameEnded);
        g.setMoves(FXCollections.observableArrayList(this.moves));
        g.setSpect(FXCollections.observableArrayList(this.spect));
        g.setChat(FXCollections.observableArrayList(this.chat));
        g.setTowers(FXCollections.observableArrayList(this.towers));
        return g;
    }

    /**
     * Returns the game ID
     * @return game ID as a UUID
     */
    public UUID getGameId() {
        return gameId;
    }

    /**
     * Returns the number of towers in total
     * @return number of towers in total as an int
     */
    public int getNbOfTowers() {
        return nbOfTowers;
    }

    /**
     * Returns game host
     * @return game host as a Player object
     */
    public Player getHost() {
        return host;
    }

    /**
     * Returns game opponent
     * @return game opponent as a Player object
     */
    public Player getOpponent() {
        return opponent;
    }
}



