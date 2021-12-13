package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.SendSavedGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;

import java.net.Socket;
import java.util.UUID;

public class GetSavedGameMessage extends BaseServerMessage {
    private final UUID gameId;

    public GetSavedGameMessage(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().getStoredGame(gameId);
        controller.sendMessage(socket, new SendSavedGameMessage(game));
    }
}
