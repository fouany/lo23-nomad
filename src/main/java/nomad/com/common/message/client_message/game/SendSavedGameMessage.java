package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;

public class SendSavedGameMessage extends BaseClientMessage {
    private final Game game;

    public SendSavedGameMessage(Game game) {
        this.game = game;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().transferSavedGame(game);
    }
}
