package nomad.common.interfaces.com;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.Tower;
import nomad.common.data_structure.UserLight;

public interface ComToIhmMainInterface {
    enum Color {
        RED,
        WHITE,
        RANDOM,
        NULL, //if no color is clicked
    }
    void newGame(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, Color hostColor);

    void enoughPlayers(GameLight game);

    void rejectPlayer(GameLight game);

    void launchGame(GameLight game);

    void addPlayerInGame(Player player, GameLight game);

    void addSpecInGame(UserLight user, GameLight game);

    void placeTower(Tower tower);
}
