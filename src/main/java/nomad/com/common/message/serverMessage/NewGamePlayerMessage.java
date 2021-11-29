package nomad.com.common.message.serverMessage;

import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.net.Socket;

public class NewGamePlayerMessage extends BaseServerMessage{
    private final Player player;
    private final GameLight game;

    public NewGamePlayerMessage(Player player, GameLight game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().joinGameRequest(player, game);
    }
}
