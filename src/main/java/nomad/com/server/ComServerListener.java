package nomad.com.server;

import nomad.com.server.message.ComServerMessage;
import nomad.com.server.message.ConnectionRequestMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ComServerListener extends Thread {
    private final Socket client;

    public ComServerListener(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        getFirstConnexionMessage();

        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ComServerMessage message = (ComServerMessage) in.readObject();
                message.process();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void getFirstConnexionMessage() {
        try {
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ConnectionRequestMessage message = (ConnectionRequestMessage) in.readObject();
            message.setListener(this);
            message.process();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
