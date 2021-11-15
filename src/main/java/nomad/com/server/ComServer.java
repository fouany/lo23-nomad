package nomad.com.server;

import nomad.com.client.ComClient;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class ComServer extends Thread {
    public HashMap<UUID, ComClient> clientList = new HashMap<>();
    public HashMap<UUID, ComServerListener> listenerList = new HashMap<>();
    private ServerSocket serverSocket;
    private DataToComInterface DataToCom;

    public ComServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ComServerListener listener = new ComServerListener(client);
                listener.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
