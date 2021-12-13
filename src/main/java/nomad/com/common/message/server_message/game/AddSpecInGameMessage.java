package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.SpecAddedInGameMessage;
import nomad.com.common.message.client_message.game.UpdateSpecListMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.UserLight;

import java.net.Socket;
import java.util.List;
import java.util.UUID;

public class AddSpecInGameMessage extends BaseServerMessage {
    private final UserLight user;
    private final GameLight game;

    public AddSpecInGameMessage(UserLight user, GameLight game) {
        this.user = user;
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().addSpecInGame(user, game);
        List<UUID> userIds = controller.getDataToCom().getUserList(game);
        controller.sendMessage(socket, new SpecAddedInGameMessage(game, true));
        for (UUID id : userIds) {
            Socket client = controller.getClientSocket(id);
            controller.sendMessage(client, new UpdateSpecListMessage(user, true));
        }
    }
}
