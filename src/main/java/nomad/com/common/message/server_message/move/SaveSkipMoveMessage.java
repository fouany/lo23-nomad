package nomad.com.common.message.server_message.move;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Skip;

import java.net.Socket;

/**
 * SaveSkipMoveMessage contains a skip to call the saveSkip method
 */
public class SaveSkipMoveMessage extends BaseServerMessage {
    /**
     * Instance of skip
     */
    Skip skip;

    /**
     * Constructor of SaveSkipMoveMessage class
     * @param skip the skip sent by the client app
     */
    public SaveSkipMoveMessage(Skip skip) {
        this.skip = skip;
    }

    /**
     * When this message is received, call the saveSkip method
     * @param socket the client's socket
     * @param controller an instance of the server controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().saveSkip(skip);
    }
}
