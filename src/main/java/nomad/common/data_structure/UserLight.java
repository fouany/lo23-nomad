package nomad.common.data_structure;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a version of the User used when minimum information is required
 */
public class UserLight implements Serializable {

    /**
     * User's Id
     */
    private UUID id;

    /**
     * User's login
     */
    private String login;

    /**
     * Constructor of a UserLight
     * @param id - the UserLight's id
     * @param login - the UserLight's login
     */
    public UserLight(UUID id, String login) {
        this.id = id;
        this.login = login;
    }

    /**
     * Creates a UserLight from a Player : uses the Player's id and login.
     * @param player - the Player from which the information will be used
     */
    public UserLight(Player player) {
        this.id = player.getId();
        this.login = player.getLogin();
    }

    /**
     * Returns the id of the UserLight
     * @return id - UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets a new id
     * @param id - desired UUID
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the UserLight's login
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
     * Returns a string representation of the object
     * @return UserLightString - String
     */
    @Override
    public String toString() {
        return "UserLight{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
