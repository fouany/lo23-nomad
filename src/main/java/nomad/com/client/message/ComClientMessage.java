package nomad.com.client.message;

import nomad.com.client.controller.ComClientController;
import nomad.com.common.ComMessage;

/**
 * ComClient Message abstract class - Client Side
 */
public abstract class ComClientMessage extends ComMessage {
    /**
     * dataToCom Interface
     */
    protected ComClientController clientController;


    public ComClientMessage(ComClientController myClientController) {
        this.clientController = myClientController;
    }
}
