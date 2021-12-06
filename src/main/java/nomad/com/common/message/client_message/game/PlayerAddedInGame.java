package nomad.com.common.message.client_message.game;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerAddedInGame extends BaseClientMessage {
    public final Game game;
    private boolean isValid;
    public final Player opponent;

    public PlayerAddedInGame(Game game, boolean isValid) {
        this.game = game;
        this.isValid = isValid;
        this.opponent = null;
    }

    /**
     * Nouveau contructeur pour fixer le bug de l'atrribut opponent qui passe a nul lors de l'envoi
     * ajout d'un objet opponent pour relier les deux bou coté client
     */


    public PlayerAddedInGame(Game game, boolean isValid, Player opponent) {
        this.game = game;
        this.isValid = isValid;
        this.opponent = opponent;
    }




    @Override
    public void process(ClientController controller) {
        try {
            game.setOpponent(opponent);
            controller.getDataToCom().addedPlayerInGame(game, isValid);
        } catch (GameException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to add player in game");
        }
    }
    /*todo fix one day bug with opponent*/
}
