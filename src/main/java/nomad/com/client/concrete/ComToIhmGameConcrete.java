package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.server_message.SaveMoveServer;
import nomad.com.common.message.server_message.SaveSkipMoveServer;
import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToIhmGameInterface;

public class ComToIhmGameConcrete implements ComToIhmGameInterface {
    ClientController clientController;

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void checkGameEnd(GameLight game) {
        //TODO
    }

    @Override
    public void transmissionCom(Message msg) {
        //TODO
    }

    @Override
    public void playMove(Move move) {
        UserLight u = clientController.getDataToCom().getUserLight();
        Class<? extends Move> moveType = move.getClass();

        if (moveType.equals(Tile.class)) {
            clientController.sendMessage(new SaveMoveServer((Tile) move, u));
        } else if (moveType.equals(Skip.class)) {
            clientController.sendMessage(new SaveSkipMoveServer((Skip) move, u));
        }
    }
}
