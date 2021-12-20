package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Skip;
import nomad.common.data_structure.SkipException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use to validate a skip move
 */
public class ValidateSkipMoveMessage extends BaseClientMessage {
    /**
     * Skip
     */
    private final Skip skip;

    /**
     * Constructor
     * @param skip Skip
     */
    public ValidateSkipMoveMessage(Skip skip) {
        this.skip = skip;
    }

    /**
     * Process to Data
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().skipValidation(skip, true);
        } catch (SkipException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to validate skip move");

        }
    }
}
