package nomad.com.common.message.client_message.information;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.User;

public class GetUserMessage extends BaseClientMessage {
    private final User user;

    public GetUserMessage(User user) {
        this.user = user;
    }

    @Override
    public void process(ClientController controller) {
        controller.getIhmMainToCom().getUser(user);
    }
}
