package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;

/**
 * Message to sent to the client when a game is created
 */
public class GameCreatedMessage extends BaseClientMessage {
    /**
     * The created game
     */
    private final Game game;

    public GameCreatedMessage(Game game) {
        this.game = game;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().gameCreated(game);
        controller.getDataToCom().updateSession(new GameLight(game.getGameId(), game.getHost(), game.getNbOfTowers()));
    }
}
