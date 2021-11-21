package nomad.com.client.controller;

import nomad.com.client.ComClient;
import nomad.com.common.ComMessage;
import nomad.com.common.SocketClosedException;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComClientController {
    private final DataToComInterface dataToCom;
    private User currentUser;
    private ComClient client;

    public ComClientController(DataToComInterface dataToCom) {
        this.dataToCom = dataToCom;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public DataToComInterface getDataToCom() {
        return dataToCom;
    }

    /**
     * Initialize the socket between the client and the server.
     *
     * @throws IOException When client creation fails.
     */
    public void initSocket() throws IOException {
        if (currentUser == null || currentUser.getLastServer() == null) {
            throw new NullPointerException();
        }

        client = new ComClient(currentUser.getLastServer().getIpAddress(), currentUser.getLastServer().getPort());
        client.startListening();
    }

    /**
     *
     * @param message The message to send to the server.
     * @throws IOException If the message cannot be sent.
     */
    public void sendServerMessage(ComMessage message) throws IOException {
        try {
            client.sendMessage(message);
        } catch (SocketClosedException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Socket has been closed !");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
        }
    }
}
