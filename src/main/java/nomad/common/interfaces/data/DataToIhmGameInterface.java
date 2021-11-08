package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

public interface DataToIhmGameInterface {
    User getUser();
    Player getPlayer();
    UserLight getUserLight();
    Game getGame();
    GameLight getGameLight();
    void setProfile(User user);
    void saveCurrentGame();
}
