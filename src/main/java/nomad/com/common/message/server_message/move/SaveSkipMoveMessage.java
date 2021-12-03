package nomad.com.common.message.server_message.move;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Skip;

import java.net.Socket;

public class SaveSkipMoveMessage extends BaseServerMessage {
    Skip skip;

    public SaveSkipMoveMessage(Skip skip) {
        this.skip = skip;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().saveSkip(skip);
    }
}
