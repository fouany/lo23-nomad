package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.PlayerAddedInGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;

import java.net.Socket;
import java.util.UUID;

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
        PlayerAddedInGameMessage playerAddedInGameMessage = new PlayerAddedInGameMessage(game, true,game.getOpponent());
        controller.sendMessage(opponentSocket, playerAddedInGameMessage);
        // TODO : Update game for spec
    }
}
