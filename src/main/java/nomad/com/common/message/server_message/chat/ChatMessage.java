package nomad.com.common.message.server_message.chat;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.common.message.client_message.chat.ChatSpreadMessage;
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
public class ChatMessage extends BaseServerMessage {

    /**
     * message
     */
    private final Message message;

    private final Game game;


    /**
     * Constructor
     * @param message
     * @param game
     */
    public ChatMessage(Message message, Game game) {
        this.message = message;
        this.game = game;
    }

    /**
     * Process method
     * @param controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        try {
            // Store message on com server
            //controller.getDataToCom().storeNewMessage(message);
        } catch (Error e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error on storing a message");
        }

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
                controller.sendMessage(client, new ChatSpreadMessage(message, game));
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Not able to find user socket");
            }
        }
    }
}
