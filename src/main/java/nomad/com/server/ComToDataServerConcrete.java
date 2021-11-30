package nomad.com.server;

import nomad.com.common.message.client_message.game.NewGamePlayerClientMessage;
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

public class ComToDataServerConcrete implements ComToDataServerInterface {
    ServerController serverController;
    private static final String ERROR_SOCKET = "Failed to get client socket";

    @Override
    public void requestHost(GameLight game, Player opponent) {
        UUID hostId = game.getHost().getId();
        Socket client = serverController.getClientSocket(hostId);
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new NewGamePlayerClientMessage(game.getGameId(), opponent));
    }

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

    @Override
    public void towerValid(Tower t, List<UUID> listOther) {
        UUID userId = t.getUserId();
        Socket client = serverController.getClientSocket(userId);
        if (client == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
        }
        serverController.sendMessage(client, new ValidateTowerMoveMessage(t));
        for (UUID id : listOther) {
            Socket socket = serverController.getClientSocket(id);
            if (socket == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ERROR_SOCKET);
            }
            serverController.sendMessage(socket, new SendOtherPlayersMoveMessage(t));
        }
    }
}
