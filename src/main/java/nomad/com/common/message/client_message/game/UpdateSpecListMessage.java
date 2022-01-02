package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.UserLight;

/**
 * Add spectator in Game
 */
public class UpdateSpecListMessage extends BaseClientMessage {
    /**
     * User
     */
    private final UserLight user;
    /**
     * Is added
     */
    private final boolean isAdded;

    /**
     * Constructor
     * @param user UserLight
     * @param isAdded boolean
     */
    public UpdateSpecListMessage(UserLight user, boolean isAdded) {
        this.user = user;
        this.isAdded = isAdded;
    }

    /**
     * Process to data
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().handleSpectator(user, isAdded);
    }
}
