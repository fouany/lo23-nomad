package nomad.com.client;

import nomad.com.common.ComMessage;
import nomad.com.common.SocketClosedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ComClientListener listens messages from a specified remote server.
 */
public class ClientListener extends Thread {

    private final Socket client;
    private final ObjectInputStream input;
    private final ClientController controller;

    /**
     * Initialize ComClientListener and ObjectInputStream associated with socket
     *
     * @param client An open socket to the server
     * @param parent The parent controller
     */
    public ClientListener(Socket client, ClientController parent) throws IOException {
        this.client = client;
        this.controller = parent;
        synchronized (this.client) {
            input = new ObjectInputStream(client.getInputStream());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                ComMessage message = readMessage();
                if (message != null) {
                    controller.processMessage(message);
                }

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to receive message from server !");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
            } catch (SocketClosedException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Socket has been closed !");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
                break;
            }
        }
    }

    /**
     * Read a message from the socket, in a non-blocking way
     *
     * @return ComMessage or null if no message was available
     * @throws IOException            Fail to create InputStream
     * @throws ClassNotFoundException Fail to find the class of the serialized object
     * @throws SocketClosedException  Socket with the server has been closed
     */
    private ComMessage readMessage() throws IOException, ClassNotFoundException, SocketClosedException {
        synchronized (client) {
            if (client.isClosed()) {
                input.close();
                throw new SocketClosedException();
            }

            ComMessage message = null;
            if (input.available() > 0) {
                message = (ComMessage) input.readObject();
            }

            return message;
        }
    }
}
