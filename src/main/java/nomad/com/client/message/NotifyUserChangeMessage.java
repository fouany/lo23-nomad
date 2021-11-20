package nomad.com.client.message;

import nomad.com.client.controller.ComClientController;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;

/**
 * NotifyUserChange Message - Client Side
 */
public class NotifyUserChangeMessage extends ComClientMessage {
    /**
     * player
     */
    private final User user;

    /**
     * bool
     */
    private final Boolean connected;

    /**
     * Constructor
     * @param clientController
     * @param user
     * @param connected
     */
    public NotifyUserChangeMessage(ComClientController clientController, User user, Boolean connected) {
        super(clientController);
        this.user = user;
        this.connected = connected;
    }
}