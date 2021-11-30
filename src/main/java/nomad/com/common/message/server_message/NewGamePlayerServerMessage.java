package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.net.Socket;

public class NewGamePlayerServerMessage extends BaseServerMessage{
    private final Player player;
    private final GameLight game;

    public NewGamePlayerServerMessage(Player player, GameLight game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().joinGameRequest(player, game);
    }
}
