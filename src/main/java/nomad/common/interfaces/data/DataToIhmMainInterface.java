package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.util.Date;
import java.util.UUID;

public interface DataToIhmMainInterface {
    User getUser();
    Player getPlayer();
    UserLight getUserLight();
    Game getGame();
    GameLight getGameLight();
    Game getStoredGame(UUID gameId);

    void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate);
    void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate);
    boolean addAccount(String path);
    void login(String login, String password);
    void logout();
    void getProfileInfos (UUID idUserLight);
    void createCategory (Category category);
    void addUser (UserLight user, Category category);
    void setPermissions(Contact updatedContact);
    void setPermissions(Category updatedCategory);
}
