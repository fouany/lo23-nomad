package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmMainInterface;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

public class DataToMainConcrete  implements DataToIhmMainInterface {

    DataClientController dataClientController;

    public void setController(DataClientController dataClientController) {
        this.dataClientController = dataClientController;
    }

    public User getUser() { return dataClientController.getUserController().getUser();}

    public Player getPlayer() { return dataClientController.getUserController().getPlayer();}

    public UserLight getUserLight() {return dataClientController.getUserController().getUserLight();}

    public Game getGame() {return dataClientController.getGameController().getGame();}

    public GameLight getGameLight() {return dataClientController.getGameController().getGameLight();}

    public Game getStoredGame(UUID gameId){
        return dataClientController.getUserController().getGameStored(gameId);
    }

    /**
     * creates an account and stores it in a file
     * @param login login of the user
     * @param pwd password of the user
     * @param name name of the user
     * @param profilePicture picture of the user
     * @param birthDate date of birth of the user
     * @throws IOException error writing file
     */
    public void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException, UserException {
        //Verify information
        if (login.isEmpty() || pwd.isEmpty() || name.isEmpty()){
            throw new UserException("Some user information is not empty");
        }

        //Create User
        String passwordHash = hashPassword(pwd);
        User u = new User(new UserInfo(login, passwordHash, name, profilePicture, birthDate));

            //Verify there is not already a file with this login
            try {
                dataClientController.read(login);
            } catch (IOException | ClassNotFoundException e) {
                //if there is no file => create
                dataClientController.write(u);
                return;
            }
            //if there is already a user with this login
            throw new UserException("Login already exist");
    }

    /**
     * hashes the password so it is stored safely in the user file
     * @param password
     * @return
     */
    public String hashPassword(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return castHashToString(encodedHash);
    }

    /**
     * casts the encoded hash (in bytes []) into a String, so it matches the type of password attribute String
     * @param encodedHash
     * @return
     */
    public String castHashToString(byte [] encodedHash){
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (int i = 0; i < encodedHash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * modifies an account
     * @param login login modified
     * @param pwd password modified
     * @param name name modified
     * @param profilePicture picture modified
     * @param birthDate date of Birth modified
     * @throws IOException error writing or reading file
     */
    public void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException {

        if (login.isEmpty()) try {
            throw new UserException("Login is empty or not valid");
        } catch (UserException e) {
            e.printStackTrace();
        }

        // stores the old login in order to update the name of the file
        String oldLogin = getUser().getLogin();

        // retrieves the current user from the DataClientController
        User newUser = getUser();
        // updates the profile information
        newUser.setLogin(login);
        newUser.setPassword(hashPassword(pwd));
        newUser.setName(name);
        newUser.setProfilePicture(profilePicture);
        newUser.setBirthDate(birthDate);

        if (!oldLogin.equals(login)) {
            File fileToDelete = new File(dataClientController.getPathProfile());
            // deletion of file, in order to create a new one with updated details
            if (!fileToDelete.delete())
                throw new NoSuchFileException("No such file exception, please check the path");
            // sets the new path if the login is different
            dataClientController.setPath(login);
        }
        // updated information is written in the file with previous given path
        dataClientController.write(newUser);
    }

    /**
     * @param path
     * @throws IOException error writing and reading file
     * @throws ClassNotFoundException class not found
     */
    // TODO : return boolean or exception ??
    public void addAccount(String path) throws IOException, ClassNotFoundException {
        //try to read file
        User newUser = null;
        try{
            newUser = dataClientController.read(path);
        } finally {
            //try to read the file with all the user and the new user
            dataClientController.write(newUser);
        }
    }

    // TODO : boolean or exception ??

    /**
     * Login
     * @param login login of the user
     * @param password password of the user
     * @param ip IP address of the server
     * @param port Port of the user
     * @throws UserException User not found
     * @throws IOException Error in writing or reading file
     * @throws ClassNotFoundException class not found
     */
    public void login(String login, String password, String ip, int port) throws UserException, IOException, ClassNotFoundException {
        //1 - Verify account exists else throw exception
        dataClientController.setPath(login);
        User u = dataClientController.read(dataClientController.getPathProfile());
        if ((u.getLogin().equals(login)) && (u.getPassword().equals(password))) {
            dataClientController.getUserController().setUser(u);
            //2 Make sure the right port and IP is saved in user
            if (ip != null){
                dataClientController.getUserController().getUser().getLastServer().setIpAddress(InetAddress.getByName(ip));
            }
            if (port != 0){
                dataClientController.getUserController().getUser().getLastServer().setPort(port);
            }
            //2 Inform Com that a new user is connected
            dataClientController.getComToDataInterface().addConnectedUser(u);
            dataClientController.getIhmMainToDataInterface().updateObservable(dataClientController.getSession());
            return;
        }

        throw new UserException("User doesn't exist");

    }

    // TODO : add logout method in ComToDataInterface

    /**
     * logout
     */
    public void logout(){
        // Reset all attributes
        dataClientController.reset();

        // Call logout method of ComToDataInterface
        dataClientController.getComToDataInterface().logout();
    }

    //TODO: COM getProfileInfos()

    /**
     * @param idUserLight id of the User
     * @return User
     */
    public User getProfileInfos(UUID idUserLight){
        return null; // TODO : implement and call getProfile in ComClientToData
    }

    /**
     * Create a category
     * @param category new category
     * @throws CategoryException Category already exist
     * @throws IOException Error writing or reading file
     */
    public void createCategory(Category category) throws CategoryException, IOException {
        //1- Create category
        dataClientController.getUserController().createCategory(category);

        //2- Update Profile
        dataClientController.write(getUser());
    }

    /**
     * Add user to a category
     * @param user User to add to the category
     * @param category Category
     * @throws CategoryException Category doesn't exist
     * @throws IOException Error writing or reading file
     */
    public void addUser(UserLight user, Category category) throws CategoryException, IOException {
        //1- Add user to a category
        dataClientController.getUserController().addUser(user, category);

        //2- Update profile
        dataClientController.write(getUser());
    }

    /**
     * Set permission of a contact
     * @param updatedContact Contact updated (with new permissions)
     * @throws CategoryException New Category not found
     * @throws IOException Error in writing or reading file
     */
    public void setPermissions(Contact updatedContact) throws CategoryException, IOException {
        dataClientController.getUserController().setPermissions(updatedContact);
        dataClientController.write(getUser());
    }

    /**
     * Update category Permission
     * @param lastCategory Category without new permissions
     * @param updatedCategory Category updated
     * @throws CategoryException Category without new permissions not found
     * @throws IOException Error in writing or reading
     */
    public void setPermissions(Category lastCategory, Category updatedCategory) throws CategoryException, IOException {
        //1- Set category permission
        dataClientController.getUserController().setPermissions(lastCategory, updatedCategory);
        //2- Update profile
        dataClientController.write(getUser());
    }

    @Override
    public void enoughPlayers(GameLight game) throws GameException {
        Game g = dataClientController.getGameController().getGame();
        if (g.getOpponent()==null){
            throw new GameException("No opponent have been added to the game yet.");
        }
        else{
            dataClientController.getComToDataInterface().enoughPlayers(game.getGameId(), g.getOpponent().getId());
        }
    }

    @Override
    public void rejectPlayers(GameLight game) throws GameException {
        Game g = dataClientController.getGameController().getGame();
        if (g.getOpponent()==null){
            throw new GameException("No opponent have been added to the game yet.");
        }
        else{
            g.setOpponent(null);
            dataClientController.getComToDataInterface().rejectPlayers(game.getGameId());
        }
    }

}
