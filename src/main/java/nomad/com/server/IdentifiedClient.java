package nomad.com.server;

import nomad.com.common.message.serverMessage.BaseServerMessage;

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

    private UUID id;
    private final Socket socket;
    private final ServerController serverController;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private boolean connected;

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public Socket getSocket() {
        return socket;
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
                BaseServerMessage message = (BaseServerMessage) inputStream.readObject();
                message.process(socket, serverController);
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
                serverController.disconnectClient(socket);
                connected = false;
            }
        }
    }
}