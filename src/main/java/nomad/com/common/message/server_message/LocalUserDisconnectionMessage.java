package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;

/**
 * Message sent to disconnect local user from the server
 */
public class LocalUserDisconnectionMessage extends BaseServerMessage {
    @Override
    public void process(Socket socket, ServerController controller) {
        User u = controller.getDataToCom().getUserProfile(controller.getAssociatedUserUID(socket));
        controller.getDataToCom().updateUserListRemove(controller.getAssociatedUserUID(socket));
        controller.disconnectClient(socket, u);
    }
}
