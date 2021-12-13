package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;

import java.util.UUID;

public class RemoveGameMessage extends BaseClientMessage {
    private final UUID idGame;

    public RemoveGameMessage(UUID idGame) {
        this.idGame = idGame;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().removeFinishedGame(idGame);
    }
}
