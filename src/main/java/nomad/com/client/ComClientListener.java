package nomad.com.client;

import nomad.com.client.message.ComClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ComClientListener extends Thread {

    private final Socket client;

    public ComClientListener(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ComClientMessage message = (ComClientMessage) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
