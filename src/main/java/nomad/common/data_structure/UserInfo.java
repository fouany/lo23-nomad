package nomad.common.data_structure;

import java.util.Date;
import java.util.UUID;

/**
 * Contains user static information.
 */
public class UserInfo {

    private UUID userId;
    private String login;
    private String password;
    private String name;
    private String profilePicture;
    private Date birthDate;

    public UserInfo(String login, String password, String name, String profilePicture, Date birthDate) {
        this.userId = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
    }

    public UUID getId(){
        return userId;
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
}
