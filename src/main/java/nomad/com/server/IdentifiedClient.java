package nomad.com.server;

import nomad.com.common.message.ComMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store informations required for communication with client
 */
public class IdentifiedClient extends Thread {

    /**
     * Build IdentifiedClient object and initialize in/out streams with the client
     *
     * @param id Client UID
     * @param socket Socket connected to the client
     * @param server Reference to a Server object
     * @throws IOException Fail to register ObjectStreams
     */
    public IdentifiedClient(UUID id, Socket socket, Server server) throws IOException {
        this.id = id;
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.messageProcessor = server.getMessageProcessor();
        this.server = server;
        this.connected = true;
    }

    private UUID id;
    private final Socket socket;
    private final Server server;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private final MessageProcessor messageProcessor;
    private boolean connected;

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public UUID getUID() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Stop the message listener thread
     */
    public void stopClientCommunication() {
        connected = false;
    }

    @Override
    public void run() {
        while (connected) {
            try {
                messageProcessor.processMessage(socket, (ComMessage) inputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
                server.disconnectClient(socket);
                connected = false;
            }
        }
    }
}