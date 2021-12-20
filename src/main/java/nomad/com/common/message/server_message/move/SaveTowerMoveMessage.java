package nomad.com.common.message.server_message.move;

import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Tower;
import nomad.common.data_structure.TowerException;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveTowerMoveMessage extends BaseServerMessage {
    private final Tower tower;

    public SaveTowerMoveMessage(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        try {
            controller.getDataToCom().saveTower(tower);
        } catch (TowerException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to make the tower move");
        }
    }
}
