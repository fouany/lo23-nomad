package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;

import java.util.UUID;

/**
 * Use to notify that a game was remove
 */
public class RemoveGameMessage extends BaseClientMessage {
    /**
     * Game id
     */
    private final UUID idGame;

    /**
     * Constructor
     * @param idGame Game id
     */
    public RemoveGameMessage(UUID idGame) {
        this.idGame = idGame;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().removeFinishedGame(idGame);
    }
}
