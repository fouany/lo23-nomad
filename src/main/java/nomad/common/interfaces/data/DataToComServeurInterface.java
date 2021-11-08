package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.util.List;
import java.util.UUID;

public interface DataToComServeurInterface {
    Game createGame(String name, UserLight host, int nbOfTowers, boolean spectAllowed, boolean spectChatAllowed, boolean hostColour);
    void joinGameRequest(Player player, GameLight game);
    void guestAccepted();
    void guestRefused(Player player);
    void addSpecInGame(UserLight user, GameLight game);
    List<User> getUserList(GameLight game);
    void launchGame(GameLight game);
    Tower saveTower(UserLight user, Tower t);
    Move saveMove(UserLight user, Move m);
    boolean checkGameEnded(GameLight game);
    Game getStoredGame(UUID gameId);
    void storeMessage(Message message);
    List<Player> requestConnectedUserList();
    List<GameLight> requestGamelist();
    void updateUserlistAdd (Player newUser);
    Player updateUserListRemove (User oldUser);
    User getPofileData(UUID idUser);
}
