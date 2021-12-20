package nomad.com.common.message.server_message.game;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.net.Socket;

/**
 * Message New game
 */
public class NewGamePlayerServerMessage extends BaseServerMessage {
    /**
     * player
     */
    private final Player player;
    /**
     * game
     */
    private final GameLight game;

    /**
     * Constructor
     * @param player player
     * @param game game
     */
    public NewGamePlayerServerMessage(Player player, GameLight game) {
        this.player = player;
        this.game = game;
    }

    /**
     * Process
     * @param socket socket
     * @param controller controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        controller.getDataToCom().joinGameRequest(player, game);
    }
}
