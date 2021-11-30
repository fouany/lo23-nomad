package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;

public class GameLaunchedMessage extends BaseClientMessage {

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().gameLaunchEvent();
    }
}
