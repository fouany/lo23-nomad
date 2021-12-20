package nomad.com.common.message.server_message.move;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Tower;
import nomad.common.data_structure.TowerException;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SaveTowerMoveMessage contains a tower to call the saveTower method
 */
public class SaveTowerMoveMessage extends BaseServerMessage {
    /**
     * Instance of Tower
     */
    private final Tower tower;

    /**
     * Constructor of SaveTowerMoveMessage class
     * @param tower the tower sent by the client app
     */
    public SaveTowerMoveMessage(Tower tower) {
        this.tower = tower;
    }

    /**
     * When this message is received, tries to call the saveTower method
     * @param socket the client's socket
     * @param controller instance of the server controller
     */
    @Override
    public void process(Socket socket, ServerController controller) {
        try {
            controller.getDataToCom().saveTower(tower);
        } catch (TowerException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to make the tile move");
        }
    }
}
