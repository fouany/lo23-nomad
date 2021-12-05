package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;

/**
 * Use to notify that a new game has been created
 */
public class NotifyNewGame extends BaseClientMessage {
    private Game game;

    public NotifyNewGame(Game game){
        this.game = game;
    }

    @Override
    public void process(ClientController controller) {
        /*todo todo*/
        controller.getDataToCom().updateSession(new GameLight(game.getGameId(), game.getHost(), game.getNbOfTowers()));

    }
}
