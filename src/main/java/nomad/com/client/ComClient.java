package nomad.com.client;

import nomad.com.client.message.IsDisconnectedMessage;
import nomad.com.message.ComMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ComClient creates a socket binding on the remote server.
 */
public class ComClient {
    private final Socket socket;
    private final ComClientListener listener;

    /**
     * Create a ComClient object to communicate with the remote server.
     *
     * @param address Server IP address
     * @param port    Server listening port
     * @throws IOException When unable to create a socket
     */
    public ComClient(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        listener = new ComClientListener(socket);
    }

    /**
     * Get the stored socket.
     *
     * @return the socket binded to the server or null if no connection is active.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Send a message to the server.
     * Warning ! The socket must be initialized and connected.
     *
     * @param message The message to send to the server.
     * @throws IOException If the message cannot be sent.
     */
    public void sendMessage(ComMessage message) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
        out.close();
    }

    /**
     * Start listening to the server for messages.
     */
    public void startListening() {
        listener.start();
    }
}
