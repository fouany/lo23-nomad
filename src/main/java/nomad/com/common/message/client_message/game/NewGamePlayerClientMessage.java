package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.Player;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGamePlayerClientMessage extends BaseClientMessage {
    private final UUID gameId;
    private final Player player;

    public NewGamePlayerClientMessage(UUID id, Player player) {
        this.gameId = id;
        this.player = player;
    }

    @Override
    public void process(ClientController controller) {
        try {

            controller.getDataToCom().newPlayer(gameId, player);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error on adding user to the game");
        }
    }
}
