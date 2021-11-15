package nomad.com.client;

import nomad.com.client.controller.ComClientController;
import nomad.common.data_structure.Server;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class ComClient extends Thread implements Serializable {
    public int port;
    public String ipRemote;
    public Socket socket;
    public User user;
    public ComClientListener listener;
    public DataToComInterface dataToCom;
    public ComClientController clientController;

    public ComClient(User user) {
        Server server = user.getLastServer();
        this.port = server.getPort();
        this.ipRemote = server.getIpAddress().toString();
    }


    @Override
    public void run() {
        try {
            socket = new Socket(ipRemote, port);
            listener = new ComClientListener(socket);
            listener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
