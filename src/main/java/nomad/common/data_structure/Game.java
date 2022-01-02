package nomad.common.data_structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a game
 */
public class Game extends Observable implements Serializable  {

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
    private ObservableList<Move> moves;

    /**
     * List of spectators
     */
    private ObservableList<UserLight> spect;

    /**
     * List of the messages sent in the chat
     */
    private ObservableList<Message> chat;

    /**
     * List of played towers - useful to access their position
     */
    private ObservableList<Tower> towers;
    private int nbRounds = 0;

    /**
     * Game constructor
     * @param host - Game host
     * @param nbOfTowers - Number of towers in game (default is 5)
     * @param name - Game name
     * @param gameParameters - Game settings
     */
    public Game(Player host, int nbOfTowers, String name, GameParameters gameParameters) {
        this.gameId = UUID.randomUUID();
        this.host = host;
        this.nbOfTowers = nbOfTowers;
        this.name = name;
        this.board = new Board();
        this.currentPlayer = host.getId();
        this.gameParameters = gameParameters;
        this.nbOfTilesPlayed = 0;
        this.nbOfTowersPlayed = 0;
        this.spect = FXCollections.observableArrayList(new ArrayList<>());
        this.moves = FXCollections.observableArrayList(new ArrayList<>());
        this.chat = FXCollections.observableArrayList(new ArrayList<>());
        this.towers = FXCollections.observableArrayList(new ArrayList<>());
    }

    /**
     * Gets the number of tiles already played
     * @return the number of tiles already played as an int
     */
    public int getNbOfTilesPlayed() {
        return nbOfTilesPlayed;
    }

    /** Adds a move to the game (and the list of moves) - can be a Tile, a Tower or a Skip
     * If Move is a tower, update the list of towers and update the board
     * If Move is a tile, increment the number of tiles played and update the board
     * @param m - Move to be added
     */
    public void addMove(Move m) {
        if (m instanceof Tower) {
            towers.add((Tower) m);
            board.updateBoard((Tower) m);
        } else if (m instanceof Tile) {
            nbOfTilesPlayed++;
            board.updateBoard((Tile) m);
        }
        this.incrementNbRounds();
        moves.add(m);
    }

    /**
     * Returns the game ID
     * @return game ID as a UUID
     */
    public UUID getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID
     * @param gameId - Game ID as a UUID
     */
    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    /**
     * Returns game settings
     * @return Game settings as a gameParamaters object
     */
    public GameParameters getGameParameters() {
        return gameParameters;
    }

    /**
     * Returns game host
     * @return game host as a Player object
     */
    public Player getHost() {
        return host;
    }

    /**
     * Sets the game host
     * @param host - game host as a Player object
     */
    public void setHost(Player host) {
        this.host = host;
    }

    /**
     * Returns game opponent
     * @return game opponent as a Player object
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Sets the game opponent
     * @param opponent - Game opponent as a Player object
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Returns the number of towers in total
     * @return number of towers in total as an int
     */
    public int getNbOfTowers() {
        return nbOfTowers;
    }

    /**
     * Sets the number of towers in total
     * @param nbOfTowers - number of towers in total as an int
     */
    public void setNbOfTowers(int nbOfTowers) {
        this.nbOfTowers = nbOfTowers;
    }

    /**
     * Returns the game name
     * @return Game name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the game name
     * @param name - Game name as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns true if spectators are allowed to join and watch the game
     * @return - Right as a boolean
     */
    public boolean isSpectAllowed() {
        return gameParameters.isSpectAllowed();
    }

    /**
     * Sets a boolean to say if spectators are allowed to join and watch the game
     * @param spectAllowed - Right as a boolean
     */
    public void setSpectAllowed(boolean spectAllowed) {
        this.gameParameters.setSpectAllowed(spectAllowed);
    }

    /**
     * Returns true if spectators are allowed to chat
     * @return - Right as a boolean
     */
    public boolean isSpectChatAllowed() {
        return gameParameters.isSpectChatAllowed();
    }

    /**
     * Sets a boolean to say if spectators are allowed to chat
     * @param spectChatAllowed - Right as a boolean
     */
    public void setSpectChatAllowed(boolean spectChatAllowed) {
        this.gameParameters.setSpectChatAllowed(spectChatAllowed);
    }

    /**
     * Returns the board of the game
     * @return the board as a Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the board of the game
     * @param board - board to be implemented
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Returns the current active player UUID
     * @return the current active player as a UUID
     */
    public UUID getCurrentPlayerUUID() {
        return currentPlayer;
    }

