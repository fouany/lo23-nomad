package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;

/**
 * Message broadcast by the server to all users to update a change of user
 */
public class UserChangedMessageBase extends BaseClientMessage {
    /**
     * The user concerned by changes
     */
    public final User user;

    /**
     * True for new user, false for logged out user
     */
    public final boolean isConnected;

    /**
     * Constructor
     *
     * @param user        The user (dis)connecting
     * @param isConnected True for a connection, false for a disconnection
     */
    public UserChangedMessageBase(User user, boolean isConnected) {
        this.user = user;
        this.isConnected = isConnected;
    }

    @Override
    public void process(ClientController controller) {
        //Update information on connected or disconnected users
        if (isConnected) { // User connected, transfer information to data
            controller.getDataToCom().updateUserSession(new Player(user.getUserId(), user.getLogin(), user.getProfilePicture()), true);
        } else { // User disconnected
            controller.getDataToCom().isDisconnected(user.getUserId(), true);
        }
    }
}