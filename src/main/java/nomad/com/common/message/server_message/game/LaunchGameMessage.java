package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.GameLaunchedMessage;
import nomad.com.common.message.client_message.game.GameStateChangeMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaunchGameMessage extends BaseServerMessage {
    private final Game game;

    public LaunchGameMessage(Game game) {
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().launchGame(game.getGameId());

        // Get all game user ids and send the game launched message
        List<UUID> userIds = new ArrayList<>();
        userIds.add(game.getHost().getId());
        userIds.add(game.getOpponent().getId());
        // TODO : Update spec view
        for (UUID id : userIds) {
            Socket client = controller.getClientSocket(id);
            if (socket != null) {
                controller.sendMessage(client, new GameLaunchedMessage());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Not able to find user socket");
            }
        }

        // Update game list state
        for (Socket client : controller.getClientList().keySet()) {
            controller.sendMessage(client, new GameStateChangeMessage(game.getGameId(), true));
        }
    }
}
