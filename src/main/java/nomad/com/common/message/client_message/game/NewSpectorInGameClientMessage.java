package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.UserLight;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewSpectorInGameClientMessage extends BaseClientMessage {
    private final UUID gameId;
    private final UserLight userLight;

    public NewSpectorInGameClientMessage(UUID id, UserLight userLight) {
        this.gameId = id;
        this.userLight = userLight;
    }

    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().newSpectator(gameId, userLight);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error on adding spectator to the game's player");
        }
    }
}
