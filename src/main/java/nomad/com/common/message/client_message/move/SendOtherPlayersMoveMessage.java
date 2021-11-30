package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Move;

public class SendOtherPlayersMoveMessage extends BaseClientMessage {
    private final Move move;

    public SendOtherPlayersMoveMessage(Move move) {
        this.move = move;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().moveReceived(move);
    }
}
