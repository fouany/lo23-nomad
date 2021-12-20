package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.GameSerializable;

/**
 * Use to notify that a new game has been created
 */
public class NotifyNewGameMessage extends BaseClientMessage {
    /**
     * Game
     */
    private final GameSerializable game;

    /**
     * Contructor
     * @param game Game
     */
    public NotifyNewGameMessage(GameSerializable game){
        this.game = game;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().updateSession(new GameLight(game.getGameId(), game.getHost(), game.getNbOfTowers()));
    }
}
