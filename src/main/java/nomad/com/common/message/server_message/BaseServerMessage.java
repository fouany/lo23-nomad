package nomad.com.common.message.server_message;

import nomad.com.common.message.Message;
import nomad.com.server.ServerController;

import java.net.Socket;

/**
 * Communication Message abstract class for server side
 */
public abstract class BaseServerMessage extends Message {
    public void process(Socket socket, ServerController controller) {
    }
}
