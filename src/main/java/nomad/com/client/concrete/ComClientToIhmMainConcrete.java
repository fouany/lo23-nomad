package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.server_message.game.GameCreationMessage;
import nomad.com.common.message.server_message.game.LaunchGameMessage;
import nomad.com.common.message.server_message.game.NewGamePlayerServerMessage;
import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToIhmMainInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ComClientToIhmMainConcrete implements ComToIhmMainInterface {
    ClientController clientController;

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    /**
     * newGame informs the server that a new game has been created
     *
     * @param name              is the name of the game
     * @param host              is the host user of the game
     * @param nbTowers          is the number of towers in the party
     * @param areSpecAllowed    allows spectators in the game
     * @param isSpecChatAllowed allows spectator to chat together
     * @param hostColor         is the playing color of the host
     */
    @Override
    public void newGame(String name, UserLight host, int nbTowers, boolean areSpecAllowed, boolean isSpecChatAllowed, boolean hostColor) {
        if (host == null) {
            throw new NullPointerException();
        }
        if (!clientController.sendMessage(new GameCreationMessage(name, host, nbTowers, areSpecAllowed, isSpecChatAllowed, hostColor))) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send new game to the remote server !");
        }
    }

    /**
     * @param player is the player added in the game
     * @param game is the game joined by the player
     */
    @Override
    public void addPlayerInGame(Player player, GameLight game) {
        if (player == null || game == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null data");
        }

        if (!clientController.sendMessage(new NewGamePlayerServerMessage(player, game))) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send new player request to the remote server !");
        }
    }

    @Override
    public void launchGame(Game game) {
        if (game == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null datas");
        }

        if (!clientController.sendMessage(new LaunchGameMessage(game))) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send launch game request to the remote server !");
        }
    }

    @Override
    public void enoughPlayers(GameLight game) {
        //TODO
    }

    @Override
    public void rejectPlayer(GameLight game) {
        //TODO
    }



    @Override
    public void addSpecInGame(UserLight user, GameLight game) {
        //TODO
    }

    @Override
    public void placeTower(Tower tower) {
        //TODO
    }
}
