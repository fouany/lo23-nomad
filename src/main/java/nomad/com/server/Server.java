package nomad.com.server;

import nomad.com.common.message.ComMessage;
import nomad.com.common.message.UserChangedMessage;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private final HashMap<Socket, IdentifiedClient> clientList = new HashMap<>();
    private ServerSocket serverSocket;
    private final DataToComServerInterface dataToCom;
    private final MessageProcessor messageProcessor;
    private boolean serverRun = true;

    public Server(int port, DataToComServerInterface dataToCom) {
        this.dataToCom = dataToCom;
        this.messageProcessor = new MessageProcessor(dataToCom, this);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Register an user on a unidentified socket
     *
     * @param socket Socket connected to the client
     * @param user   User profile transmitted by client
     */
    public void registerUser(Socket socket, User user) {
        IdentifiedClient identified = clientList.get(socket);
        identified.setId(user.getUserId());
        clientList.put(socket, identified);
    }

    /**
     * Get the User UID associated with a given socket
     *
     * @param socket Socket connected to a client
     * @return UID of the associated identified user or null if not found or not identified
     */
    public UUID getAssociatedUser(Socket socket) {
        if (clientList.containsKey(socket)) {
            return clientList.get(socket).getUID();
        }

        return null;
    }

    /**
     * Send a message to a specified client. In case of error, the client is disconnected
     *
     * @param client  Destination of the message
     * @param message Message to be send
     */
    public void sendMessage(Socket client, ComMessage message) {
        try {
            clientList.get(client).getOutputStream().writeObject(message);
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
            disconnectClient(client, clientList.get(client).getUID()); // Error has happened, kick the faulty client
        }
    }

    /**
     * Broadcast a message to identified clients
     *
     * @param message Message to broadcast
     */
    public void broadcast(ComMessage message) {
        for (Map.Entry<Socket, IdentifiedClient> entry : clientList.entrySet()) {
            if (entry.getValue().getUID() == null) { // Skip unidentified users
                continue;
            }

            try {
                entry.getValue().getOutputStream().writeObject(message);
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
                disconnectClient(entry.getKey(), entry.getValue().getUID()); // Error has happened, kick the faulty client
            }
        }
    }

    /**
     * Disconnect a client and broadcast the change au identified users
     *
     * @param client Client to disconnect
     * @param userId ID of the client to disconnect
     */
    public void disconnectClient(Socket client, UUID userId) {
        clientList.get(client).stopClientCommunication(); // Stop client communication thread
        clientList.remove(client); // Remove client from identified clients list
        try {
            client.close();
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to close connection with client !");
        }

        broadcast(new UserChangedMessage(dataToCom.getUserProfile(userId), false)); // announce disconnection to all clients
    }

    /**
     * Get the MessageProcessor instance of the server
     *
     * @return an initialized MessageProcessor
     */
    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    /**
     * Stop the server thread and do not allow new connections
     */
    public void stopServer() {
        serverRun = false;
    }

    @Override
    public void run() {
        while (serverRun) {
            try {
                Socket client = serverSocket.accept();
                IdentifiedClient unidentified = new IdentifiedClient(null, client, this);
                unidentified.start();
                clientList.put(client, unidentified); // Add unidentified user
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to create client connection !");
            }
        }
    }
}
