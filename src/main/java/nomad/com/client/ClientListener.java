package nomad.com.client;

import nomad.com.common.exception.SocketClosedException;
import nomad.com.common.message.clientMessage.BaseClientMessage;

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
    private boolean listen;

    /**
     * Initialize ComClientListener and ObjectInputStream associated with socket
     *
     * @param client An open socket to the server
     * @param parent The parent controller
     */
    public ClientListener(Socket client, ClientController parent) throws IOException {
        listen = true;
        this.client = client;
        this.controller = parent;
        synchronized (this.client) {
            input = new ObjectInputStream(client.getInputStream());
        }
    }

    /**
     * Stop the listener thread
     */
    public void stopListening() {
        listen = false;
    }

    @Override
    public void run() {
        while (listen) {
            try {
                BaseClientMessage message = readMessage();
                if (message != null) {
                    message.process(controller);
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
     * Read a message from the socket, in a blocking way
     *
     * @return ComMessage or null if no message was available
     * @throws IOException            Fail to create InputStream
     * @throws ClassNotFoundException Fail to find the class of the serialized object
     * @throws SocketClosedException  Socket with the server has been closed
     */
    private BaseClientMessage readMessage() throws IOException, ClassNotFoundException, SocketClosedException {
        synchronized (client) {
            if (client.isClosed()) {
                input.close();
                stopListening();
                throw new SocketClosedException();
            }

            return (BaseClientMessage) input.readObject();
        }
    }
}
