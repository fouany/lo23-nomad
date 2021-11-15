package nomad.com.client.message;

import nomad.com.client.controller.ComClientController;
import nomad.common.data_structure.Player;
import nomad.common.interfaces.data.DataToComInterface;

/**
 * NotifyUserChange Message - Client Side
 */
public class NotifyUserChangeMessage extends ComClientMessage {
    /**
     * player
     */
    private final Player player;

    /**
     * bool
     */
    private final Boolean connected;

    /**
     * Constructor
     * @param clientController
     * @param player
     * @param connected
     */
    public NotifyUserChangeMessage(ComClientController clientController, Player player, Boolean connected) {
        super(clientController);
        this.player = player;
        this.connected = connected;
    }

    /**
     * process
     */
    @Override
    public void process() {
        clientController.getDataToCom().updateUserSession(player, connected);
    }
}