package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.Message;
import nomad.com.common.message.server_message.game.AddSpecInGameMessage;
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
        sendMessage(new GameCreationMessage(name, host, nbTowers, areSpecAllowed, isSpecChatAllowed, hostColor), "Failed to send GameCreationMessage to the remote server !");
    }

    /**
     * @param player is the player added in the game
     * @param game   is the game joined by the player
     */
    @Override
    public void addPlayerInGame(Player player, GameLight game) {
        if (player == null || game == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Null data");
        }

        sendMessage(new NewGamePlayerServerMessage(player, game), "Failed to send NewGamePlayerServerMessage to the remote server !");
    }

    /**
     * launchGame send a message to the server to launch a new game
     *
     * @param game is the game to launch
     */
    @Override
    public void launchGame(Game game) {
        
        if (game == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Game is null");
            return;
        }

        sendMessage(new LaunchGameMessage(game.getGameSerializable()), "Failed to send LaunchGameMessage to the remote server");
    }

    /**
     * addSpecInGame send a message to add a spectator in a specific game
     * @param user is the spectator added in the game
     * @param game is the game that the spectator wants to join
     */
    @Override
    public void addSpecInGame(UserLight user, GameLight game) {
        sendMessage(new AddSpecInGameMessage(user, game), "Failed to send AddSpecInGameMessage to the remote server");
    }

    /**
     * sendMessage is a private function to send a message to the server
     *
     * @param message is the message to sent
     * @param errorMessage is the error message to sent
     */
    private void sendMessage(Message message, String errorMessage) {
        if (!clientController.sendMessage(message)) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, errorMessage);
        }
    }
}
