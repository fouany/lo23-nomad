package nomad.com.common.message.client_message.information;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.User;

public class GetUserGameMessage extends BaseClientMessage {
    private final User user;

    public GetUserGameMessage(User user) {
        this.user = user;
    }

    @Override
    public void process(ClientController controller) {
        controller.getIhmGameToCom().getUser(user);
    }
}
