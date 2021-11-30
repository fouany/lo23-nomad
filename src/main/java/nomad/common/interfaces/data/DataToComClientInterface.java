package nomad.common.interfaces.data;

import nomad.common.data_structure.*;
import java.util.List;
import java.util.UUID;

public interface DataToComClientInterface {

    /**
     * adds a new game in the lobby
     * @param gameLight
     */
    void updateSession(GameLight gameLight);

    /**
     * Change the State of Game in Session
     * @param gameId
     * @param gameLaunched
     */
    void updateSessionGameState(UUID gameId, boolean gameLaunched);

    /**
     * adds a user light to the connected user of the session object
     * @param player
     * @param connected
     */
    void updateUserSession(Player player, boolean connected);

    /**
     * updates the opponent of the game
     * @param player
     * @param game
     * @throws GameException
     */
    void updateOpponent(Player player, GameLight game) throws GameException;

    /**
     * adds a user light to the spectator's list of the game
     * @param userLight
     * @param isAdded
     */
    void handleSpectator(UserLight userLight, boolean isAdded);

    /**
     * sets the game as launched and adds the observable to the game
     */
    void gameLaunchEvent();

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tower
     * @param valid
     */
    void towerValid(Tower tower, boolean valid);

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tile
     * @param valid
     * @throws TileException
     */
    void tileValid(Tile tile, boolean valid) throws TileException;

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param skip
     * @param valid
     * @throws SkipException
     */
    void skipValidation(Skip skip, boolean valid) throws SkipException;

    /**
     * adds the move to the list of moves, changes the current player, and updates the observable
     * @param move
     * @param user
     */
    void moveReceived(Move move, UserLight user);

    /**
     * removes the game from the gamesInPlay from the session object and sets the game to "ended" state
     * @param gameLight
     * @param gameEnded
     */
    void endGame (GameLight gameLight, boolean gameEnded);

    /**
     * adds the game to the saved games of the user
     * @param savedGame
     */
    void transferSavedGame (Game savedGame);

    /**
     * adds the message to the chat of the game
     * @param message
     */
    void storeNewMessage(Message message);

    /**
     * adds the connected user, and updates the according lists of connected user to the games in lobby and in play
     * @param players
     * @param gamesInLobby
     * @param gamesInPlay
     */
    void addConnectedUserProfile (List<Player> players, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay);

    void isDisconnected(UUID idUser, boolean isDeconnected);

    void gameCreated(Game game);

    void newUser(GameLight game, Player player, boolean isPlayer) throws GameException;

    void newPlayer(UUID gameId, Player opponent) throws GameException;

    List<Game> getStoredAvailableGames();

    List<UserLight> getOnlineUsers();

    //TODO UUID or boolean ?
    UUID currentUserIsPlayer();

    UserLight getUserLight();

    void addedPlayerInGame (Game game, boolean isAdded);
}
