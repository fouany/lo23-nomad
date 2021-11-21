package nomad.com.client;

import nomad.com.common.ComMessage;
import nomad.com.common.LobbyInformationsMessage;
import nomad.com.common.UserChangedMessage;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComInterface;

/**
 * Process messages sent by the server on client side
 */
public class MessageProcessor {
    private final DataToComInterface dataToCom;

    /**
     * Initialize the message processor
     *
     * @param dataToCom Data interface
     */
    public MessageProcessor(DataToComInterface dataToCom) {
        this.dataToCom = dataToCom;
    }

    /**
     * Process messages based on their class
     *
     * @param message Received message
     */
    public void processMessage(ComMessage message) {
        Class<? extends ComMessage> messageClass = message.getClass();

        if (messageClass.equals(UserChangedMessage.class)) {
            processUserChangedMessage((UserChangedMessage) message);
        } else if (messageClass.equals(LobbyInformationsMessage.class)) {
            processLobbyInformationsMessage((LobbyInformationsMessage) message);
        }
    }

    /**
     * Update information on connected or disconnected users
     *
     * @param message Message received from server
     */
    private void processUserChangedMessage(UserChangedMessage message) {
        if (message.isConnected) { // User connected, transfer information to data
            User user = message.user;
            dataToCom.updateUserSession(new Player(user.getUserId(), user.getLogin(), user.getProfilePicture()), true);
        } else { // User disconnected
            dataToCom.isDisconnected(message.user.getUserId(), true);
        }
    }

    /**
     * Update information on the connected users and current games
     *
     * @param message Message received from server
     */
    private void processLobbyInformationsMessage(LobbyInformationsMessage message) {
        dataToCom.addConnectedUserProfile(message.players, message.games);
    }
}
