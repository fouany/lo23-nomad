package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.PlayerAddedInGame;
import nomad.com.common.message.server_message.BaseServerMessage;
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
        Socket opponentSocket = controller.getClientSocket(opponentId);
        Game game = controller.getDataToCom().guestAccepted(gameId, opponentId);
        controller.sendMessage(opponentSocket, new PlayerAddedInGame(game, true));

        /*List<UserLight> spectators = game.getSpect();
        for (UserLight spec : spectators) {
            Socket client = controller.getClientSocket(spec.getId());
            if (client == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to get client socket");
            }
            // TODO : Que faire après avec les spectateurs ? Le DS n'est pas adapté
        }*/
    }
}
