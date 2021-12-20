package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;

/**
 * Launch new game
 */
public class GameLaunchedMessage extends BaseClientMessage {
    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().gameLaunchEvent();
    }
}
