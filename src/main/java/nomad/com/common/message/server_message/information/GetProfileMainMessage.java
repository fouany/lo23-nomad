package nomad.com.common.message.server_message.information;

import nomad.com.common.message.client_message.information.GetUserMainMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.User;

import java.net.Socket;
import java.util.UUID;

public class GetProfileMainMessage extends BaseServerMessage {
    private final UUID userId;

    public GetProfileMainMessage(UUID userId) {
        this.userId = userId;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        User user = controller.getDataToCom().getUserProfile(userId);
        controller.sendMessage(socket, new GetUserMainMessage(user));
    }
}
