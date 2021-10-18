package model;

import java.io.Serializable;
import java.util.UUID;

public class UserLight implements Serializable {

    private UUID id;
    private String login;

    public UserLight(UUID id, String login) {
        this.id = id;
        this.login = login;
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

    @Override
    public String toString() {
        return "UserLight{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
