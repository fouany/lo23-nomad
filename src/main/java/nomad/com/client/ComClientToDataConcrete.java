package nomad.com.client;

import nomad.com.common.message.LocalUserConnectionMessage;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataClientInterface;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComClientToDataConcrete implements ComToDataClientInterface {
    ClientController clientController;

    public void setController(ClientController clientController) {
        this.clientController = clientController;
    }

    /**
     * addConnectedUser inform the server of the connection of the local user.
     * This method is called by data upon completion of the login form.
     * It is responsible for initializing the socket with the server.
     * Warning : in case of failure to create the socket or send the message, no exception is thrown.
     *
     * @param user The local connected user
     */
    @Override
    public void addConnectedUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }

        try {
            clientController.connectClient(user.getLastServer().getIpAddress(), user.getLastServer().getPort());
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to init socket to remote server !");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
            return;
        }

        if (!clientController.sendMessage(new LocalUserConnectionMessage(user))) { // Connect to the remote server
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to register the user to the remote server !");
        }
    }

    /**
     * Disconnect the local user from the local server.
     *
     * @param user The local user to disconnect from the server.
     */
    @Override
    public void logout(User user) {
        // send disconnect message to the server via the controller
        // if socket is still active, send disconnection message
    }

    /**
     * Ask the server to retrieve the replay of a specified game.
     *
     * @param game The identifier of the game to retrieve.
     */
    @Override
    public void askForSave(UUID game) {
        //TODO
    }

    /**
     * Retrieve the profile of a given remote user from the server.
     *
     * @param idUser the unique identifier of the user profile to recover.
     */
    @Override
    public void getProfile(UUID idUser) {
        //TODO
    }
}
