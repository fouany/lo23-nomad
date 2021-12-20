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

    /**
     * Gets the user color.
     * @param userID - the user ID
     * @return the user color
     */
    boolean getUserColor(UUID userID);

    /**
     * save the current game
     * @throws IOException error in file writing
     */
    void saveCurrentGame() throws IOException;
}
