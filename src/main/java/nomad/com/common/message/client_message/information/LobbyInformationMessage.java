package nomad.com.common.message.client_message.information;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.util.ArrayList;

/**
 * Message sent by the server to inform new users of current games and logged players or update users of changes
 */
public class LobbyInformationMessage extends BaseClientMessage {
    /**
     * Current players on the server
     */
    private final ArrayList<Player> players;

    /**
     * Current games in lobby on the server
     */
    private final ArrayList<GameLight> gamesInLobby;

    /**
     * Current games in play on the server
     */
    private final ArrayList<GameLight> gamesInPlay;


    /**
     * Build LobbyInformationMessage with list of current games and players
     *
     * @param gamesInLobby The actual games on server
     * @param players      The actual players on server
     */
    public LobbyInformationMessage(ArrayList<Player> players, ArrayList<GameLight> gamesInLobby, ArrayList<GameLight> gamesInPlay) {
        this.gamesInLobby = gamesInLobby;
        this.gamesInPlay = gamesInPlay;
        this.players = players;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().addConnectedUserProfile(players, gamesInLobby, gamesInPlay);
    }
}
