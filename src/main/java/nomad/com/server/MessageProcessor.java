package nomad.com.server;

import nomad.com.common.message.ComMessage;
import nomad.com.common.message.LobbyInformationMessage;
import nomad.com.common.message.LocalUserConnectionMessage;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class MessageProcessor {
    private final DataToComServerInterface dataToCom;
    private final Server server;

    public MessageProcessor(DataToComServerInterface dataToCom, Server server) {
        this.dataToCom = dataToCom;
        this.server = server;
    }

    public void processMessage(Socket socket, ComMessage message) {
        UUID userId = server.getAssociatedUser(socket);

        if (userId == null && message.getClass() == LocalUserConnectionMessage.class) {
            registerUser(socket, (LocalUserConnectionMessage) message);
            sendLobbyInformation();
        }

        Class<? extends ComMessage> messageClass = message.getClass();
    }

    private void registerUser(Socket socket, LocalUserConnectionMessage message) {
        server.registerUser(socket, message.user);
        dataToCom.updateUserListAdd(message.user);
    }

    private void sendLobbyInformation() {
        LobbyInformationMessage informationMessage = new LobbyInformationMessage(
                (ArrayList<GameLight>) dataToCom.requestGameList(),
                (ArrayList<Player>) dataToCom.requestConnectedUserList()
        );

        server.broadcast(informationMessage);

    }
}
