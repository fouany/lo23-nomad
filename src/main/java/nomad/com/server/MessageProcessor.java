package nomad.com.server;

import nomad.com.common.message.*;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MessageProcessor is responsible of processing all messages received by the server
 */
public class MessageProcessor {
    private final DataToComServerInterface dataToCom;
    private final Server server;

    /**
     *
     * @param dataToCom Interface between Com and Data
     * @param server Server object reference
     */
    public MessageProcessor(DataToComServerInterface dataToCom, Server server) {
        this.dataToCom = dataToCom;
        this.server = server;
    }

    /**
     * Process a message received from a client
     *
     * @param socket Send client socket
     * @param message Sent message
     */
    public void processMessage(Socket socket, ComMessage message) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, message.getClass().toString());
        UUID userId = server.getAssociatedUser(socket);

        if (userId == null && message.getClass() == LocalUserConnectionMessage.class) {
            registerUser(socket, (LocalUserConnectionMessage) message);
            sendLobbyInformation(socket);
        }

        Class<? extends ComMessage> messageClass = message.getClass();
        if (messageClass.equals(LocalUserDisconnectionMessage.class)) {

        }
    }

    /**
     * Register a new connected client on the server
     *
     * @param socket Send client socket
     * @param message Connection message
     */
    private void registerUser(Socket socket, LocalUserConnectionMessage message) {
        server.broadcast(new UserChangedMessage(message.user, true));
        dataToCom.updateUserListAdd(message.user);
        server.registerUser(socket, message.user);
    }

    /**
     * Send informations on games and users to newly connected client
     *
     * @param socket Socket of new client
     */
    private void sendLobbyInformation(Socket socket) {
        LobbyInformationMessage informationMessage = new LobbyInformationMessage(
                (ArrayList<GameLight>) dataToCom.requestGameList(),
                (ArrayList<Player>) dataToCom.requestConnectedUserList()
        );
        server.sendMessage(socket, informationMessage);
    }


}
