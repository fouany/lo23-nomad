package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Move;

import java.util.UUID;


/**
 * End game Message class
 */
public class EndGameMessage extends BaseClientMessage {
    /**
     * Game Id
     */
    private final UUID gameId;
    /**
     * Winner
     */
    private final UUID winner;
    /**
     * Last move
     */
    private final Move lastMove;

    /**
     * Constructor
     * @param gameId UUID
     * @param winner UUID
     * @param lastMove Move
     */
    public EndGameMessage(UUID gameId, UUID winner, Move lastMove) {
        this.gameId = gameId;
        this.winner = winner;
        this.lastMove = lastMove;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().endGame(gameId, winner, lastMove);
    }
}
