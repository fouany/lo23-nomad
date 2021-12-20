package nomad.com.common.message.server_message.information;

import nomad.com.common.message.client_message.information.GetUserGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;
import java.util.UUID;

public class GetProfileGameMessage extends BaseServerMessage {
    private final UUID userId;

    public GetProfileGameMessage(UUID userId) {
        this.userId = userId;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        User user = controller.getDataToCom().getUserProfile(userId);
        controller.sendMessage(socket, new GetUserGameMessage(user));
    }
}
