package nomad.common.interfaces.data;

import nomad.common.data_structure.*;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public interface DataToIhmMainInterface {
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
     * gets the game stored whose id is gameId
     * @param gameId - UUID : id of the game stored
     * @return Game stored
     */
    Game getStoredGame(UUID gameId);

    /**
     * creates an account and stores it in a file
     *
     * @param login  - String : login of the user
     * @param pwd - String : password of the user
     * @param name - String : name of the user
     * @param profilePicture - String : picture of the user
     * @param birthDate - Date : date of birth of the user
     * @throws IOException : error writing file
     * @throws UserException : some user information is empty
     */
    void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException,UserException;

    /**
     * modifies an account
     *
     * @param login - String : login modified
     * @param pwd - String : password modified
     * @param name - String : name modified
     * @param profilePicture - String : picture modified
     * @param birthDate - Date : date of Birth modified
     * @throws IOException error writing or reading file
     * @throws UserException error in login (empty or not valid)
     */
    void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate)  throws IOException;

    /**
     * Imports a profile by reading a file given by a path and creates the account of the imported user/profile
     *
     * @param path - String : path of the file
     * @throws IOException : error writing and reading file
     * @throws ClassNotFoundException : class not found
     */
    void addAccount(String path) throws IOException, ClassNotFoundException, UserException;

    /**
     * Login
     *
     * @param login - String : login of the user
     * @param password - String : password of the user
     * @param ip - String : IP address of the server
     * @param port - int : Port of the user
     * @throws UserException : User not found
     * @throws IOException : Error in reading file
     * @throws ClassNotFoundException : class not found
     */
    void login(String login, String password, String ip, int port) throws UserException,IOException, ClassNotFoundException;

    /**
     * logout
     */
    void logout();

    /**
     * gets the profile of another user (which has a specific id)
     *
     * @param idUserLight - UUID : id of the User
     */
    void getProfileInfos (UUID idUserLight);

    /**
     * Create a category
     *
     * @param category - Category : new category
     * @throws CategoryException : Error Category already exist
     * @throws IOException : Error in writing file
     */
    void createCategory (Category category) throws CategoryException, IOException;

    /**
     * adds user to a category
     *
     * @param user - UserLight : User to add to the category
     * @param category - Category : category
     * @throws CategoryException : Category doesn't exist
     * @throws IOException : Error in writing file
     */
    void addUser (UserLight user, Category category) throws CategoryException, IOException;

    /**
     * sets permission of a contact
     *
     * @param updatedContact - Contact : contact updated (with new permissions)
     * @throws CategoryException : New Category not found
     * @throws IOException : Error in writing file
     */
    void setPermissions(Contact updatedContact) throws CategoryException, IOException;

    /**
     * updates category Permission
     *
     * @param lastCategory - Category : category without new permissions
     * @param updatedCategory - Category : category updated
     * @throws CategoryException : category without new permissions not found
     * @throws IOException :Error in writing or reading
     */
    void setPermissions(Category lastCategory,Category updatedCategory) throws CategoryException, IOException;


    /**
     * verifies if there is enough players in a game (= if there is an opponent)
     * @param game - GameLight : game
     * @throws GameException : No opponent have been added to the game yet
     */
    void enoughPlayers (GameLight game) throws GameException;

    /**
     * rejects the opponent in a game
     * @param game - GameLight : game
     * @throws GameException : No opponent have been added to the game yet
     */
    void rejectPlayers (GameLight game) throws GameException;

    /**
     * Exports a User and its respective data to a file specified by the path
     * @param exportPath - String : path of the file
     */
    void exportProfile(String exportPath) throws IOException;
}
