package nomad.common.interfaces.com;

import nomad.common.data_structure.*;

import java.util.List;

public interface ComToIhmMainInterface {
    void newGame(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, boolean hostColor);
    void enoughPlayers(GameLight game);
    void rejectPlayer(GameLight game);
    void launchGame(GameLight game);
    void addPlayerInGame(Player player, GameLight game);
    void addSpecInGame(UserLight user, GameLight game);
    void placeTower(Tower tower);
}
