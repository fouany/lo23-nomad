package nomad.common.interfaces.data;

import nomad.common.data_structure.*;
import java.util.List;
import java.util.UUID;

public interface DataToComInterface {

    void updateSession(GameLight game, String name, boolean spect, boolean chatSpect);
    void updateSessionGameState(UUID idGame , boolean isLaunched);
    void updateUserSession(Player player, boolean connected);
    void updateOpponent(Player player, GameLight game);

    void handleSpectator(UserLight user, boolean isAdded);
    void gameLaunchEvent();
    void towerValid(Tower t, boolean valid);
    void tileValid(Tile m, boolean valid);
    void skipValidation(Skip m, boolean valid);
    void moveReceived(Move m, UserLight user);

    void endGame (GameLight game, boolean gameEnded);
    void transferSavedGame (Game savedGame);
    void storeNewMessage(Message message);
    void addConnectedUserProfile (List<Player> players, List<GameLight> games);
    void isDisconnected(UUID idUser, boolean isDeconnected);
    void gameCreated(Game game);
    void newUser(GameLight game, Player player, boolean isPlayer);

    List<Game> getStoredAvailableGames();
    boolean currentUserisPlayer();
}
