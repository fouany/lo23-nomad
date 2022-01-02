package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.util.List;
import java.util.UUID;

public interface DataToComClientInterface {

    /**
     * adds a game in games in lobby in Session
     * @param gameLight - GameLight : game to add
     */
    void updateSession(GameLight gameLight);

    /**
     * changes the state of Game in Session
     * @param gameId UUID : id of the game
     * @param gameLaunched - boolean : to know if the game is Launched
     */
    void updateSessionGameState(UUID gameId, boolean gameLaunched);

    /**
     * adds a user light to the connected user of the Session object
     * @param player - Player : user to add to the connected user
     * @param connected - boolean : to know if the user is connected
     */
    void updateUserSession(Player player, boolean connected);

    /**
     * updates the opponent of the game
     * @param player - Player : new opponent of the game
     * @param gameLight - GameLight : game to update
     * @throws GameException Exception if the game does not exist
     */
    void updateOpponent(Player player, GameLight gameLight) throws GameException;

    /**
     * adds a user light to the spectator's list of the game
     * @param userLight - UserLight : user Light to add
     * @param isAdded - boolean : not used
     */
    void handleSpectator(UserLight userLight, boolean isAdded);

    /**
     * sets the game as launched and adds the observable to the game
     */
    void gameLaunchEvent();

    /**
     * adds the tower move to the list of moves of the game, changes the current player
     * @param tower - Move : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws TowerException Tower Placement not valid
     */
    void towerValid(Tower tower, boolean valid) throws TowerException;

    /**
     * adds the tile move to the list of moves of the game and changes the current player
     * @param tile - Move : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws TileException Tile Placement not valid
     */
    void tileValid(Tile tile, boolean valid) throws TileException;

    /**
     * adds the skip move to the list of moves of the game and changes the current player
     * @param skip - Move : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws SkipException Skip not valid
     */
    void skipValidation(Skip skip, boolean valid) throws SkipException;

    /**
     * adds the move to the list of moves, changes the current player, and updates the observable
     * uses for the spectators and the opponent
     * @param move - Move : move to add
     */
    void moveReceived(Move move);

    /**
     * removes the game from the gamesInPlay from the session object and sets the game to "ended" state
     * @param gameId - UUID : the game which has just ended
     * @param winner - UUID : the winner of the game
     * @param lastMove - Move : The Move that ended the game
     */
    void endGame (UUID gameId, UUID winner, Move lastMove);

    /**
     * adds the game to the saved games of the user
     * @param savedGame - Game : game to add
     */
    //TODO : to delete (new sequence diagram)
    void transferSavedGame (Game savedGame);

    /**
     * adds the message to the chat of the game
     * @param message - Message : message to add
     */
    void storeNewMessage(Message message);

    /**
     * adds the connected user, and updates the according lists of connected user to Session
     * @param players - List<Player> : connected users to add
     * @param gamesInLobby - List<GameLight> : games in Lobby to add
     * @param gamesInPlay - List<GameLight> : games in Play to add
     */
    void addConnectedUserProfile(List<Player> players, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay);

    /**
     * removes a user from the connected users of the session object
     * @param idUser - UUID : id of the user to remove
     * @param isDisconnected  - boolean : to know if the user isDisconnected
     */
    void isDisconnected(UUID idUser, boolean isDisconnected);

    /**
     * retrieves all the online users
     * @return List<UserLight> : List of connected users
     */
    List<UserLight> getOnlineUsers();

    /**
     * sets the game (created by the server) in the GameController.
     * @param game - Game : game created
     */
    void gameCreated(Game game);

    /**
     * adds a user to the game as opponent
     * @param gameID - UUID : id of the game
     * @param p - Player : opponent to add
     * @throws GameException : Exception if the game doesn't exist
     */
    void newPlayer(UUID gameID, Player p) throws GameException;

    /**
     * adds a user to the game as spectator
     * @param gameID - UUID : id of the game
     * @param spec - Player : user to add
     * @throws GameException : Exception if the game doesn't exist
     */
    void newSpectator(UUID gameID, Player spec) throws GameException;


    /**
     * retrieves all the saved games of the user.
     * @return List<Game> : list of the game saved
     */
    List<Game> getStoredAvailableGames();


    /**
     * returns the UUID of the player currently playing
     * @return UUID
     */
    UUID currentUserIsPlayer();

    /**
     * get profile of the current user
     * @return UserLight
     */
    UserLight getUserLight();

    /**
     * adds or refused an opponent in a game
     * @param game - Game : game to add/refuse the opponent
     * @param isAdded - boolean : to know if the opponent is added or refused
     * @throws GameException : Exception if the opponent is refused
     */

    void addedPlayerInGame(Game game, boolean isAdded) throws GameException;

    void addedSpecInGame(Game game, boolean isAdded);

    void removeFinishedGame(UUID gameId);
}
