package nomad.com.common.message.client_message;

import nomad.com.client.ClientController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateTileMoveMessage extends BaseClientMessage {
    private final Tile tile;

    public ValidateTileMoveMessage(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().tileValid(tile, true);
        } catch (TileException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to validate the tile move");
        }
    }
}
