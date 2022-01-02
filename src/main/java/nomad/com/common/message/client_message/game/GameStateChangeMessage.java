package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;

import java.util.UUID;

/**
 * Change on game state
 */
public class GameStateChangeMessage extends BaseClientMessage {
    /**
     * Game Id
     */
    private final UUID gameId;
    /**
     * is Game Launched
     */
    private final boolean isGameLaunched;

    /**
     * Contructor
     * @param gameId UUID
     * @param isLaunched Boolean
     */
    public GameStateChangeMessage(UUID gameId, boolean isLaunched) {
        this.gameId = gameId;
        this.isGameLaunched = isLaunched;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().updateSessionGameState(gameId, isGameLaunched);
    }
}
