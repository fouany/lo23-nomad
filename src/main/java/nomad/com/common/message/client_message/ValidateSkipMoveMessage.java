package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;
import nomad.common.data_structure.Skip;
import nomad.common.data_structure.SkipException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateSkipMoveMessage extends BaseClientMessage {
    private final Skip skip;

    public ValidateSkipMoveMessage(Skip skip) {
        this.skip = skip;
    }

    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().skipValidation(skip, true);
        } catch (SkipException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to validate skip move");

        }
    }
}
