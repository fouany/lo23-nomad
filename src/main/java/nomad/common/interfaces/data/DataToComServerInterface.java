package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.util.List;
import java.util.UUID;

public interface DataToComServerInterface {

    /**
     * Transfer all the necessary information for the server to create the game
     * @param name - String : the name of the game
     * @param host - UserLight : the creator of the game
     * @param nbOfTowers - int : the number of tower of the game
     * @param spectAllowed - boolean : if spectators are allowed in the game
     * @param spectChatAllowed - boolean : if spectators are allowed to chat in the game
     * @param hostColour - boolean : the color of the host of the game
     * @return Game - the created Game
     */
    Game createGame(String name, UserLight host, int nbOfTowers, boolean spectAllowed, boolean spectChatAllowed, boolean hostColour);

    /**
     * A request for another player to join the game
     * @param player - Player : the player requesting to join the game
     * @param game - GameLight : the game requested to be joined
     */
    void joinGameRequest(Player player, GameLight game);

    /**
     * Method used when an opponent is accepted
     * @param gameId - UUID : id of the game
     * @param opponentId - UUID : id of the user
     * @return Game - the Game
     */
    Game guestAccepted(UUID gameId, UUID opponentId);

    /**
     * Denies a player's request to be the opponent
     * @param player - Player : the player requesting to join the game
     */
    void guestRefused(Player player);

    /**
     * Adds a spectator in a game
     * @param user - UserLight : the spectator added to the game
     * @param game - GameLight : the game that the spectator joins
     */
    void addSpecInGame(UserLight user, GameLight game);

    /**
     * Sends the Users present in the game
     * @param game - GameLight : the game of the Users
     * @return List<UUID> : the list of UUID
     */
    List<UUID> getUserList(GameLight game);

    /**
     * Launches the game
     * @param gameId - UUID : id of the game
     */
    void launchGame(UUID gameId);

    /**
     * Saves a tower on the game's board
     * @param t - Tower : The Tower to be saved
     * @throws TowerException
     */
    void saveTower(Tower t) throws TowerException;

    /**
     * Saves a tile on the game's board
     * @param t - Tile : The Tile to be saved
     * @throws TileException
     */
    void saveTile(Tile t) throws TileException;

    /**
     * Saves a move on the game's board
     * @param m - Move : The Move to be saved
     */
    void saveMove(UserLight user, Move m);

    /**
     * Saves a skip on the game's board
     * @param s - Skip : The Skip to be saved
     */
    void saveSkip(Skip s);

    /**
     * Checks if a game is ended
     * @param game - GameLight : the game that has to be checked
     * @return boolean
     */
    boolean checkGameEnded(GameLight game);

    /**
     * Gets the current Game
     * @param gameId - UUID : id of the Game
     * @return Game
     */
    Game getStoredGame(UUID gameId);

    /**
     * store a message
     * @param message message to store
     */
    void storeMessage(Message message) throws MessageException;

    /**
     * Gets all the Users currently connected
     * @return List<Player>
     */
    List<Player> requestConnectedUserList();

    /**
     * Gets all the Games currently in lobby
     * @return List<GameLight>
     */
    List<GameLight> requestGameListInLobby();

    /**
     * Gets all the Games currently being played
     * @return List<GameLight>
     */
    List<GameLight> requestGameListInPlay();

    /**
     * Adds a connected User
     * @param newUser - User : User to be added
     */
    void updateUserListAdd(User newUser);

    /**
     * Removes a user of the connected users list and removes all Games in Lobby he created
     * @param userId - UUID : id of the user to be removed
     * @return User
     */
    User updateUserListRemove(UUID userId);

    /**
     * Removes a game of the games in play list
     * @param oldUser - User : id of the user whose games have to be removed
     */
    void updateListGamesRemove(User oldUser);

    /**
     * get profile of an user
     * @param idUser : id of the user
     * @return User
     */
    User getUserProfile(UUID idUser);

    /**
     * Adds an opponent to a game
     * @param gameId - UUID : id of the game
     * @param userId - UUID : id of the user
     */
    void addOpponent(UUID gameId, UUID userId);
}
