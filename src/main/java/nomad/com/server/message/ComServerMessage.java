package nomad.com.server.message;

import nomad.com.message.ComMessage;
import nomad.com.server.ComServer;
import nomad.com.server.controller.ComServerController;
import nomad.common.interfaces.data.DataToComInterface;
import nomad.common.interfaces.data.DataToComServeurInterface;

import java.io.Serializable;

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
