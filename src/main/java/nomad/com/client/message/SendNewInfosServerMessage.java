package nomad.com.client.message;

import nomad.com.client.controller.ComClientController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.interfaces.data.DataToComInterface;

import java.util.List;

/**
 * SendNewInfos Message - Client Side
 */
public class SendNewInfosServerMessage extends ComClientMessage {
    /**
     * games
     */
    private final List<GameLight> games;

    /**
     * players
     */
    private final List<Player> players;

    /**
     * Constructor
     *
     * @param clientController
     * @param games
     * @param players
     */
    public SendNewInfosServerMessage(ComClientController clientController, List<GameLight> games, List<Player> players) {
        super(clientController);
        this.games = games;
        this.players = players;
    }

    /**
     * process
     */
    @Override
    public void process() {
        clientController.getDataToCom().addConnectedUserProfile(players, games);
    }
}
