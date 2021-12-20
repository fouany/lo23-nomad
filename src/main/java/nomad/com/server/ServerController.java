package nomad.com.server;

import nomad.com.common.message.Message;
import nomad.com.common.message.client_message.information.UserChangedMessage;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server Controller which controls the server
 */
public class ServerController extends Thread {
    /**
     * Client list which associate a Socket to a Client Id
     */
    private final HashMap<Socket, IdentifiedClient> clientList = new HashMap<>();
    /**
     * Interface data to com
     */
    private final DataToComServerInterface dataToCom;
    /**
     * Server socket
     */
    private ServerSocket serverSocket;
    /**
     * Boolean to check while the server is running
     */
    private boolean serverRun = true;

    /**
     * Constructor
     * @param port port in which runs the server
     * @param dataToCom Interface Datatocom to communicate with Data
     */
    public ServerController(int port, DataToComServerInterface dataToCom) {
        this.dataToCom = dataToCom;
        try {
            serverSocket = new ServerSocket(port); // Initialize the server
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Unable to create the ServerSocket");
        }
    }

    /**
     * Getter Datatocom interface
     * @return
     */
    public DataToComServerInterface getDataToCom() {
        return dataToCom;
    }

    /**
     * Getter client list
     * @return server clientList
     */
    public Map<Socket, IdentifiedClient> getClientList() {
        return clientList;
    }

    /**
     * Register an user on a unidentified socket
     * @param socket Socket connected to the client
     * @param user   User profile transmitted by client
     */
    public void registerUser(Socket socket, User user) {
        IdentifiedClient identified = clientList.get(socket);
        identified.setId(user.getUserId());
        clientList.put(socket, identified);
    }

    /**
     * Get the client socket with a given ID
     *
     * @param userId ID of the user
     * @return Socket associate to the user
     */
    public Socket getClientSocket(UUID userId) {
        for (IdentifiedClient client : clientList.values()) {
            if (client.getUID().equals(userId)) {
                return client.getSocket();
            }
        }
        return null;
    }


    /**
     * Send a message to a specified client. In case of error, the client is disconnected
     *
     * @param client  Destination of the message
     * @param message Message to be send
     */
    public void sendMessage(Socket client, Message message) {
        try {
            clientList.get(client).getOutputStream().writeObject(message);
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
            disconnectClient(client); // Error has happened, kick the faulty client
        }
    }

    /**
     * Broadcast a message to identified clients
     *
     * @param message Message to broadcast
     */
    public void broadcast(Message message) {
        // Loop in the Client connected in the game
        for (Map.Entry<Socket, IdentifiedClient> entry : clientList.entrySet()) {
            if (entry.getValue().getUID() == null) { // Skip unidentified users
                continue;
            }

            try {
                entry.getValue().getOutputStream().writeObject(message); // Send message
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send message to client !");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
                disconnectClient(entry.getKey()); // Error has happened, kick the faulty client
            }
        }
    }

    /**
     * Disconnect a client and broadcast the change au identified users
     *
     * @param client Client to disconnect
     */
    public void disconnectClient(Socket client) {
        clientList.get(client).stopClientCommunication(); // Stop client communication thread
        UUID uid = clientList.get(client).getUID();
        clientList.remove(client); // Remove client from identified clients list
        try {
            client.close();
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to close connection with client !");
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Disconnected user from server !");

        if (uid != null) {
            broadcast(new UserChangedMessage(dataToCom.getUserProfile(uid), false)); // announce disconnection to all clients
            dataToCom.updateUserListRemove(uid);
        }
    }

    /**
     * Stop the server thread and do not allow new connections
     */
    public void stopServer() {
        serverRun = false;
    }

    /**
     * Run function
     */
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
