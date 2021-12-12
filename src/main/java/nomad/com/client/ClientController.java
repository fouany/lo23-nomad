package nomad.com.client;

import nomad.com.common.message.Message;
import nomad.com.common.message.server_message.information.LocalUserDisconnectionMessage;
import nomad.common.interfaces.data.DataToComClientInterface;
import nomad.common.interfaces.main.IhmMainToComInterface;

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
    private final DataToComClientInterface dataToCom;

    private final IhmMainToComInterface ihmMainToCom;
    private ClientListener listener;

    private ObjectOutputStream output;
    private Socket socket;

    public ClientController(DataToComClientInterface dataToCom, IhmMainToComInterface ihmMainToCom) {
        this.dataToCom = dataToCom;
        this.ihmMainToCom = ihmMainToCom;
    }

    public DataToComClientInterface getDataToCom() {
        return dataToCom;
    }

    public IhmMainToComInterface getIhmMainToCom() {
        return ihmMainToCom;
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

    /**
     * Disconnect the client from the server. If the socket is still active, inform the server of the disconnection.
     */
    public void disconnect() {
        if (!socket.isClosed()) {
            sendMessage(new LocalUserDisconnectionMessage());
            try {
                socket.close();
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error happened during the disconnection from the server !");
            }
        }

        listener.stopListening();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Disconnected from the server");
    }

    /**
     * Send a message to the server
     * Warning ! The socket must be initialized and connected
     *
     * @param message The message to send to the server
     */
    public boolean sendMessage(Message message) {
        try {
            if (socket.isClosed()) {
                disconnect();
                return false;
            }
            output.writeObject(message);
            output.flush();

        } catch (Exception e) {
            disconnect();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message remote server !");
            return false;
        }
        return true;
    }
}