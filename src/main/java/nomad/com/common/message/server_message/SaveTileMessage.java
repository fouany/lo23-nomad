package nomad.com.common.message.serverMessage;

import nomad.com.server.ServerController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.net.Socket;

public class SaveTileMessage extends BaseServerMessage{
    private final Tile t;

    public SaveTileMessage(Tile t) {
        this.t = t;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        try {
            controller.getDataToCom().saveTile(t);
        } catch (TileException e) {
            e.printStackTrace();
        }
        SaveTileMessage saveTileMessage = new SaveTileMessage(t);
        controller.sendMessage(socket, saveTileMessage);
    }
}
