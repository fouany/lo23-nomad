package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.GameSerializable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerAddedInGameMessage extends BaseClientMessage {
    public final GameSerializable gameSerializable;
    private final boolean isValid;

    public PlayerAddedInGameMessage(GameSerializable game, boolean isValid) {
        this.gameSerializable = game;
        this.isValid = isValid;
    }

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
