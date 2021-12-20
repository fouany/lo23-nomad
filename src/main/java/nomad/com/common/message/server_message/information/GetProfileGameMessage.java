package nomad.com.common.message.server_message.information;

import nomad.com.common.message.client_message.information.GetUserGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;
import java.util.UUID;

/**
 * GetProfileGameMessage contains a userId to send a GetUserGameMessage to this user
 */
public class GetProfileGameMessage extends BaseServerMessage {
    /**
     * Instance of UUID of the user
     */
    private final UUID userId;

    /**
     * Constructor of GetProfileGameMessage class
     * @param userId Id of the user we want to send the message to.
     */
    public GetProfileGameMessage(UUID userId) {
        this.userId = userId;
    }

    /**
     * Get the User from the UserId then send a GetUserGameMessage to this user
     * @param socket Socket of the client app
     * @param controller Instance of the server controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        User user = controller.getDataToCom().getUserProfile(userId);
        controller.sendMessage(socket, new GetUserGameMessage(user));
    }
}
