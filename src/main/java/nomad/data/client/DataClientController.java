package nomad.data.client;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;
import nomad.common.interfaces.com.ComToDataClientInterface;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.common.interfaces.main.IhmMainToDataInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataClientController {

    UserController userController;
    private GameController gameController;
    private ComToDataClientInterface comToDataInterface;
    private IhmMainToDataInterface ihmMainToDataInterface;
    private IhmGameToDataInterface ihmGameToDataInterface;
    private String path;

    // Contains connected users, gamesInLobby, gamesInProgress
    Session session;

    public DataClientController(ComToDataClientInterface comToDataInterface,
                                IhmMainToDataInterface ihmMainToDataInterface,
                                IhmGameToDataInterface ihmGameToDataInterface) {
        this.path = "test"; // path might change !
        this.gameController = new GameController(null);
        this.userController = new UserController(null);
        this.ihmMainToDataInterface = ihmMainToDataInterface;
        this.comToDataInterface = comToDataInterface;
        this.ihmGameToDataInterface = ihmGameToDataInterface;
    }

    public Session getSession() {
        if (session == null) {
            initSession();
        }
        return session;
    }

    public GameLight getGameLight(){
        return this.gameController.getGameLight();
    }

    public UserController getUserController() {
        return userController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public ComToDataClientInterface getComToDataInterface(){
        return comToDataInterface;
    }

    public IhmMainToDataInterface getIhmMainToDataInterface() {
        return ihmMainToDataInterface;
    }

    public IhmGameToDataInterface getIhmGameToDataInterface() {
        return ihmGameToDataInterface;
    }

    public String getPathProfile(){
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void initSession(List<UserLight> users, List<GameLight> games) {
        session = new Session(users, games, new ArrayList<>());
    }

    public void initSession() {
        if(session == null) {
            session = new Session(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }

    public void reset(){
        this.session = null;
        this.getUserController().setUser(null);
        this.getGameController().setGame(null);
    }

    public void write(User user) throws IOException {
        // le try to open file at the beginning and close it at the end
        try (
            FileOutputStream fos = new FileOutputStream(user.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(user);
            oos.flush();
        }
    }

    public User read(String path) throws IOException, ClassNotFoundException {
        // le try to open file at the beginning and close it at the end
        try (
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (User) ois.readObject();
        }
    }

    public void updateProfileFile(User newUser) throws IOException, ClassNotFoundException {
        //1- Get all user
        User user = this.read(newUser.getName());

        //2- Modify the user connected
        if (user.getUserId().equals(this.userController.getUser().getUserId())) {
                this.getUserController().setUser(newUser);
        }

        //3 - Write the user with the user modified in the profile file
        this.write(user);
    }
}
