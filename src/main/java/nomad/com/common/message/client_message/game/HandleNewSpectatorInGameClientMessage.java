package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.UserLight;

public class HandleNewSpectatorInGameClientMessage extends BaseClientMessage {
    private final UserLight userLight;

    public HandleNewSpectatorInGameClientMessage(UserLight userLight) {
        this.userLight = userLight;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().handleSpectator(userLight, true);
    }
}
