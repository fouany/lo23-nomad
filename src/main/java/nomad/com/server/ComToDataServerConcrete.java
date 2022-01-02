package nomad.com.server;

import nomad.com.common.message.client_message.game.EndGameMessage;
import nomad.com.common.message.client_message.game.NewGamePlayerClientMessage;
import nomad.com.common.message.client_message.game.RemoveGameMessage;
import nomad.com.common.message.client_message.move.SendOtherPlayersMoveMessage;
import nomad.com.common.message.client_message.move.ValidateSkipMoveMessage;
import nomad.com.common.message.client_message.move.ValidateTileMoveMessage;
import nomad.com.common.message.client_message.move.ValidateTowerMoveMessage;
import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToDataServerInterface;

import java.net.Socket;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ComToDataServer contains basics functions to communicate with Data
 */
public class ComToDataServerConcrete implements ComToDataServerInterface {
    /**
     * Error message logged when the server faile to get client socket
     */
    private static final String ERROR_SOCKET = "Failed to get client socket";
    /**
     * Instance of serverController
     */
    ServerController serverController;

    /**
     * Request a host when creating a game
     * @param game an instance of a game
     * @param opponent  an instance of an opponent
     */
    @Override
    public void requestHost(GameLight game, Player opponent) {
        UUID hostId = game.getHost().getId();
        Socket client = serverController.getClientSocket(hostId);
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new NewGamePlayerClientMessage(game.getGameId(), opponent));
    }

    /**
     * Setter of server controller
     * @param serverController an instance of server controller
     */
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    /**
     * Tile validation function
     * @param t an instance of a tile
     * @param listOther an instance of the list which contains all the Tile
     */
    @Override
    public void tileValid(Tile t, List<UUID> listOther) {
        UUID userId = t.getUserId();
        Socket client = serverController.getClientSocket(userId);
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new ValidateTileMoveMessage(t));
        for (UUID id : listOther) {
            Socket socket = serverController.getClientSocket(id);
            if (socket == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
            }
            serverController.sendMessage(socket, new SendOtherPlayersMoveMessage(t));
        }
    }

    /**
     *
     * @param s an instance of a skip
     * @param listOther an instance of the List which contains all the Tile
     */
    @Override
    public void skipValid(Skip s, List<UUID> listOther) {
        UUID userId = s.getUserId();
        Socket client = serverController.getClientSocket(userId);
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new ValidateSkipMoveMessage(s));
        for (UUID id : listOther) {
            Socket socket = serverController.getClientSocket(id);
            if (socket == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
            }
            serverController.sendMessage(socket, new SendOtherPlayersMoveMessage(s));
        }
    }

    /**
     * Verification if the tower placement is valid
     * @param t Tower
     * @param listOther List of the Other Player in the game
     */
    @Override
    public void towerValid(Tower t, List<UUID> listOther) {
        UUID userId = t.getUserId();
        Socket client = serverController.getClientSocket(userId);
        // Check if the client exist
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new ValidateTowerMoveMessage(t));
        // Loop in all users in the Game and send them the new move
        for (UUID id : listOther) {
            Socket socket = serverController.getClientSocket(id);
            // Check is the socket exist
            if (socket == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
            }
            serverController.sendMessage(socket, new SendOtherPlayersMoveMessage(t)); // Message : Send move to other players
        }
    }

    /**
     * Game over function which trigger the disconnect message
     * @param idGame Id of the game
     * @param users List of users in the game
     * @param lastMove Last move that lead to the end
     * @param winner Winner of the game
     */
    @Override
    public void gameOver(UUID idGame, List<UUID> users, Move lastMove, UUID winner){
        EndGameMessage endGameMessage = new EndGameMessage(idGame,winner,lastMove);
        RemoveGameMessage removeGameMessage = new RemoveGameMessage(idGame);
        // Loop in the users of the game
        for (UUID id : users) {
            Socket socket = serverController.getClientSocket(id);
            // Check if the socket exists
            if (socket == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
            }
            serverController.sendMessage(socket, endGameMessage); // Send endgame message
        }
        // Loop in the users of the game and disconnect them
        for (Socket client : serverController.getClientList().keySet()){
            serverController.sendMessage(client, removeGameMessage);
        }
    }
}
