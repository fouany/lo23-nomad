package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.util.List;
import java.util.UUID;

public interface DataToComServerInterface {
    Game createGame(String name, UserLight host, int nbOfTowers, boolean spectAllowed, boolean spectChatAllowed, boolean hostColour);

    void joinGameRequest(Player player, GameLight game);

    Game guestAccepted(UUID gameId, UUID opponentId);

    void guestRefused(Player player);

    void addSpecInGame(UserLight user, GameLight game);

    List<User> getUserList(GameLight game);

    void launchGame(UUID gameId);

    void saveTower(Tower t) throws TowerException;

    void saveTile(Tile t) throws TileException;

    void saveMove(UserLight user, Move m);

    void saveSkip(Skip s);

    boolean checkGameEnded(GameLight game);

    Game getStoredGame(UUID gameId);

    /**
     * store a message
     * @param message message to store
     */
    void storeMessage(Message message);

    List<Player> requestConnectedUserList();

    List<GameLight> requestGameListInLobby();
    List<GameLight> requestGameListInPlay();

    void updateUserListAdd(User newUser);

    User updateUserListRemove(UUID userId);

    void updateListGamesRemove(User oldUser);

    User getUserProfile(UUID idUser);

    void addOpponent(UUID gameId, UUID userId);
}
