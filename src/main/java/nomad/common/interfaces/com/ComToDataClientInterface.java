package nomad.common.interfaces.com;

import nomad.common.data_structure.User;

import java.util.UUID;

public interface ComToDataClientInterface {

    void askForSave(UUID game);

    void addConnectedUser(User user);

    void logout(User user);

    void getProfile(UUID idUser);
}
