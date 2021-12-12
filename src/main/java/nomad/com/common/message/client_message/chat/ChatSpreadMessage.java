package nomad.com.common.message.client_message.chat;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;

/**
 * Spread Chat Message to other connected people
 */
public class ChatSpreadMessage extends BaseClientMessage {

    /**
     * message
     */
    private final Message message;

    /**
     * game
     */
    private final Game game;

    /**
     * Constructor
     * @param message
     * @param game
     */
    public ChatSpreadMessage(Message message, Game game) {
        this.message = message;
        this.game = game;
    }

    /**
     * Process method
     * @param controller
     */
    @Override
    public void process(ClientController controller) {
        // Store message on com client
        controller.getDataToCom().storeNewMessage(message);
    }
}
