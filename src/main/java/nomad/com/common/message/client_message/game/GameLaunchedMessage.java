package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;

public class GameLaunchedMessage extends BaseClientMessage {

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().gameLaunchEvent();
    }
}
