package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.SpecAddedInGameMessage;
import nomad.com.common.message.client_message.game.UpdateSpecListMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.UserLight;

import java.net.Socket;
import java.util.List;
import java.util.UUID;

public class AddSpecInGameMessage extends BaseServerMessage {
    private final UserLight user;
    private final GameLight gameLight;

    public AddSpecInGameMessage(UserLight user, GameLight gameLight) {
        this.user = user;
        this.gameLight = gameLight;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().getStoredGame(gameLight.getGameId());
        controller.getDataToCom().addSpecInGame(user, gameLight);
        List<UUID> userIds = controller.getDataToCom().getUserList(gameLight);
        controller.sendMessage(socket, new SpecAddedInGameMessage(game.getGameSerializable(), true));
        for (UUID id : userIds) {
            Socket client = controller.getClientSocket(id);
            controller.sendMessage(client, new UpdateSpecListMessage(user, true));
        }
    }
}
