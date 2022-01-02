package nomad.com.common.message.server_message.move;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SaveTileMoveMessage contains a tile to call the saveTile method
 */
public class SaveTileMoveMessage extends BaseServerMessage {
    /**
     * Instance of tile
     */
    private final Tile tile;

    /**
     * Constructor of SaveTileMoveMessage class
     * @param tile the tile sent by the client app
     */
    public SaveTileMoveMessage(Tile tile) {
        this.tile = tile;
    }

    /**
     * When this message is received, call the saveTile method
     * @param socket the client's socket
     * @param controller an instance of the server controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        try {
            controller.getDataToCom().saveTile(tile);
        } catch (TileException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to make the tile move");
        }
    }
}
