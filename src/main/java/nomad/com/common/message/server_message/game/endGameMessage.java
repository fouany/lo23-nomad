package nomad.com.common.message.server_message.game;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;

import java.net.Socket;

public class endGameMessage extends BaseServerMessage {
    private final boolean gameEnded;
    private final GameLight gamelight;
    public endGameMessage(boolean gameEnded, GameLight gamelight) {
        this.gameEnded = gameEnded;
        this.gamelight = gamelight;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        //TODO
    }
}
