package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameSerializable;

/**
 * Notify new spectator in Game
 */
public class SpecAddedInGameMessage extends BaseClientMessage {
    /**
     * Game
     */
    private final GameSerializable game;
    /**
     * Is added
     */
    private final boolean isAdded;

    /**
     * Contructor
     * @param game Gameserializable
     * @param isAdded boolean
     */
    public SpecAddedInGameMessage(GameSerializable game, boolean isAdded) {
        this.game = game;
        this.isAdded = isAdded;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().addedSpecInGame(game.getGame(), isAdded);
    }
}
