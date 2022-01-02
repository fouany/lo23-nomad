package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;

/**
 * Use to notify that a game was saved
 */
public class SendSavedGameMessage extends BaseClientMessage {
    /**
     * Game
     */
    private final Game game;

    /**
     * Constructor
     * @param game Game
     */
    public SendSavedGameMessage(Game game) {
        this.game = game;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().transferSavedGame(game);
    }
}
