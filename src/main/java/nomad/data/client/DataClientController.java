package nomad.data.client;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataClientInterface;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.common.interfaces.main.IhmMainToDataInterface;

import java.io.*;
import java.util.ArrayList;

/**
 * Controller for the data client
 */
public class DataClientController {

    /**
     * The class in charge of managing users
     */
    UserController userController;

    /**
     * The class in charge of managing games
     */
    private GameController gameController;

    /**
     * The class in charge of calling the com's module methods
     */
    private ComToDataClientInterface comToDataInterface;

    /**
     * The class in charge of calling the ihmMain's module methods
     */
    private IhmMainToDataInterface ihmMainToDataInterface;

    /**
     * The class in charge of calling the ihmGame's module methods
     */
    private IhmGameToDataInterface ihmGameToDataInterface;

    /**
     * path of the file to store data
     */
    private String path;

    /**
     * contains connected users, gamesInLobby, gamesInProgress
     */
    Session session;

    /**
     * Constructor
     * @param comToDataInterface - ComToDataClientInterface : com interface
     * @param ihmMainToDataInterface -  IhmMainToDataInterface : ihmMain interface
     * @param ihmGameToDataInterface - IhmGameToDataInterface : ihmGame interface
     */
    public DataClientController(ComToDataClientInterface comToDataInterface,
                                IhmMainToDataInterface ihmMainToDataInterface,
                                IhmGameToDataInterface ihmGameToDataInterface) {
        this.path = ""; // path might change !
        this.gameController = new GameController(null);
        this.userController = new UserController(null);
        this.ihmMainToDataInterface = ihmMainToDataInterface;
        this.comToDataInterface = comToDataInterface;
        this.ihmGameToDataInterface = ihmGameToDataInterface;
    }

    /**
     * gets the session
     * @return Session
     */
    public Session getSession() {
        if (session == null) {
            initSession();
        }
        return session;
    }

    /**
     * gets the current game in GameLight format
     * @return GameLight
     */
    public GameLight getGameLight(){
        return this.gameController.getGameLight();
    }

    /**
     * gets the UserController
     * @return UserController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * gets the GameController
     * @return GameController
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * gets the ComToData interface
     * @return ComToDataClientInterface
     */
    public ComToDataClientInterface getComToDataInterface(){
        return comToDataInterface;
    }

    /**
     * gets the IhmMainToData interface
     * @return IhmMainToDataInterface
     */
    public IhmMainToDataInterface getIhmMainToDataInterface() {
        return ihmMainToDataInterface;
    }

    /**
     * gets the IhmGameToData interface
     * @return IhmGameToDataInterface
     */
    public IhmGameToDataInterface getIhmGameToDataInterface() {
        return ihmGameToDataInterface;
    }

    /**
     * gets the path of the file to store data
     * @return String
     */
    public String getPathProfile(){
        return path;
    }

    /**
     * sets the path of the file to store data
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * initializes the session
   */
    public void initSession() {
        if(session == null) {
            session = new Session(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }

    /**
     * sets the session, the current user and the current game to default values
     */
    public void reset(){
        this.session.getConnectedUsers().clear();
        this.session.getGamesInLobby().clear();
        this.session.getGamesInPlay().clear();
        this.getUserController().setUser(null);
        this.getGameController().setGame(null);
    }

    /**
     * writes user profile in a file (path = this.path + login)
     * @param user - User : profile to write
     * @throws IOException : Error in file writing
     */
    public void write(User user) throws IOException {
        // le try to open file at the beginning and close it at the end
        try (
            FileOutputStream fos = new FileOutputStream(path + user.getLogin());
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(user);
            oos.flush();
        }
    }

    /**
     * read user profile from a file
     * @param path - String : path of the file
     * @return User read
     * @throws IOException : Error in the file reading
     * @throws ClassNotFoundException : class not found
     */
    public User read(String path) throws IOException, ClassNotFoundException {
        // le try to open file at the beginning and close it at the end
        try (
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (User) ois.readObject();
        }
    }
}
