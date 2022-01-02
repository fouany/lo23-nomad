package nomad.com.common.message.client_message.chat;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Message;

/**
 * Spread Chat Message to other connected people
 */
public class ChatIncomingMessageMessage extends BaseClientMessage {

    /**
     * message
     */
    private final Message message;

    /**
     * Constructor
     *
     * @param message is the message sent by the message sender
     */
    public ChatIncomingMessageMessage(Message message) {
        this.message = message;
    }

    /**
     * Process method
     * @param controller client controller
     */
    @Override
    public void process(ClientController controller) {
        // Store message on com client
        controller.getDataToCom().storeNewMessage(message);
    }
}
