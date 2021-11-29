package nomad.com.common.message.serverMessage;

import nomad.com.common.message.clientMessage.GameCreatedMessage;
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

    public GameCreationMessage(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, boolean hostColor) {
        this.name = name;
        this.host = host;
        this.nbTowers = nbTowers;
        this.areSpecAllowed = areSpecAllowed;
        this.isSpecChatAllowed = isSpecChatAllowed;
        this.hostColor = hostColor;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        Game game = controller.getDataToCom().createGame(name, host, nbTowers, areSpecAllowed, isSpecChatAllowed, hostColor);
        GameCreatedMessage gameCreatedMessage = new GameCreatedMessage(game);
        controller.sendMessage(socket, gameCreatedMessage);
    }
}
