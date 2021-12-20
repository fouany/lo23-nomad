package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.io.IOException;
import java.util.UUID;

public interface DataToIhmGameInterface {
    User getUser();
    Player getPlayer();
    UserLight getUserLight();
    Game getGame();
    GameLight getGameLight();
    void setProfile(User user);
    void getProfilInfos(UUID userId);
    /**
     * save the current game
     * @throws IOException error in file writing
     */
    void saveCurrentGame() throws IOException;
}
