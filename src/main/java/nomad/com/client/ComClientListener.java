package nomad.com.client;

import nomad.com.client.message.ComClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ComClientListener listens messages from a specified remote server.
 */
public class ComClientListener extends Thread {

    private final Socket client;

    /**
     * Initialize ComClientListener.
     * @param client An open socket to the server.
     */
    public ComClientListener(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (client.isConnected() && !client.isClosed()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ComClientMessage message = (ComClientMessage) ois.readObject();
                // TODO : process message with a MessageProcessor class
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to receive message from server !");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
            }
        }
    }
}
