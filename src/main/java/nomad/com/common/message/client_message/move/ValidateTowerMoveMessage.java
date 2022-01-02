package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.TileException;
import nomad.common.data_structure.Tower;
import nomad.common.data_structure.TowerException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use to validate a Tower move
 */
public class ValidateTowerMoveMessage extends BaseClientMessage {
    /**
     * Tower
     */
    private final Tower tower;

    /**
     * Constructor
     * @param tower Tower
     */
    public ValidateTowerMoveMessage(Tower tower) {
        this.tower = tower;
    }

    /**
     * Process to data
     * @param controller clientController
     */
    @Override
    public void process(ClientController controller) {
        try {
            controller.getDataToCom().towerValid(tower, true);
        } catch (TowerException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to validate the tower move");
        }

    }
}
