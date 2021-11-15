package nomad.com.server.message;

import nomad.com.client.ComClient;
import nomad.com.client.message.IsDisconnectedMessage;
import nomad.com.client.message.NotifyUserChangeMessage;
import nomad.com.server.controller.ComServerController;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComServeurInterface;

import java.util.HashMap;
import java.util.UUID;

/**
 * Disconnection Message - Server Side
 */
public class DisconnectionRequestMessage extends ComServerMessage {
    /**
     * user
     * @param serverController
     * @param dataToCom
     */
    private final User user;

    /**
     * Constructor
     * @param serverController
     * @param user
     */
    public DisconnectionRequestMessage(ComServerController serverController, User user) {
        super(serverController);
        this.user = user;
    }

    /**
     * process
     */
    @Override
    public void process() {
        Player player = serverController.getDataToCom().updateUserListRemove(user);


        // Fetch all data required (clientList)
        HashMap<UUID, ComClient> clientList = ComServerController.server.clientList;
        clientList.remove(user.getUserId());

        // ComServerController will send messages on the network
        for (ComClient client : clientList.values()) {
            ComServerController.SendClientMessage(client.socket, new NotifyUserChangeMessage(client.clientController, player, false));
        }

        UUID userId = user.getUserId();
        ComClient client = clientList.get(userId);
        ComServerController.SendClientMessage(client.socket, new IsDisconnectedMessage(client.clientController, userId, true));
    }
}
