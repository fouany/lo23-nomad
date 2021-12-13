package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameSerializable;

public class SpecAddedInGameMessage extends BaseClientMessage {
    private final GameSerializable game;
    private final boolean isAdded;

    public SpecAddedInGameMessage(GameSerializable game, boolean isAdded) {
        this.game = game;
        this.isAdded = isAdded;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().addedSpecInGame(game.getGame(), isAdded);
    }
}
