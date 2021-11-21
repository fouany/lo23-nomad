package nomad.com.server.message;

import nomad.com.common.ComMessage;

import java.util.UUID;

/**
 * Disconnection Message - Server Side
 */
public class DisconnectionRequestMessage extends ComMessage {
    private final UUID userId;

    /**
     * Constructor
     *
     * @param serverController
     * @param id
     */
    public DisconnectionRequestMessage(UUID id) {
        this.userId = id;
    }

    /**
     * process
     */
    /*@Override
    public void process() {
        User user = serverController.getDataToCom().updateUserListRemove(userId);


        // Fetch all data required (clientList)
        HashMap<UUID, ComClient> clientList = serverController.server.clientList;
        clientList.remove(userId);

        // ComServerController will send messages on the network
        for (ComClient client : clientList.values()) {
            serverController.SendClientMessage(client.socket, new NotifyUserChangeMessage(client.clientController, user, false));
        }

        UUID userId = this.userId;
        ComClient client = clientList.get(userId);
        serverController.SendClientMessage(client.socket, new IsDisconnectedMessage(client.clientController, userId, true));
    }*/
}
