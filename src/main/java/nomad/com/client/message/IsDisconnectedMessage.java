package nomad.com.client.message;

import nomad.com.client.controller.ComClientController;
import nomad.common.interfaces.data.DataToComInterface;

import java.util.UUID;

/**
 * IsDisconnected Message - Client Side
 */
public class IsDisconnectedMessage extends ComClientMessage {
    /**
     * idUser
     */
    private final UUID userId;

    /**
     * isDisconnected
     */
    private final Boolean isDisconnected;

    /**
     * Constructor
     * @param clientController
     * @param userId
     * @param isDisconnected
     */
    public IsDisconnectedMessage(ComClientController clientController, UUID userId, Boolean isDisconnected) {
        super(clientController);
        this.userId = userId;
        this.isDisconnected = isDisconnected;
    }

    /**
     * process
     */
   // @Override
    /*public void process() {
        clientController.getDataToCom().isDisconnected(userId, isDisconnected);
    }*/
}
