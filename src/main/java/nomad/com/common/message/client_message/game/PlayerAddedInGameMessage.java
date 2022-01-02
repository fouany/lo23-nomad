package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.GameSerializable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use to notify new player in game
 */
public class PlayerAddedInGameMessage extends BaseClientMessage {
    /**
     * Game serializable
     */
    public final GameSerializable gameSerializable;
    /**
     * Is Valid
     */
    private final boolean isValid;

    /**
     * Contructor
     * @param game Game
     * @param isValid boolean
     */
    public PlayerAddedInGameMessage(GameSerializable game, boolean isValid) {
        this.gameSerializable = game;
        this.isValid = isValid;
    }

    /**
     * Process to data
     * @param controller ClientController
     */
    @Override
    public void process(ClientController controller) {
        try {
            Game game = gameSerializable.getGame();
            controller.getDataToCom().addedPlayerInGame(game, isValid);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to add player in game");
        }
    }
}
