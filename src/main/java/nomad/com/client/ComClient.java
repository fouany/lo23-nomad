package nomad.com.client;

import nomad.com.client.controller.ComClientController;
import nomad.common.data_structure.Server;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComInterface;
import nomad.main.controller.ServerConnectionController;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComClient extends Thread implements Serializable {
    public Socket socket;
    public ComClientListener listener;
    public DataToComInterface dataToCom;
    public ComClientController clientController;

    public ComClient(InetAddress adress, int port) {
        try {
            socket = new Socket(adress, port);
        } catch (IOException e) {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.INFO, e.toString());
        }
    }


    @Override
    public void run() {
        listener = new ComClientListener(socket);
        listener.start();
    }
}
