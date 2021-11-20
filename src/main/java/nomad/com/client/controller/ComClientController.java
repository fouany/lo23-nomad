package nomad.com.client.controller;

import nomad.com.client.ComClient;
import nomad.com.client.ComClientSender;
import nomad.com.message.ComMessage;
import nomad.common.data_structure.User;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.Serializable;

public class ComClientController implements Serializable {
    private final DataToComInterface dataToCom;
    private ComClientSender sender;
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
     * Initialize the socket between the client and the server
     */
    public void initSocket() {
        if (currentUser == null || currentUser.getLastServer() == null) {
            throw new RuntimeException();
        }
        client = new ComClient(currentUser);
        client.run();
        }

    public void sendServerMessage(ComMessage message) {
        ComClientSender sender = new ComClientSender(client.socket, message);
        sender.start();
    }
}
