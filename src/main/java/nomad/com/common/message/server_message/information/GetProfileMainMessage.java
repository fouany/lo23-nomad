package nomad.com.common.message.server_message.information;

import nomad.com.common.message.client_message.information.GetUserMainMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;
import java.util.UUID;

/**
 * GetProfileMainMessage contains a userId to send a GetUserMainMessage to this user
 */
public class GetProfileMainMessage extends BaseServerMessage {
    /**
     * Instance of UUID of the user
     */
    private final UUID userId;

    /**
     * Constructor of GetProfileMainMessage class
     * @param userId Id of the user we want to send the message to.
     */
    public GetProfileMainMessage(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get the User from the UserId then send a GetUserMainMessage to this user
     * @param socket Socket of the client app
     * @param controller Instance of the server controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        User user = controller.getDataToCom().getUserProfile(userId);
        controller.sendMessage(socket, new GetUserMainMessage(user));
    }
}
