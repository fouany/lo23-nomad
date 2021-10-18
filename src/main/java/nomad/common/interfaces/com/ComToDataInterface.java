package nomad.common.interfaces.com;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;

import java.util.List;
import java.util.UUID;

public interface ComToDataInterface {
    void createGame(Game game);
    Game getGame(UUID gameId);
    List<User> getUserList(UUID gameId);
    Move saveMove(UUID playerId, Move move);
    boolean checkAction(Move action, UUID clientId);
    void storeAction(Move action, UUID clientId);
    void updateGame();
    Game getStoredGame(UUID gameId);
    List<User> requestUserList();
    List<Game> requestGameList();
    void updateUserList(User newUser);
    void updateUserList(UUID userId);
    void joinGameRequest(Player player, Game game);
    void guestAccepted(Player player);
    void storeSavedGame(Game savedGame);
    void getProfileInfos(UUID userId);
    void getAvailableStoredGames();
}
