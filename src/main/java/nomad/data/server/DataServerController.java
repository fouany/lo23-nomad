package nomad.data.server;

import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataClientInterface;
import nomad.common.interfaces.data.*;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.common.interfaces.main.IhmMainToDataInterface;

import java.util.List;
import java.util.UUID;

/**
 * Controller for the data server.
 */
public class DataServerController {
    private UserController userController;
    private DataToIhmGameServerInterface dataToIhmGame;
    private DataToIhmMainServerInterface dataToIhmMain;
    private DataToComServerInterface dataToCom;
    private IhmGameToDataInterface gameOfferedInterface;
    private IhmMainToDataInterface mainOfferedInterface;
    private ComToDataClientInterface comOfferedInterface;
    private Session session;
    private GamesController gamesController;

    public DataServerController(UserController userController, DataToIhmGameServerInterface dataToIhmGame,
                                DataToIhmMainServerInterface dataToIhmMain, DataToComServerInterface dataToCom,
                                GamesController gamesController, IhmGameToDataInterface gameOfferedInterface,
                                IhmMainToDataInterface mainOfferedInterface, ComToDataClientInterface comOfferedInterface){
        this.userController = userController;
        this.dataToIhmGame = dataToIhmGame;
        this.dataToIhmMain = dataToIhmMain;
        this.dataToCom = dataToCom;
        this.gamesController = gamesController;
        this.gameOfferedInterface = gameOfferedInterface;
        this.mainOfferedInterface = mainOfferedInterface;
        this.comOfferedInterface= comOfferedInterface;
    }

    /**
     * Sets the users at the specified index.
     * @param user
     */
    public void setUser(User user){
        userController.setUser(user);
    }

    /**
     * Returns the list of users.
     * @return users
     */
    public List<User> getUsers(){
        return userController.getAllUsers();
    }

    /**
     * Returns the user at the specified index.
     * @param userId
     * @return the user at the specified index.
     */
    public User getUser(UUID userId){
        return userController.getUser(userId);
    }

    /**
     * Returns the dataToIhmMain interface.
     * @return dataToIhmMain
     */
    public IhmMainToDataInterface getIhmMainOfferedInterface(){
        return mainOfferedInterface;
    }

    /**
     * Returns the dataToIhmGame interface.
     * @return dataToIhmGame
     */
    public IhmGameToDataInterface getIhmGameOfferedInterface(){
        return gameOfferedInterface;
    }

    /**
     * Returns the dataToCom interface.
     * @return dataToCom
     */
    public ComToDataClientInterface getComOfferedInterface(){
        return comOfferedInterface;
    }

    /**
     * Sets the Ihm Game observer.
     * @param gameInterface
     */
    public void setGameObserver(DataToIhmGameConcrete gameInterface){
        this.dataToIhmGame = (DataToIhmGameServerInterface) gameInterface;
    }

    /**
     * Sets the Ihm Main observer.
     * @param mainInterface
     */
    public void setMainObserver(DataToIhmMainConcrete mainInterface){
        this.dataToIhmMain = (DataToIhmMainServerInterface) mainInterface;
    }

    /**
     * Sets the communication observer.
     * @param comInterface
     */
    public void setComObserver(DataToComConcrete comInterface){
        this.dataToCom = (DataToComServerInterface) comInterface;
    }

    /**
     * Gets the session.
     */
    public Session getSession(){
        return session;
    }

    /**
     * Sets the session.
     */
    public void setSession(Session session){
        this.session = session;
    }

    /**
     * Gets the user controller.
     * @return userController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * Sets tue user controller.
     * @param userController
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    /**
     * Gets the games controller.
     * @return gameController
     */
    public GamesController getGamesController() {
        return gamesController;
    }

    /**
     * Sets the games controller.
     * @param gamesController
     */
    public void setGamesController(GamesController gamesController) {
        this.gamesController = gamesController;
    }
}
