package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.GameSerializable;
import nomad.common.data_structure.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerAddedInGameMessage extends BaseClientMessage {
    public final GameSerializable gameSerializable;
    public final Player opponent;
    private final boolean isValid;

    public PlayerAddedInGameMessage(GameSerializable game, boolean isValid) {
        this.gameSerializable = game;
        this.isValid = isValid;
        this.opponent = null;
    }


    /**
     * Constructor to fix game opponent issue. The opponent wasn't sent and was null
     * Adding opponent in the constructor allows to set the opponent before sending the game
     * To fix if possible
     *
     * @param game     is the game
     * @param isValid  is set to check if adding player is ready
     * @param opponent is the game opponent
     */
    public PlayerAddedInGameMessage(GameSerializable game, boolean isValid, Player opponent) {
        this.gameSerializable = game;
        this.isValid = isValid;
        this.opponent = opponent;
    }


    @Override
    public void process(ClientController controller) {
        try {
            Game game = gameSerializable.getGame();
            controller.getDataToCom().addedPlayerInGame(game, isValid);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to add player in game");
        }
    }
}
