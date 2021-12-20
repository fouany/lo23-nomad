package nomad.common.data_structure;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class User extends Observable implements Serializable {

    /**
     * User static information
     */
    private UserInfo userInfo;
    /**
     * User statistics
     */
    private ProfileStat profileStat;

    /**
     * User contacts
     */
    private List<Contact> contacts;

    /**
     * User category list
     */
    private List<Category> categoryList;

    /**
     * User saved games
     */
    private List<Game> savedGames;

    /**
     * Last server used by the user
     */
    private Server lastServer;

    /**
     * User constructor
     * @param userInfo - User static information
     * @throws UnknownHostException
     */
    public User(UserInfo userInfo) throws UnknownHostException {
        this.userInfo = userInfo;
        this.profileStat = new ProfileStat(0, 0, 0);
        this.contacts = new ArrayList<>();
        this.savedGames = new ArrayList<>();
        this.lastServer = new Server("default", InetAddress.getLocalHost(), 0);
    }

    /**
     * Returns the user ID
     * @return user ID as a UUID
     */
    public UUID getUserId() {
        return userInfo.getId();
    }

    /**
     * Returns the user login
     * @return user login as a String
     */
    public String getLogin() {
        return userInfo.getLogin();
    }

    /**
     * Sets the user login
     * @param login - User login as a String
     */
    public void setLogin(String login) {
        this.userInfo.setLogin(login);
    }

    /**
     * Returns the user password
     * @return user password as a String
     */
    public String getPassword() {
        return this.userInfo.getPassword();
    }

    /**
     * Sets the user password
     * @param password - User password as a String
     */
    public void setPassword(String password) {
        this.userInfo.setPassword(password);
    }

    /**
     * Returns the username
     * @return username as a String
     */
    public String getName() {
        return this.userInfo.getName();
    }

    /**
     * Sets the username
     * @param name - Username as a String
     */
    public void setName(String name) {
        this.userInfo.setName(name);
    }

    /**
     * Returns the path to profile picture of the user
     * @return the path to profile picture of the user as a String
     */
    public String getProfilePicture() {
        return this.userInfo.getProfilePicture();
    }

    /**
     * Sets the path to profile picture of the player
     * @param profilePicture - Path to profile picture of the player
     */
    public void setProfilePicture(String profilePicture) {
        this.userInfo.setProfilePicture(profilePicture);
    }

    /**
     * Returns the user birth-date
     * @return user birth-date as a Date
     */
    public Date getBirthDate() {
        return this.userInfo.getBirthDate();
    }

    /**
     * Sets the user birth-date
     * @param birthDate - user birth-date as a Date
     */
    public void setBirthDate(Date birthDate) {
        this.userInfo.setBirthDate(birthDate);
    }

    /**
     * Returns the user statistics
     * @return user statistics as a ProfileStat
     */
    public ProfileStat getProfileStat() {
        return profileStat;
    }

    /**
     * Sets the user statistics
     * @param profileStat - user statistics as a ProfileStat
     */
    public void setProfileStat(ProfileStat profileStat) {
        this.profileStat = profileStat;
    }

    /**
     * Returns the user contacts
     * @return user contacts as a list of Contact
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets the user list of contacts
     * @param contacts - user contacts as a list of Contact
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns the user categories
     * @return user categories as a list of Category
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Sets the user list of contacts
     * @param categoryList - user categories as a list of Category
     */
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Adds a category to the user list of categories
     * @param category - Category to be added
     * @throws CategoryException
     */
    public void addCategory(Category category) throws CategoryException {
        for (Category c: this.categoryList
             ) {
            if (c.getName().equals(category.getName())){
                throw new CategoryException("Category already exists");
            }

        }
        this.categoryList.add(category);
    }

    /**
     * Returns the user saved games
     * @return user saved games as a list of Game
     */
    public List<Game> getSavedGames() {
        return savedGames;
    }

    /**
     * Sets the user list of contacts
     * @param savedGames - user saved games as a list of Game
     */
    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

    /**
     * Adds a game to the user list of saved games
     * @param game - game to be added
     */
    public void addSavedGame(Game game){ this.savedGames.add(game); }

    /**
     * Removes a game from the user list of saved games
     * @param game - game to be removed
     */
    public void removeSavedGame(Game game) {this.savedGames.remove(game);}


    /**
     * Adds a user to the user contacts list
     * @param user - user to be added to the contact list
     * @param category -category of the user added to contact list
     * @throws CategoryException
     */
    public void addUser(UserLight user, Category category) throws CategoryException {
        for (Category c:this.categoryList
             ) {
            if (c.getName().equals(category.getName())){
                this.contacts.add(new Contact(user, category ));
            }

        }
        throw new CategoryException("Category doesn't exist");
    }

    /**
     * Updates a category
     * @param lastCategory - category which will be updated
     * @param newCategory - new category : it will replace the old category
     */
    public void updateCategory(Category lastCategory, Category newCategory)  {
        this.categoryList.remove(lastCategory);
        this.categoryList.add(newCategory);

    }

    /**
     * Get user last server
     * @return the last server used by the user as a Server object
     */
    public Server getLastServer() {
        if (lastServer == null) { // TODO : modify lastServer assignation
            try {
                lastServer = new Server("default", InetAddress.getByName("127.0.0.1"), 12);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return lastServer;
    }

    /**
     * Sets the last server used by the user
     * @param lastServer - last server used by the user as a Server object
     */
    public void setLastServer(Server lastServer) {
        this.lastServer = lastServer;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "model.User{" +
                "userId=" + this.userInfo.getId() +
                ", login='" + this.userInfo.getLogin() + '\'' +
                ", password='" + this.userInfo.getPassword() + '\'' +
                ", name='" + this.userInfo.getName() + '\'' +
                ", profilePicture='" + this.userInfo.getProfilePicture() + '\'' +
                ", birthDate=" + this.userInfo.getBirthDate() +
                ", profileStat=" + profileStat +
                ", contacts=" + contacts +
                ", savedGames=" + savedGames +
                ", lastServer=" + lastServer +
                '}';
    }
}
