package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.serverMessage.GameCreationMessage;
import nomad.com.common.message.serverMessage.LocalUserConnectionMessage;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.Tower;
import nomad.common.data_structure.UserLight;
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

        if (!clientController.sendMessage(new GameCreationMessage(name, host, nbTowers, areSpecAllowed, isSpecChatAllowed, hostColor))) { // Connect to the remote server
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send new game to the remote server !");
        }
    }

    @Override
    public void enoughPlayers(GameLight game) {

    }

    @Override
    public void rejectPlayer(GameLight game) {

    }

    @Override
    public void launchGame(GameLight game) {

    }

    @Override
    public void addPlayerInGame(Player player, GameLight game) {

    }

    @Override
    public void addSpecInGame(UserLight user, GameLight game) {

    }

    @Override
    public void placeTower(Tower tower) {

    }
}
