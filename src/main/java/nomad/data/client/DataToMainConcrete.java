package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmMainInterface;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Concretion of the game Interface.
 */
public class DataToMainConcrete implements DataToIhmMainInterface {

    /**
     * The main class of the client package
     */
    DataClientController dataClientController;

    /**
     * sets the controller
     * @param dataClientController - DataClientController : controller
     */
    public void setController(DataClientController dataClientController) {
        this.dataClientController = dataClientController;
    }

    /**
     * gets the current user
     * @return User
     */
    @Override
    public User getUser() {
        return dataClientController.getUserController().getUser();
    }

    /**
     * gets the current user in Player format
     * @return Player
     */
    @Override
    public Player getPlayer() {
        return dataClientController.getUserController().getPlayer();
    }

    /**
     * gets the current user in UserLight format
     * @return UserLight
     */
    @Override
    public UserLight getUserLight() {
        return dataClientController.getUserController().getUserLight();
    }

    /**
     * gets the current Game
     * @return Game
     */
    @Override
    public Game getGame() {
        return dataClientController.getGameController().getGame();
    }

    /**
     * gets the current Game in GameLight format
     * @return GameLight
     */
    @Override
    public GameLight getGameLight() {
        return dataClientController.getGameController().getGameLight();
    }

    /**
     * gets the game stored whose id is gameId
     * @param gameId - UUID : id of the game stored
     * @return Game stored
     */
    public Game getStoredGame(UUID gameId) {
        return dataClientController.getUserController().getGameStored(gameId);
    }

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
    @Override
    public void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException, UserException {
        //Verify information
        if (login.isEmpty() || pwd.isEmpty() || name.isEmpty()) {
            throw new UserException("Some user information is empty");
        }

        //Create User
        String passwordHash = hashPassword(pwd);
        if (passwordHash == null){
            throw new UserException("Password not properly hashed");
        }
        User u = new User(new UserInfo(login, passwordHash, name, profilePicture, birthDate));

        //Verify there is not already a file with this login
        try {
            dataClientController.read(login);
        } catch (IOException | ClassNotFoundException e) {
            //if there is no file => create
            dataClientController.write(u);
            dataClientController.setPath(""); // to ensure a clean path for further import/export
            return;
        }
        //if there is already a user with this login
        throw new UserException("Login already exist");
    }

