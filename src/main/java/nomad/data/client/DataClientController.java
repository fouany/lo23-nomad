package nomad.data.client;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataInterface;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.common.interfaces.main.IhmMainToDataInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataClientController {

    UserController userController;
    private GameController gameController;
    private ComToDataInterface comToDataInterface;
    private IhmMainToDataInterface ihmMainToDataInterface;
    private IhmGameToDataInterface ihmGameToDataInterface;
    private String path;

    // Contains connected users, gamesInLobby, gamesInProgress
    Session session;

    public DataClientController() {
        this.path = "test.txt"; // path might change !
        this.gameController = new GameController(null);
        this.userController = new UserController(null);
    }

    public Session getSession() {
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

    public ComToDataInterface getComToDataInterface(){
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

    public void reset(){
        this.session = null;
        this.getUserController().setUser(null);
        this.getGameController().setGame(null);
    }

    public void write(List<User> userList, String path) throws IOException {
        Path pathFichier = Paths.get(path);
        // le try to open file at the beginning and close it at the end
        try (
            final OutputStream os = Files.newOutputStream(pathFichier);
            ObjectOutputStream  out = new ObjectOutputStream(os);
        ){
            out.writeObject(userList);
            out.flush();
        }
    }

    public List<User> read(String path) throws IOException, ClassNotFoundException {
        Path pathFichier = Paths.get(path);
        // le try to open file at the beginning and close it at the end
        try (
            InputStream is = Files.newInputStream(pathFichier);
            ObjectInputStream  in = new ObjectInputStream(is);
        ) {
            return (List<User>) in.readObject();
        }
    }

    public void updateProfileFile(String file, User newUser) throws IOException, ClassNotFoundException {
        //1- Get all user
        List<User> listUser =this.read(file);

        //2- Modify the user connected
        for (User u: listUser
        ) {
            if (u.getUserId().equals(this.userController.getUser().getUserId())) {
                this.getUserController().setUser(newUser);
                break;
            }
        }

        //3 - Write the user list with the user modified in the profile file
        this.write(listUser, file);
    }
}
