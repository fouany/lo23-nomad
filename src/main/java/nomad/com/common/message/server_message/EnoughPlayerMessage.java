package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;

import java.net.Socket;

public class EnoughPlayerMessage extends BaseServerMessage {
    private final GameLight game;

    public EnoughPlayerMessage(GameLight game) {
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().guestAccepted(game);
    }
}
