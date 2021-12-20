package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.PlayerAddedInGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameSerializable;
import nomad.common.data_structure.Player;

import java.net.Socket;
import java.util.UUID;

/**
 * Reject player in lobby
 */
public class RejectPlayerMessage extends BaseServerMessage {
    /**
     * Game Id
     */
    private final UUID gameId;

    /**
     * Constructor
     * @param gameId UUId
     */
    public RejectPlayerMessage(UUID gameId) {
        this.gameId = gameId;
    }

    /**
     * Process to data
     * @param socket
     * @param controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().getStoredGame(gameId);
        Player opponent = game.getOpponent();
        controller.getDataToCom().guestRefused(opponent);
        Socket opponentSocket = controller.getClientSocket(opponent.getId());
        controller.sendMessage(opponentSocket, new PlayerAddedInGameMessage(new GameSerializable(game), false));
    }
}
