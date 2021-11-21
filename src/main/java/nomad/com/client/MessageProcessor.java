package nomad.com.client;

import nomad.com.common.message.ComMessage;
import nomad.com.common.message.LobbyInformationMessage;
import nomad.com.common.message.UserChangedMessage;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComClientInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Process messages sent by the server on client side
 */
public class MessageProcessor {
    private final DataToComClientInterface dataToCom;

    /**
     * Initialize the message processor
     *
     * @param dataToCom Data interface
     */
    public MessageProcessor(DataToComClientInterface dataToCom) {
        this.dataToCom = dataToCom;
    }

    /**
     * Process messages based on their class
     *
     * @param message Received message
     */
    public void processMessage(ComMessage message) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Received message of type : " + message.getClass().toString());
        Class<? extends ComMessage> messageClass = message.getClass();

        if (messageClass.equals(UserChangedMessage.class)) {
            processUserChangedMessage((UserChangedMessage) message);
        } else if (messageClass.equals(LobbyInformationMessage.class)) {
            processLobbyInformationMessage((LobbyInformationMessage) message);
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
    private void processLobbyInformationMessage(LobbyInformationMessage message) {
        dataToCom.addConnectedUserProfile(message.players, message.games);
    }
}
