package nomad.data.server;

import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataServerInterface;

import java.util.List;
import java.util.UUID;

/**
 * Controller for the data server.
 */
public class DataServerController {
    private UserController userController;
    private ComToDataServerInterface comOfferedInterface;
    private Session session;
    private GamesController gamesController;

    public DataServerController(UserController userController,
                                GamesController gamesController, ComToDataServerInterface comToData) {
        this.userController = userController;
        this.gamesController = gamesController;
        this.comOfferedInterface = comToData;
    }

    /**
     * Sets the users at the specified index.
     *
     * @param user
     */
    public void setUser(User user) {
        userController.setUser(user);
    }

    /**
     * Returns the list of users.
     *
     * @return users
     */
    public List<User> getUsers() {
        return userController.getAllUsers();
    }

    /**
     * Returns the user at the specified index.
     *
     * @param userId
     * @return the user at the specified index.
     */
    public User getUser(UUID userId) {
        return userController.getUser(userId);
    }

    /**
     * Returns the dataToCom interface.
     *
     * @return dataToCom
     */
    public ComToDataServerInterface getComOfferedInterface() {
        return comOfferedInterface;
    }


    /**
     * Gets the session.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets the session.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Gets the user controller.
     *
     * @return userController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * Sets tue user controller.
     *
     * @param userController
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    /**
     * Gets the games controller.
     *
     * @return gameController
     */
    public GamesController getGamesController() {
        return gamesController;
    }

    /**
     * Sets the games controller.
     *
     * @param gamesController
     */
    public void setGamesController(GamesController gamesController) {
        this.gamesController = gamesController;
    }
}
