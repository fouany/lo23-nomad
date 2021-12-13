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

    void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException,UserException;
    void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate)  throws IOException;

    /**
     * Imports a profile by reading a file given by a path and creates the account of the imported user/profile
     * @param path
     * @throws IOException error writing and reading file
     * @throws ClassNotFoundException class not found
     */
    void addAccount(String path) throws IOException, ClassNotFoundException, UserException;

    /**
     * Exports a User and its respective data to a file specified by the path
     * @param exportPath
     */
    void exportProfile(String exportPath) throws IOException;
    void login(String login, String password, String ip, int port) throws UserException,IOException, ClassNotFoundException;
    void logout();

    /**
     * get the profile of another user
     * @param idUserLight id of the User
     * @return User
     */
    User getProfileInfos (UUID idUserLight);

    void createCategory (Category category) throws CategoryException, IOException;
    void addUser (UserLight user, Category category) throws CategoryException, IOException;
    void setPermissions(Contact updatedContact) throws CategoryException, IOException;
    void setPermissions(Category lastCategory,Category updatedCategory) throws CategoryException, IOException;
    void enoughPlayers (GameLight game) throws GameException;
    void rejectPlayers (GameLight game) throws GameException;
}
