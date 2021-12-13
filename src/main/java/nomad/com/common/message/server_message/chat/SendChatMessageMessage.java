package nomad.com.common.message.server_message.chat;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.common.message.client_message.chat.ChatIncomingMessageMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.UserLight;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Chat Message sent by user
 */
public class SendChatMessageMessage extends BaseServerMessage {

    /**
     * message
     */
    private final Message message;

    /**
     * Constructor
     * @param message is the the message sent by the user
     */
    public SendChatMessageMessage(Message message) {
        this.message = message;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().storeMessage(message);
        Game game = controller.getDataToCom().getStoredGame(message.getGameId());

        // Get all game user ids and send the game launched message
        List<UUID> userIds = new ArrayList<>();
        userIds.add(game.getHost().getId());
        userIds.add(game.getOpponent().getId());
        for (UserLight user : game.getSpect()) {
            userIds.add(user.getId());
        }
        for (UUID id : userIds) {
            Socket client = controller.getClientSocket(id);
            if (client != null) {
                controller.sendMessage(client, new ChatIncomingMessageMessage(message));
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Not able to find user socket");
            }
        }
    }
}
