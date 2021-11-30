package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveTileMoveMessage extends BaseServerMessage {
    private final Tile tile;

    public SaveTileMoveMessage(Tile tile) {
        this.tile = tile;
    }

    public void process(Socket socket, ServerController controller) {
        try {
            controller.getDataToCom().saveTile(tile);
        } catch (TileException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to make the tile move");
        }
    }
}
