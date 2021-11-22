package nomad.com.common.message;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.util.ArrayList;

/**
 * Message sent by the server to inform new users of current games and logged players or update users of changes
 */
public class LobbyInformationMessage implements ComMessage {
    /**
     * Current games on the server
     */
    public final ArrayList<GameLight> games;

    /**
     * Current players on the server
     */
    public final ArrayList<Player> players;

    /**
     * Build LobbyInformationMessage with list of current games and players
     *
     * @param games   The actual games on server
     * @param players The actual players on server
     */
    public LobbyInformationMessage(ArrayList<GameLight> games, ArrayList<Player> players) {
        this.games = games;
        this.players = players;
    }
}
