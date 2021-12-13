package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.io.IOException;

public interface DataToIhmGameInterface {
    User getUser();
    Player getPlayer();
    UserLight getUserLight();
    Game getGame();
    GameLight getGameLight();
    void setProfile(User user);

    /**
     * save the current game
     * @throws IOException error in file writing
     */
    void saveCurrentGame() throws IOException;
}
