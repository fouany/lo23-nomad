package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.server_message.game.CheckGameServerMessage;
import nomad.com.common.message.server_message.move.SaveTileMoveMessage;
import nomad.com.common.message.server_message.move.SaveSkipMoveMessage;
import nomad.com.common.message.server_message.move.SaveTowerMoveMessage;
import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToIhmGameInterface;

public class ComToIhmGameConcrete implements ComToIhmGameInterface {
    ClientController clientController;

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void checkGameEnd(GameLight game) {
        CheckGameServerMessage checkgameservermessage = new CheckGameServerMessage(game);
        clientController.sendMessage(checkgameservermessage);
    }

    @Override
    public void transmissionCom(Message msg) {
        //TODO
    }

    @Override
    public void playMove(Move move) {
        Class<? extends Move> moveType = move.getClass();

        if (moveType.equals(Tile.class)) {
            clientController.sendMessage(new SaveTileMoveMessage((Tile) move));
        } else if (moveType.equals(Skip.class)) {
            clientController.sendMessage(new SaveSkipMoveMessage((Skip) move));
        } else if (moveType.equals(Tower.class)) {
            clientController.sendMessage(new SaveTowerMoveMessage((Tower) move));
        }
    }
}
