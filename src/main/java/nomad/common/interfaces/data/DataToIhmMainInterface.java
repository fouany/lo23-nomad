package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public interface DataToIhmMainInterface {
    User getUser();
    Player getPlayer();
    UserLight getUserLight();
    Game getGame();
    GameLight getGameLight();
    Game getStoredGame(UUID gameId);

    void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException;
    void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate)  throws IOException, ClassNotFoundException;

    //TODO: return boolean or exception ?
    void addAccount(String path) throws IOException, ClassNotFoundException;
    void login(String login, String password, String ip, int port) throws UserException,IOException, ClassNotFoundException;
    void logout();
    User getProfileInfos (UUID idUserLight);
    void createCategory (Category category) throws CategoryException, IOException, ClassNotFoundException;
    void addUser (UserLight user, Category category) throws CategoryException, IOException, ClassNotFoundException;
    void setPermissions(Contact updatedContact) throws CategoryException, IOException, ClassNotFoundException;
    void setPermissions(Category lastCategory,Category updatedCategory) throws CategoryException, IOException, ClassNotFoundException;
}
