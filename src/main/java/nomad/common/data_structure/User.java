package nomad.common.data_structure;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class User extends Observable implements Serializable {

    private UserInfo userInfo;
    private ProfileStat profileStat;
    private List<Contact> contacts;
    private List<Category> categoryList;
    private List<Game> savedGames;
    private Server lastServer;

    public User(UserInfo userInfo) throws UnknownHostException {
        this.userInfo = userInfo;
        this.profileStat = new ProfileStat(0, 0, 0);
        this.contacts = new ArrayList<>();
        this.savedGames = new ArrayList<>();
        this.lastServer = new Server("default", InetAddress.getLocalHost(), 0);
    }

    public UUID getUserId() {
        return userInfo.getId();
    }

    public String getLogin() {
        return userInfo.getLogin();
    }

    public void setLogin(String login) {
        this.userInfo.setLogin(login);
    }

    public String getPassword() {
        return this.userInfo.getPassword();
    }

    public void setPassword(String password) {
        this.userInfo.setPassword(password);
    }

    public String getName() {
        return this.userInfo.getName();
    }

    public void setName(String name) {
        this.userInfo.setName(name);
    }

    public String getProfilePicture() {
        return this.userInfo.getProfilePicture();
    }

    public void setProfilePicture(String profilePicture) {
        this.userInfo.setProfilePicture(profilePicture);
    }

    public Date getBirthDate() {
        return this.userInfo.getBirthDate();
    }

    public void setBirthDate(Date birthDate) {
        this.userInfo.setBirthDate(birthDate);
    }

    public ProfileStat getProfileStat() {
        return profileStat;
    }

    public void setProfileStat(ProfileStat profileStat) {
        this.profileStat = profileStat;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void addCategory(Category category) throws CategoryException {
        for (Category c: this.categoryList
             ) {
            if (c.getName().equals(category.getName())){
                throw new CategoryException("Category already exists");
            }

        }
        this.categoryList.add(category);
    }
    public List<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

    public void addSavedGame(Game game){ this.savedGames.add(game); }
    public void removeSavedGame(Game game) {this.savedGames.remove(game);}
    public void addUser(UserLight user, Category category) throws CategoryException {
        for (Category c:this.categoryList
             ) {
            if (c.getName().equals(category.getName())){
                this.contacts.add(new Contact(user, category ));
            }

        }
        throw new CategoryException("Category doesn't exist");
    }

    public void updateCategory(Category lastCategory, Category newCategory)  {
        this.categoryList.remove(lastCategory);
        this.categoryList.add(newCategory);

    }

    public Server getLastServer() throws UnknownHostException {
        if (lastServer == null) { // TODO : modify lastServer assignation
            lastServer = new Server("default", InetAddress.getByName("127.0.0.1"), 12);
        }
        return lastServer;
    }

    public void setLastServer(Server lastServer) {
        this.lastServer = lastServer;
    }

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
