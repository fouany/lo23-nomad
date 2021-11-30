package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;

import java.util.UUID;

public class GameStateChangeMessage extends BaseClientMessage {
    private final UUID gameId;
    private final boolean isGameLaunched;

    public GameStateChangeMessage(UUID gameId, boolean isLaunched) {
        this.gameId = gameId;
        this.isGameLaunched = isLaunched;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().updateSessionGameState(gameId, isGameLaunched);
    }
}
