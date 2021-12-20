package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Represents user static information.
 */
public class UserInfo implements Serializable {

    /**
     * The user's id
     */
    private UUID userId;

    /**
     * The user's login
     */
    private String login;

    /**
     * The user's password
     */
    private String password;

    /**
     * The user's name
     */
    private String name;

    /**
     * The user's profile picture
     */
    private String profilePicture;

    /**
     * The user's birth-date
     */
    private Date birthDate;

    /**
     * Constructor of UserInfo
     * @param login - The user's login
     * @param password - The user's password
     * @param name - The user's name
     * @param profilePicture - The user's profilePicture
     * @param birthDate - The user's birth-date
     */
    public UserInfo(String login, String password, String name, String profilePicture, Date birthDate) {
        this.userId = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
    }

    /**
     * Returns the id of the UserLight
     * @return id - UUID
     */
    public UUID getId(){
        return userId;
    }

    /**
     * Returns the login of the UserLight
     * @return login - String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login of the user
     * @param login - desired login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the password of the UserLight
     * @return password - String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * @param password - desired password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the name of the UserLight
     * @return name - String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the login of the user
     * @param name - desired name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the profile picture of the UserLight
     * @return profilePicture - String
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the profile Picture of the user
     * @param profilePicture - desired profilePicture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Returns the BirthDate of the UserLight
     * @return birthDate - date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth-date of the user
     * @return birthdate - Date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
