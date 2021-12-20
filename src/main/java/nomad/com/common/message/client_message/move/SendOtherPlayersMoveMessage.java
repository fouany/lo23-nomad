package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Move;

/**
 * Send the current move to spectator
 */
public class SendOtherPlayersMoveMessage extends BaseClientMessage {
    /**
     * Move
     */
    private final Move move;

    /**
     * Constructor
     * @param move Move
     */
    public SendOtherPlayersMoveMessage(Move move) {
        this.move = move;
    }

    /**
     * Process to data
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().moveReceived(move);
    }
}
