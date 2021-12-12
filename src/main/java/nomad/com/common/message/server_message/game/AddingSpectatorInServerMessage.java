package nomad.com.common.message.server_message.game;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.UserLight;

import java.net.Socket;

public class AddingSpectatorInServerMessage extends BaseServerMessage {
    private final UserLight userLight;
    private final GameLight game;

    public AddingSpectatorInServerMessage(UserLight userLight, GameLight game) {
        this.userLight = userLight;
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().addSpecInGame(userLight, game);
    }
}
