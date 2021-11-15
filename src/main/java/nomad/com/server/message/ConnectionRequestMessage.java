package nomad.com.server.message;

import nomad.com.client.ComClient;
import nomad.com.client.message.NotifyUserChangeMessage;
import nomad.com.client.message.SendNewInfosServerMessage;
import nomad.com.server.ComServerListener;
import nomad.com.server.controller.ComServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComServeurInterface;

import java.util.List;

/**
 * Connexion Message - Server Side
 */
public class ConnectionRequestMessage extends ComServerMessage {
    /**
     * user
     */
    private final User user;

    private ComServerListener listener;

    public void setListener(ComServerListener listener) {
        this.listener = listener;
    }

    /**
     * Constructor
     *
     * @param serverController
     * @param user
     */
    public ConnectionRequestMessage(ComServerController serverController, User user) {
        super(serverController);
        this.user = user;
    }

    /**
     * User getter
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * process
     */
    @Override
    public void process() {
        ComClient client = ComServerController.server.clientList.get(getUser().getUserId());
        ComServerController.requestConnection(client, listener);

        // Creating a new player from the current user
        Player player = new Player(getUser().getUserId(), getUser().getLogin(), getUser().getProfilePicture());

        // Fetch all data required (player and games)
        List<Player> players = serverController.getDataToCom().requestConnectedUserList();
        List<GameLight> games = serverController.getDataToCom().requestGamelist();

        // ComServerController will send messages on the network
        ComServerController.SendClientMessage(client.socket, new SendNewInfosServerMessage(client.clientController, games, players));

        for (ComClient cli : ComServerController.server.clientList.values()) {
            ComServerController.SendClientMessage(cli.socket, new NotifyUserChangeMessage(cli.clientController, player, true));
        }
    }
}
