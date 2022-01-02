package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.io.IOException;
import java.util.UUID;

public interface DataToIhmGameInterface {
    /**
     * gets the current user
     * @return User
     */
    User getUser();

    /**
     * gets the current user in Player format
     * @return Player
     */
    Player getPlayer();


    /**
     * gets the current user in UserLight format
     * @return UserLight
     */
    UserLight getUserLight();

    /**
     * gets the current Game
     * @return Game
     */
    Game getGame();

    /**
     * gets the current Game in GameLight format
     * @return GameLight
     */
    GameLight getGameLight();

    /**
     * sets the profile of the current user
     * @param user - User : new profile of the user
     */
    void setProfile(User user);
    void getProfilInfos(UUID userId);
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