    /**
     * hashes the password so it is stored safely in the user file
     *
     * @param password : password to hash
     * @return String : hash password in string format
     */
    public String hashPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return castHashToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(DataToMainConcrete.class.getName()).log(Level.WARNING, "Error caused by {0}.", e.toString());
        }
        return (null);
    }

    /**
     * casts the encoded hash (in bytes []) into a String, so it matches the type of password attribute String
     *
     * @param encodedHash : encoded hash in bytes[]
     * @return String : encoded hash in string format
     */
    public String castHashToString(byte[] encodedHash) {
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (int i = 0; i < encodedHash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

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
    @Override
    public void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException {

        if (login.isEmpty()) try {
            throw new UserException("Login is empty or not valid");
        } catch (UserException e) {
            Logger.getLogger(DataToMainConcrete.class.getName()).log(Level.WARNING, "Error caused by {0}.", e.toString());
        }

        // stores the old login in order to update the name of the file
        String oldLogin = getUser().getLogin();

        // retrieves the current user from the DataClientController
        User newUser = getUser();
        // updates the profile information
        newUser.setLogin(login);
        if (pwd != null ){
            newUser.setPassword(hashPassword(pwd));
        }
        newUser.setName(name);
        newUser.setProfilePicture(profilePicture);
        newUser.setBirthDate(birthDate);

        if (!oldLogin.equals(login)) {
            Files.delete(Paths.get(oldLogin));
        }
        // updated information is written in the file with previous given path
        dataClientController.write(newUser);
    }

    /**
     * Imports a profile by reading a file given by a path and creates the account of the imported user/profile
     *
     * @param path - String : path of the file
     * @throws IOException : error writing and reading file
     * @throws ClassNotFoundException : class not found
     */
    @Override
    public void addAccount(String path) throws IOException, ClassNotFoundException, UserException {
        User u = null;
        try {
            // Reading the user information from the file selected by the user
            u = dataClientController.read(path);
        } finally {
            if (u != null) {
                // Creating the account of the user
                createAccount(u.getLogin(), u.getPassword(), u.getName(), u.getProfilePicture(), u.getBirthDate());

                //write user
                dataClientController.write(u);
            }
        }
    }

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
    @Override
    public void login(String login, String password, String ip, int port) throws UserException, IOException, ClassNotFoundException {
        //1 - Verify account exists else throw exception
        dataClientController.setPath("");
        User u = dataClientController.read(login);
        if ((u.getLogin().equals(login)) && (u.getPassword().equals(hashPassword(password)))) {
            dataClientController.getUserController().setUser(u);
            //2 Make sure the right port and IP is saved in user
            if (ip != null) {
                dataClientController.getUserController().getUser().getLastServer().setIpAddress(InetAddress.getByName(ip));
            }
            if (port != 0) {
                dataClientController.getUserController().getUser().getLastServer().setPort(port);
            }
            //2 Inform Com that a new user is connected
            dataClientController.getComToDataInterface().addConnectedUser(u);
            dataClientController.getIhmMainToDataInterface().updateObservable(dataClientController.getSession());
            return;
        }
        
        throw new UserException("User doesn't exist or wrong credentials");

    }


    /**
     * logout
     */
    @Override
    public void logout() {
        // Reset all attributes
        dataClientController.reset();

        // Call logout method of ComToDataInterface
        dataClientController.getComToDataInterface().logout();
    }

    /**
     * gets the profile of another user (which has a specific id)
     *
     * @param idUserLight - UUID : id of the User
     */
    @Override
    public void getProfileInfos(UUID idUserLight) {
        //1- Get the profile of the user
        dataClientController.getComToDataInterface().getProfileMain(idUserLight);
    }

    /**
     * Create a category
     *
     * @param category - Category : new category
     * @throws CategoryException : Error Category already exist
     * @throws IOException : Error in writing file
     */
    @Override
    public void createCategory(Category category) throws CategoryException, IOException {
        //1- Create category
        dataClientController.getUserController().createCategory(category);

        //2- Update Profile
        dataClientController.write(getUser());
    }

    /**
     * adds user to a category
     *
     * @param user - UserLight : User to add to the category
     * @param category - Category : category
     * @throws CategoryException : Category doesn't exist
     * @throws IOException : Error in writing file
     */
    @Override
    public void addUser(UserLight user, Category category) throws CategoryException, IOException {
        //1- Add user to a category
        dataClientController.getUserController().addUser(user, category);

        //2- Update profile
        dataClientController.write(getUser());
    }

    /**
     * sets permission of a contact
     *
     * @param updatedContact - Contact : contact updated (with new permissions)
     * @throws CategoryException : New Category not found
     * @throws IOException : Error in writing file
     */
    @Override
    public void setPermissions(Contact updatedContact) throws CategoryException, IOException {
        dataClientController.getUserController().setPermissions(updatedContact);
        dataClientController.write(getUser());
    }

    /**
     * updates category Permission
     *
     * @param lastCategory - Category : category without new permissions
     * @param updatedCategory - Category : category updated
     * @throws CategoryException : category without new permissions not found
     * @throws IOException :Error in writing or reading
     */
    @Override
    public void setPermissions(Category lastCategory, Category updatedCategory) throws CategoryException, IOException {
        //1- Set category permission
        dataClientController.getUserController().setPermissions(lastCategory, updatedCategory);
        //2- Update profile
        dataClientController.write(getUser());
    }

    /**
     * verifies if there is enough players in a game (= if there is an opponent)
     * @param game - GameLight : game
     * @throws GameException : No opponent have been added to the game yet
     */
    @Override
    public void enoughPlayers(GameLight game) throws GameException {
        Game g = dataClientController.getGameController().getGame();
        if( g.getGameId().equals(game.getGameId())){
            if (g.getOpponent() == null) {
                throw new GameException("No opponent added to the game yet.");
            } else {
                dataClientController.getComToDataInterface().enoughPlayers(game.getGameId(), g.getOpponent().getId());
            }
        } else {
            throw new GameException("Can't find game");
        }
    }

    /**
     * rejects the opponent in a game
     * @param game - GameLight : game
     * @throws GameException : No opponent have been added to the game yet
     */
    @Override
    public void rejectPlayers(GameLight game) throws GameException {
        Game g = dataClientController.getGameController().getGame();
        if( g.getGameId().equals(game.getGameId())) {
            if (g.getOpponent() == null) {
                throw new GameException("No opponent added to the game yet.");
            } else {
                g.setOpponent(null);
                dataClientController.getComToDataInterface().rejectPlayers(game.getGameId());
            }
        } else {
            throw new GameException("Can't find game");
        }
    }


    /**
     * Exports a User and its respective data to a file specified by the path
     *
     * @param exportPath - String : path of the file
     */
    public void exportProfile(String exportPath) throws IOException {
        // Retrieve the user and the respective data
        User user = dataClientController.getUserController().getUser();

        // Setting the path for the file to export

        String path = exportPath;
        if (exportPath.contains("\\")){
            path = path+"\\";
        }else if (exportPath.contains("/")){
            path = path+"/";
        }
        dataClientController.setPath(path);

        System.out.println("Path = " + dataClientController.getPathProfile());


        //Verify there is not already a file with this login
        try {
            dataClientController.read(path+user.getLogin());
        } catch (IOException | ClassNotFoundException e) {
            //if there is no file => create one
            dataClientController.write(user);
            dataClientController.setPath(""); // to ensure a clean path for further import/export
            return;
        }

        //if there is already a user with this login
        throw new IOException("File already exist");


    }

}
