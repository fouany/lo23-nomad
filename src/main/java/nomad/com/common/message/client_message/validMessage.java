package nomad.com.common.message.clientMessage;
import nomad.com.client.ClientController;
import nomad.com.common.message.serverMessage.SaveTileMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.TileException;

import java.net.Socket;

public class validMessage extends BaseClientMessage {
    private final Tile t;

    public validMessage(Tile t){
        this.t = t;
    }

    @Override
    public void process(ClientController controller) {
        super.process(controller);
    }
}
