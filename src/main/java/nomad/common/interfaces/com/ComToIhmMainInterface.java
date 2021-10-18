package nomad.common.interfaces.com;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;

import java.util.List;

public interface ComToIhmMainInterface {
    void addOnlineUser(User user);
    void addAddedPlayerInGame(Game game, boolean isAdded);
    void addAddedSpecInGame(Game game, boolean isAdded);
    void isDisconnected(User user, boolean disconnected);
    void addConnectedUserProfil(User user, boolean isAdded, List<Player> players, List<Game> games);
}
