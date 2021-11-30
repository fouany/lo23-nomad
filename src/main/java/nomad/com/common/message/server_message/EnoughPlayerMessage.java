package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.UserLight;

import java.net.Socket;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnoughPlayerMessage extends BaseServerMessage {
    private final UUID gameId;
    private final UUID opponentId;

    public EnoughPlayerMessage(UUID gameId, UUID opponentId) {
        this.gameId = gameId;
        this.opponentId = opponentId;
    }


    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().guestAccepted(gameId, opponentId);
        List<UserLight> spectators = game.getSpect();
        for (UserLight spec : spectators) {
            Socket client = controller.getClientSocket(spec.getId());
            if (client == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to get client socket");
            }
            // TODO : Que faire après avec les spectateurs ? Le DS n'est pas adapté
        }
    }
}