    /**
     * Sets the current active player
     * @param currentPlayer - Current active player as a UUID
     */
    public void setCurrentPlayer(UUID currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Changes the current player using the UUIDs already present in the Game object
     */
    public void changeCurrentPlayer(){
        UUID currentPlayerUUID = getCurrentPlayerUUID();
        UUID opponentUUID = getOpponent().getId();
        UUID hostUUID = getHost().getId();

        if (hostUUID.equals(currentPlayerUUID)) {
            setCurrentPlayer(opponentUUID);
        } else {
            setCurrentPlayer(hostUUID);
        }
    }

    /**
     * Returns the color of the host
     * @return the color of the host as a boolean
     */
    public boolean isHostColor() {
        return gameParameters.isHostColor();
    }

    /**
     * Sets the color of the host
     * @param hostColor - color of the host as a boolean
     */
    public void setHostColor(boolean hostColor) {
        this.gameParameters.setHostColor(hostColor);
    }

    /**
     * Returns the state of the game to know if it has begun or not
     * @return the state of the game as a boolean - True = Game has begun
     */
    public boolean isGameLaunched() {
        return gameLaunched;
    }

    /**
     * Sets the state of the game to know if it has begun or not
     * @param gameLaunched  - State of the game as a boolean - True = Game has begun
     */
    public void setGameLaunched(boolean gameLaunched) {
        this.gameLaunched = gameLaunched;
        // Those force updates should not be here (could be link to a sync problem from the modal)
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the state of the game to know if it has ended or not
     * @return the state of the game as a boolean - True = Game has ended
     */
    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     * Sets the state of the game to know if it has ended or not
     * @param gameEnded  - State of the game as a boolean - True = Game has ended
     */
    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }


    /**
     * Returns the list of moves already played
     * @return the list of moves already played as a List of Move object
     */
    public ObservableList<Move> getMoves() {
        return moves;
    }

    /**
     * Sets the list of moves already played
     * @param moves - List of moves as a List of Move object
     */
    public void setMoves(ObservableList<Move> moves) {
        this.moves = moves;
    }

    /**
     * Returns the list of the game spectators
     * @return the list of the game spectators as a List of UserLight
     */
    public ObservableList<UserLight> getSpect() {
        return this.spect;
    }

    /**
     * Sets the list of game spectators
     * @param spect - List of spectators as a List of UserLight
     */
    public void setSpect(ObservableList<UserLight> spect) {
        this.spect = spect;
    }

    /**
     * Returns the game chat
     * @return the game chat as a List of Message
     */
    public ObservableList<Message> getChat() {
        return this.chat;
    }

    /**
     * Sets the game chat
     * @param chat  - Chat as a List of Message
     */
    public void setChat(ObservableList<Message> chat) {
        this.chat = chat;
    }

    /**
     * Adds a message to the game chat
     * @param message - Message to be added to the chat
     */
    public void addMessage(Message message) {
        chat.add(message);
    }

    /**
     * Adds a spectator to the list of game spectators
     * @param spect - Spectator to be added
     */
    public void addSpec(UserLight spect) {
        this.spect.add(spect);
    }

    /**
     * Create an instance of GameLight from the current Game object
     * - GameLight is a lighter version of game -
     * @return
     */
    public GameLight createGameLight(){
        return new GameLight(gameId, host, nbOfTowers);
    }

    public boolean areAllTowersConnected() {
        return false;
    }

    public int getNbRounds() {
        return this.nbRounds;
    }

    public void incrementNbRounds() {
        this.nbRounds++;
    }
    /**
     * Returns a string representation of the object
     * @return String
     */
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

    /**
     * Remove the current opponent of the game
     */
    public void removeOpponent() {
        this.opponent = null;
    }

    /**
     * Returns a list of all the users of the game apart from the host (spectators + opponent)
     * @return a list of all the users of the game apart from the host as a list of UUID
     */
    public List<UUID> getListOther() {
        List<UUID> listOther = new ArrayList<>();
        if (this.currentPlayer.equals(this.host.getId())) {
            listOther.add(this.opponent.getId());
        } else {
            listOther.add(this.host.getId());
        }
        for (UserLight ul : this.spect) {
            listOther.add(ul.getId());
        }
        return listOther;
    }

    /**
     * Returns a serializable version of the game object
     * @return a serializable version of the game object as a GameSerializable object
     */
    public GameSerializable getGameSerializable() {
        return new GameSerializable(this);
    }

    /**
     * Returns a list of played towers
     * @return a list of played towers as a List of Towers
     */
    public ObservableList<Tower> getTowers() {
        return this.towers;
    }

    /**
     * Sets the list of played towers
     * @param towers - List of Towers
     */
    public void setTowers(ObservableList<Tower> towers) {
        this.towers = towers;
    }

    /**
     * Sets the number of tiles played
     * @param nbOfTilesPlayed - Number of tiles played as an int
     */
    public void setNbOfTilesPlayed(int nbOfTilesPlayed) {
        this.nbOfTilesPlayed = nbOfTilesPlayed;
    }

    public int getNbOfTowersPlayed() {
        return this.nbOfTowersPlayed;
    }

    public void setNbOfTowersPlayed(int nbOfTowersPlayed) {
        this.nbOfTowersPlayed = nbOfTowersPlayed;
    }

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

    public boolean switchTowerTile() {
        return getNbOfTowersPlayed() == getNbOfTowers();
    }
}
