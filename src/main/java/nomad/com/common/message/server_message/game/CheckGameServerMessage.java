package nomad.com.common.message.server_message.game;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;

import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class CheckGameServerMessage extends BaseServerMessage {
    private final GameLight gamelight;

    public CheckGameServerMessage(GameLight gamelight) {
        this.gamelight = gamelight;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        boolean b = controller.getDataToCom().checkGameEnded(this.gamelight);
        if (b) {
            endGameMessage endgamemessage = new endGameMessage(b, this.gamelight);
            List<UUID> list= controller.getDataToCom().getUserList(this.gamelight);
            for (UUID id : list){
                controller.sendMessage(controller.getClientSocket(id),endgamemessage);
            }
        }
    }
}
