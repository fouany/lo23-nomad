package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.GameCreatedMessage;
import nomad.com.common.message.client_message.game.NotifyNewGameMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.UserLight;

import java.net.Socket;

/**
 * GameCreationMessage is the message to pass when a game is being created
 */
public class GameCreationMessage extends BaseServerMessage {
    /**
     * The name of the game
     */
    private final String name;

    /**
     * The host of the game
     */
    private final UserLight host;

    /**
     * The number of towers in the game
     */
    private final int nbTowers;

    /**
     * True if spectators are allowed to watch the game
     */
    private final boolean areSpecAllowed;

    /**
     * True if spectators are allow to chat while watching the game
     */
    private final boolean isSpecChatAllowed;

    /**
     * Determine the color of the host
     */
    private final boolean hostColor;

    /**
     * Constructor
     * @param name name of the game
     * @param host host of the game
     * @param nbTowers number of towers in the game
     * @param areSpecAllowed boolean that allow spectator to join the game
     * @param isSpecChatAllowed boolean that allow chat
     * @param hostColor boolean
     */
    public GameCreationMessage(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, boolean hostColor) {
        this.name = name;
        this.host = host;
        this.nbTowers = nbTowers;
        this.areSpecAllowed = areSpecAllowed;
        this.isSpecChatAllowed = isSpecChatAllowed;
        this.hostColor = hostColor;
    }

    /**
     * Process method
     * @param socket socket
     * @param controller controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().createGame(name, host, nbTowers, areSpecAllowed, isSpecChatAllowed, hostColor);
        GameCreatedMessage gameCreatedMessage = new GameCreatedMessage(game.getGameSerializable());
        controller.sendMessage(socket, gameCreatedMessage);
        NotifyNewGameMessage notifyNewGameMessage = new NotifyNewGameMessage(game.getGameSerializable());
        controller.broadcast(notifyNewGameMessage);
    }
}
