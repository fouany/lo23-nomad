package nomad.com.client;

import nomad.com.common.message.ComMessage;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ComClientController creates a socket binding on the remote server
 */
public class ClientController {
    private final MessageProcessor messageProcessor;
    private ObjectOutputStream output;
    private Socket socket;

    public ClientController(DataToComInterface dataToCom) {
        this.messageProcessor = new MessageProcessor(dataToCom);
    }

    /**
     * Initialize the ComClientController object to communicate with the remote server
     *
     * @param address Server IP address
     * @param port    Server listening port
     * @throws IOException Unable to create the socket
     */
    public void connectClient(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        ClientListener listener;
        synchronized (socket) {
            output = new ObjectOutputStream(socket.getOutputStream());
            listener = new ClientListener(socket, this);
        }

        listener.start(); // Start listening for server messages
    }

    /**
     * Get the stored socket
     *
     * @return the socket binded to the server or null if no connection is active
     */
    public Socket getSocket() {
        return socket;
    }

    public void processMessage(ComMessage message) {
        messageProcessor.processMessage(message);
    }

    /**
     * Send a message to the server
     * Warning ! The socket must be initialized and connected
     *
     * @param message The message to send to the server
     */
    public boolean sendMessage(ComMessage message) {
        synchronized (socket) {
            try {
                if (socket.isClosed()) {
                    output.close();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Socket with the server has been closed !");
                }
                output.writeObject(message);

            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message remote server !");
                return false;
            }
        }
        return true;
    }
}