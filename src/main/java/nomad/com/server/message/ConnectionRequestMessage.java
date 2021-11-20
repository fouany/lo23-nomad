package nomad.com.server.message;

import nomad.com.message.ComMessage;
import nomad.com.server.ComServerListener;
import nomad.common.data_structure.User;

/**
 * Connexion Message - Server Side
 */
public class ConnectionRequestMessage extends ComMessage {
    /**
     * user
     */
    private final User user;
    private ComServerListener listener;

    public ConnectionRequestMessage(User user) {
        this.user = user;
    }

    public void setListener(ComServerListener listener) {
        this.listener = listener;
    }

    /**
     * process
     */
   /* @Override
    public void process() {
        ComClient client = serverController.server.clientList.get(user.getUserId());
        serverController.requestConnection(client, listener);

        // Creating a new player from the current user
        Player player = new Player(user.getUserId(), user.getLogin(), user.getProfilePicture());

        // Fetch all data required (player and games)
        List<Player> players = serverController.getDataToCom().requestConnectedUserList();
        List<GameLight> games = serverController.getDataToCom().requestGameList();

        // ComServerController will send messages on the network
        serverController.SendClientMessage(client.socket, new SendNewInfosServerMessage(client.clientController, games, players));

        for (ComClient cli : serverController.server.clientList.values()) {
            serverController.SendClientMessage(cli.socket, new NotifyUserChangeMessage(cli.clientController, user, true));
        }
    }*/
}
