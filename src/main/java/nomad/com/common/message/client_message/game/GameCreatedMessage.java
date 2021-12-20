package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameSerializable;

/**
 * Message to sent to the client when a game is created
 */
public class GameCreatedMessage extends BaseClientMessage {
    /**
     * The created game
     */
    private final GameSerializable game;

    /**
     * Constructor
     * @param game Game
     */
    public GameCreatedMessage(GameSerializable game) {
        this.game = game;
    }


    /**
     * Process to Data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        Game g = this.game.getGame();
        controller.getDataToCom().gameCreated(g);

    }
}
