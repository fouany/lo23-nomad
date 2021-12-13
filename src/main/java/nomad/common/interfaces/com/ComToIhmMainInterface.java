package nomad.common.interfaces.com;

import nomad.common.data_structure.*;

public interface ComToIhmMainInterface {
    void newGame(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, boolean hostColor);

    void launchGame(Game game);

    void addPlayerInGame(Player player, GameLight game);

    void addSpecInGame(UserLight user, GameLight game);
}
