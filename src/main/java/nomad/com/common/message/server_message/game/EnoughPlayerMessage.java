package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.PlayerAddedInGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;

import java.net.Socket;
import java.util.UUID;

/**
 * Message to check if there is enough player in the game
 */
public class EnoughPlayerMessage extends BaseServerMessage {
    /**
     * game id
     */
    private final UUID gameId;
    /**
     * oppenent id
     */
    private final UUID opponentId;

    /**
     * Constructor
     * @param gameId game id
     * @param opponentId oppenent id
     */
    public EnoughPlayerMessage(UUID gameId, UUID opponentId) {
        this.gameId = gameId;
        this.opponentId = opponentId;
    }

    /**
     * Process method
     * @param socket socket
     * @param controller controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        Socket opponentSocket = controller.getClientSocket(opponentId);
        Game game = controller.getDataToCom().guestAccepted(gameId, opponentId);
        PlayerAddedInGameMessage playerAddedInGameMessage = new PlayerAddedInGameMessage(game.getGameSerializable(), true);
        controller.sendMessage(opponentSocket, playerAddedInGameMessage);

    }
}
