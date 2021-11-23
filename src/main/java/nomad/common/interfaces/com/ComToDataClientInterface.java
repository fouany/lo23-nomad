package nomad.common.interfaces.com;

import nomad.common.data_structure.User;

import java.io.IOException;
import java.util.UUID;

public interface ComToDataClientInterface {

    void askForSave(UUID game);

    void addConnectedUser(User user);

    void logout() throws IOException;

    void getProfile(UUID idUser);
}
