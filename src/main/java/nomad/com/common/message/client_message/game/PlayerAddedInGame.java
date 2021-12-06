package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerAddedInGame extends BaseClientMessage {
    private final Game game;
    private boolean isValid;

    public PlayerAddedInGame(Game game, boolean isValid) {
        this.game = game;
        this.isValid = isValid;
    }

    @Override
    public void process(ClientController controller) {
        try {

            controller.getDataToCom().addedPlayerInGame(game, isValid);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to add player in game");
        }
    }
}
