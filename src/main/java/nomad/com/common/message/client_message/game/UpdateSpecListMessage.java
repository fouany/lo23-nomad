package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.UserLight;

public class UpdateSpecListMessage extends BaseClientMessage {
    private final UserLight user;
    private final boolean isAdded;

    public UpdateSpecListMessage(UserLight user, boolean isAdded) {
        this.user = user;
        this.isAdded = isAdded;
    }


    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().handleSpectator(user, isAdded);
    }
}
