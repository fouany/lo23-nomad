package nomad.com.server;

import nomad.com.client.ClientController;
import nomad.com.common.message.ComMessage;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.net.Socket;
import java.util.UUID;

public class ServerController {
//    private final Server server;
//    private final DataToComServerInterface dataToCom;
//
//    public ServerController(int port, DataToComServerInterface dataToCom) {
//        server = new Server(port, dataToCom);
//        this.dataToCom = dataToCom;
//    }
//
//    public void sendClientMessage(Socket client, ComMessage message) {
//        ServerSender sender = new ServerSender(client, message);
//        sender.start();
//    }
//
//    public void requestConnection(ClientController client, ServerListener listener) {
//        //UUID userId = client.user.getUserId();
//        //server.clientList.put(userId, client);
//        //server.listenerList.put(userId, listener);
//    }
//
//    public void requestDisconnection(UUID userId) {
//        server.clientList.remove(userId);
//        server.listenerList.get(userId).stop();
//        server.listenerList.remove(userId);
//    }
//
//    public void startServer() {
//        server.start();
//    }
}
