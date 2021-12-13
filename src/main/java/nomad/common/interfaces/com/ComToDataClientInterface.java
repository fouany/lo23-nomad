package nomad.common.interfaces.com;

import nomad.common.data_structure.User;

import java.util.UUID;

public interface ComToDataClientInterface {

    void askForSave(UUID game);

    void addConnectedUser(User user);

    void logout();

    void getProfile(UUID userId);

    void enoughPlayers(UUID gameId, UUID opponentId);

    void rejectPlayers(UUID gameId);
}
