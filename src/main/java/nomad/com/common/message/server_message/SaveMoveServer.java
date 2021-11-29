package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.UserLight;

import java.net.Socket;

public class SaveMoveServer extends BaseServerMessage{
    Tile m;
    UserLight user;

    public SaveMoveServer(Tile m, UserLight user) {
        this.m = m;
        this.user = user;
    }

    public void process(Socket socket, ServerController controller) {
        //TODO
    }
}
