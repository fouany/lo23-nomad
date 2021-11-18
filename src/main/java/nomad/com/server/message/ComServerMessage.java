package nomad.com.server.message;

import nomad.com.message.ComMessage;
import nomad.com.server.controller.ComServerController;

/**
 * Communication Message abstract class - Server side
 */
public abstract class ComServerMessage extends ComMessage {
    /**
     * dataToComServer Interface
     */
    protected ComServerController serverController;

    public ComServerMessage(ComServerController serverController) {
        this.serverController = serverController;
    }

}
