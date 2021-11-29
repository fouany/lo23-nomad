package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGameUserMessage extends BaseClientMessage {
    private final GameLight game;
    private final Player player;
    private final boolean isPlayer;

    public NewGameUserMessage(GameLight game, Player player, boolean isPlayer) {
        this.game = game;
        this.player = player;
        this.isPlayer = isPlayer;
    }

    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().newUser(game, player, isPlayer);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error on adding user to the game");
        }
    }
}
