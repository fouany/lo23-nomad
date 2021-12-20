package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.server_message.chat.SendChatMessageMessage;
import nomad.com.common.message.server_message.move.SaveSkipMoveMessage;
import nomad.com.common.message.server_message.move.SaveTileMoveMessage;
import nomad.com.common.message.server_message.move.SaveTowerMoveMessage;
import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToIhmGameInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ComToIhmGameConcrete implements ComToIhmGameInterface {
    ClientController clientController;

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void transmissionCom(Message message) {
        sendMessage(new SendChatMessageMessage(message), "Failed to send SendChatMessageMessage to the remote server");
    }

    @Override
    public void playMove(Move move) {
        Class<? extends Move> moveType = move.getClass();

        if (moveType.equals(Tile.class)) {
            sendMessage(new SaveTileMoveMessage((Tile) move), "Failed to send SaveTileMoveMessage to the remote server");
        } else if (moveType.equals(Skip.class)) {
            sendMessage(new SaveSkipMoveMessage((Skip) move), "Failed to send SaveSkipMoveMessage to the remote server");
        } else if (moveType.equals(Tower.class)) {
            sendMessage(new SaveTowerMoveMessage((Tower) move), "Failed to send SaveTowerMoveMessage to the remote server");
        }
    }

    private void sendMessage(nomad.com.common.message.Message message, String errorMessage) {
        if (!clientController.sendMessage(message)) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, errorMessage);
        }
    }
}
