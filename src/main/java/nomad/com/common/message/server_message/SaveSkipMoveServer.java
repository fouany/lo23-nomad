package nomad.com.common.message.server_message;

import nomad.com.server.ServerController;
import nomad.common.data_structure.Skip;
import nomad.common.data_structure.UserLight;

import java.net.Socket;

public class SaveSkipMoveServer extends BaseServerMessage {
    Skip m;
    UserLight user;

    public SaveSkipMoveServer(Skip m, UserLight user) {
        this.m = m;
        this.user = user;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        //TODO
    }
}
