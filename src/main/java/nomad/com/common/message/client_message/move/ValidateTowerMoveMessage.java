package nomad.com.common.message.client_message.move;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.BaseClientMessage;
import nomad.common.data_structure.Tower;

public class ValidateTowerMoveMessage extends BaseClientMessage {
    private final Tower tower;

    public ValidateTowerMoveMessage(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void process(ClientController controller) {
        controller.getDataToCom().towerValid(tower, true);
    }
}
