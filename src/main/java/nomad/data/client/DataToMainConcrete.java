package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmMainInterface;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
    public void createAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException {
        User u = new User(UUID.randomUUID(), login, pwd, name, profilePicture, birthDate,null,null,null,null);
        dataClientController.write(u);
    }


    /**
     * modifies an account
     * @param login login modified
     * @param pwd password modified
     * @param name name modified
     * @param profilePicture picture modified
     * @param birthDate date of Birth modified
     * @throws IOException error writing or reading file
     * @throws ClassNotFoundException class not found
     */
    public void modifyAccount(String login, String pwd, String name, String profilePicture, Date birthDate) throws IOException, ClassNotFoundException {
        User newUser = getUser();
        newUser.setLogin(login);
        newUser.setPassword(pwd);
        newUser.setName(name);
        newUser.setProfilePicture(profilePicture);
        newUser.setBirthDate(birthDate);
        try {
            dataClientController.updateProfileFile(newUser);
        } finally {
            dataClientController.getUserController().setUser(newUser);
        }
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
        List<User> listUser;
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
     * @param IP IP adress of the server
     * @param port Port of the user
     * @throws UserException User not found
     * @throws IOException Error in writing or reading file
     * @throws ClassNotFoundException class not found
     */
    public void login(String login, String password, String IP, int port) throws UserException, IOException, ClassNotFoundException {
        //1 - Verify account exists else throw exception

        User u = dataClientController.read(dataClientController.getPathProfile());
        if ((u.getLogin().equals(login)) && (u.getPassword().equals(password))) {
            dataClientController.getUserController().setUser(u);
            //2 Inform Com that a new user is connected
            dataClientController.getComToDataInterface().addConnectedUser(u);
            return;
        }

        throw new UserException("User doesn't exist");

    }

    // TODO : add logout method in ComToDataInterface

    /**
     * logout
     */
    public void logout(){
        //1 - get the id of the userConnected
        UUID userID = dataClientController.getUserController().getUser().getUserId();

        //2 - Reset all attributes
        dataClientController.reset();

        //3 - Call logout method of ComToDataInterface
        //dataClientController.getComToDataInterface().logout(userID);
    }

    //TODO: COM getProfileInfos()

    /**
     * @param idUserLight id of the User
     * @return User
     */
    public User getProfileInfos(UUID idUserLight){
        return null;
        //dataClientController.getComToDataInterface().getProfileInfos(idUserLight);
    }

    /**
     * Create a category
     * @param category new category
     * @throws CategoryException Category already exist
     * @throws IOException Error writing or reading file
     * @throws ClassNotFoundException Class not found
     */
    public void createCategory(Category category) throws CategoryException, IOException, ClassNotFoundException {
        //1- Create category
        dataClientController.getUserController().createCategory(category);

        //2- Update Profile
        dataClientController.updateProfileFile(getUser() );
    }

    /**
     * Add user to a category
     * @param user User to add to the category
     * @param category Category
     * @throws CategoryException Category doesn't exist
     * @throws IOException Error writing or reading file
     * @throws ClassNotFoundException Class not found
     */
    public void addUser(UserLight user, Category category) throws CategoryException, IOException, ClassNotFoundException {
        //1- Add user to a category
        dataClientController.getUserController().addUser(user, category);

        //2- Update profile
        dataClientController.updateProfileFile(getUser());
    }

    /**
     * Set permission of a contact
     * @param updatedContact Contact updated (with new permissions)
     * @throws CategoryException New Category not found
     * @throws IOException Error in writing or reading file
     * @throws ClassNotFoundException Class not found
     */
    public void setPermissions(Contact updatedContact) throws CategoryException, IOException, ClassNotFoundException {
        dataClientController.getUserController().setPermissions(updatedContact);
        dataClientController.updateProfileFile(getUser());
    }

    /**
     * Uptade category Permission
     * @param lastCategory Category without new permissions
     * @param updatedCategory Category updated
     * @throws CategoryException Category without new permissions not found
     * @throws IOException Error in writing or reading
     * @throws ClassNotFoundException Class not found
     */
    public void setPermissions(Category lastCategory, Category updatedCategory) throws CategoryException, IOException, ClassNotFoundException {
        //1- Set category permission
        dataClientController.getUserController().setPermissions(lastCategory, updatedCategory);
        //2- Update profile
        dataClientController.updateProfileFile(getUser());
    }

}
