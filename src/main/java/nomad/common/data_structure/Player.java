package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Observable;
import java.util.UUID;

/**
 * Represents a player
 */
public class Player extends Observable implements Serializable {

    /**
     * ID of the player
     */
    private UUID id;

    /**
     * login of the player
     */
    private String login;

    /**
     * Path to profile picture of the player
     */
    private String profilePicture;

    /**
     * Player constructor
     * @param id - ID of the player
     * @param login - Login of the player
     * @param profilePicture - Path to the profile picture of the player
     */
    public Player(UUID id, String login, String profilePicture) {
        this.id = id;
        this.login = login;
        this.profilePicture = profilePicture;
    }

    /**
     * Returns the player ID
     * @return player ID as a UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the player ID
     * @param id - player ID as a UUID
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the player login
     * @return player login as a String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the player login
     * @param login - player login as a String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the path to profile picture of the player
     * @return the path to profile picture of the player as a String
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the path to profile picture of the player
     * @param profilePicture - Path to profile picture of the player
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
