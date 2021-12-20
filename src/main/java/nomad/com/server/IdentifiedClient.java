package nomad.com.server;

import nomad.com.common.message.server_message.BaseServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store information required for communication with client
 */
public class IdentifiedClient extends Thread {
    /**
     * Socket
     */
    private final Socket socket;
    /**
     * Server Controller
     */
    private final ServerController serverController;
    /**
     * Output stream
     */
    private final ObjectOutputStream outputStream;
    /**
     * Input stream
     */
    private final ObjectInputStream inputStream;
    /**
     * Id of the thread
     */
    private UUID id;
    /**
     * Boolean to check if the client is connected
     */
    private boolean connected;
    /**
     * Build IdentifiedClient object and initialize in/out streams with the client
     *
     * @param id               Client UID
     * @param socket           Socket connected to the client
     * @param serverController Reference to a Server object
     * @throws IOException Fail to register ObjectStreams
     */
    public IdentifiedClient(UUID id, Socket socket, ServerController serverController) throws IOException {
        this.id = id;
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.serverController = serverController;
        this.connected = true;
    }

    /**
     * Getter output stream
     * @return outputStream
     */
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Getter socket
     * @return socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Getter User ID
     * @return id
     */
    //TODO Rename function
    public UUID getUID() {
        return id;
    }

    /**
     * Setter User id
     * @param id id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Stop the message listener thread
     */
    public void stopClientCommunication() {
        connected = false;
    }

    /**
     * Run method
     */
    @Override
    public void run() {
        // Loop while the client is connected
        while (connected) {
            try {
                BaseServerMessage message = (BaseServerMessage) inputStream.readObject();
                message.process(socket, serverController); // When there is a message sent on the network, it triggers the process function of it
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
                serverController.disconnectClient(socket);
                connected = false;
            }
        }
    }
}