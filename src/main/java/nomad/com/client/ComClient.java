package nomad.com.client;

import nomad.com.common.ComMessage;
import nomad.com.common.SocketClosedException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ComClient creates a socket binding on the remote server
 */
public class ComClient {
    private final ObjectOutputStream output;
    private final Socket socket;
    private final ComClientListener listener;

    /**
     * Create a ComClient object to communicate with the remote server
     *
     * @param address Server IP address
     * @param port    Server listening port
     * @throws IOException Unable to create the socket
     */
    public ComClient(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        synchronized (socket) {
            output = new ObjectOutputStream(socket.getOutputStream());
            listener = new ComClientListener(socket);
        }
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
     * Send a message to the server
     * Warning ! The socket must be initialized and connected
     *
     * @param message The message to send to the server
     * @throws IOException           The message cannot be sent to server
     * @throws SocketClosedException Socket with the server has been closed
     */
    public void sendMessage(ComMessage message) throws IOException, SocketClosedException {
        synchronized (socket) {
            if (socket.isClosed()) {
                output.close();
                throw new SocketClosedException();
            }
            output.writeObject(message);
        }
    }


    /**
     * Start listening to the server for messages
     */
    public void startListening() {
        listener.start();
    }
}
