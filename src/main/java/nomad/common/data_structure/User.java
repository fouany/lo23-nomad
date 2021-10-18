package model;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class User extends Observable {

    private UUID userId;
    private String login;
    private String password;
    private String name;
    private String profilePicture;
    private Date birthDate;
    private ProfileStat profileStat;
    private List<Contact> contacts;
    private List<Game> savedGames;
    private Server lastServer;

    public User(UUID userId, String login, String password, String name, String profilePicture, Date birthDate, ProfileStat profileStat, List<Contact> contacts, List<Game> savedGames, Server lastServer) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
        this.profileStat = profileStat;
        this.contacts = contacts;
        this.savedGames = savedGames;
        this.lastServer = lastServer;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public List<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(List<Game> savedGames) {
        this.savedGames = savedGames;
    }

    public Server getLastServer() {
        return lastServer;
    }

    public void setLastServer(Server lastServer) {
        this.lastServer = lastServer;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", birthDate=" + birthDate +
                ", profileStat=" + profileStat +
                ", contacts=" + contacts +
                ", savedGames=" + savedGames +
                ", lastServer=" + lastServer +
                '}';
    }
}
