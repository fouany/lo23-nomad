package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Use to validate a tile move
 */
public class ValidateTileMoveMessage extends BaseClientMessage {
    /**
     * Tile
     */
    private final Tile tile;

    /**
     * Constructor
     * @param tile Tile
     */
    public ValidateTileMoveMessage(Tile tile) {
        this.tile = tile;
    }

    /**
     * Process to Data
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().tileValid(tile, true);
        } catch (TileException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to validate the tile move");
        }
    }
}
