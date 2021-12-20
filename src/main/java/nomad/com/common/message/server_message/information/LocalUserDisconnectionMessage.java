package nomad.com.common.message.server_message.information;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;

/**
 * Message sent to disconnect local user from the server
 */
public class LocalUserDisconnectionMessage extends BaseServerMessage {
    @Override
    public void process(Socket socket, ServerController controller) {
        controller.disconnectClient(socket);
    }
}
