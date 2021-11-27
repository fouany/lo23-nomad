package nomad.com.server;

import nomad.com.common.message.*;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
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
        UUID userId = server.getAssociatedUserUID(socket);

        if (userId == null && message.getClass() == LocalUserConnectionMessage.class) {
            broadcastNewUser((LocalUserConnectionMessage) message);
            sendLobbyInformation(socket);
            registerUser(socket, (LocalUserConnectionMessage) message);
        }

        Class<? extends ComMessage> messageClass = message.getClass();
        if (messageClass.equals(LocalUserDisconnectionMessage.class)) {
            User u = dataToCom.getUserProfile(server.getAssociatedUserUID(socket));
            dataToCom.updateUserListRemove(server.getAssociatedUserUID(socket));
            server.disconnectClient(socket, u);
        }
    }

    /**
     * Register a new connected client on the server
     *
     * @param socket Send client socket
     * @param message Connection message
     */
    private void registerUser(Socket socket, LocalUserConnectionMessage message) {
        dataToCom.updateUserListAdd(message.user);
        server.registerUser(socket, message.user);
    }

    private void broadcastNewUser(LocalUserConnectionMessage message) {
        server.broadcast(new UserChangedMessage(message.user, true));
    }

    /**
     * Send informations on games and users to newly connected client
     *
     * @param socket Socket of new client
     */
    private void sendLobbyInformation(Socket socket) {
        LobbyInformationMessage informationMessage = new LobbyInformationMessage(
                (ArrayList<GameLight>) dataToCom.requestGameListInLobby(),
                (ArrayList<Player>) dataToCom.requestConnectedUserList()
        );
        server.sendMessage(socket, informationMessage);
    }


}
