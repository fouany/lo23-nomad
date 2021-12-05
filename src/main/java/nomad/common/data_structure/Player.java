package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Observable;
import java.util.UUID;

public class Player extends Observable implements Serializable {

    private UUID id;
    private String login;
    private String profilePicture;

    public Player(UUID id, String login, String profilePicture) {
        this.id = id;
        this.login = login;
        this.profilePicture = profilePicture;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
