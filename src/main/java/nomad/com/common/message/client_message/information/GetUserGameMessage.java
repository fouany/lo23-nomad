package nomad.com.common.message.client_message.information;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.User;

/**
 * Use to get the user in a specific game
 */
public class GetUserGameMessage extends BaseClientMessage {
    /**
     * User
     */
    private final User user;

    /**
     * Constructor
     * @param user User
     */
    public GetUserGameMessage(User user) {
        this.user = user;
    }

    /**
     * Process to IHM Game
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getIhmGameToCom().getUser(user);
    }
}
