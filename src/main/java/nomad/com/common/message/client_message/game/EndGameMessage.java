package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Move;

import java.util.UUID;

public class EndGameMessage extends BaseClientMessage {
    private final UUID gameId;
    private final UUID winner;
    private final Move lastMove;

    public EndGameMessage(UUID gameId, UUID winner, Move lastMove) {
        this.gameId = gameId;
        this.winner = winner;
        this.lastMove = lastMove;
    }


    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().endGame(gameId, winner, lastMove);
    }
}
