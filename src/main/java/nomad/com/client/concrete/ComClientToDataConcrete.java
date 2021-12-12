package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.client_message.game.RejectPlayerMessage;
import nomad.com.common.message.server_message.game.EnoughPlayerMessage;
import nomad.com.common.message.server_message.game.GetSavedGameMessage;
import nomad.com.common.message.server_message.information.GetProfileMessage;
import nomad.com.common.message.server_message.information.LocalUserConnectionMessage;
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

    @Override
    public void enoughPlayers(UUID gameId, UUID opponentId) {
        clientController.sendMessage(new EnoughPlayerMessage(gameId, opponentId));
    }

    /**
     * Disconnect the local user from the local server.
     */
    @Override
    public void logout() {
        clientController.disconnect();
    }

    /**
     * Ask the server to retrieve the replay of a specified game.
     *
     * @param gameId The identifier of the game to retrieve.
     */
    @Override
    public void askForSave(UUID gameId) {
        clientController.sendMessage(new GetSavedGameMessage(gameId));
    }

    /**
     * Retrieve the profile of a given remote user from the server.
     *
     * @param userId the unique identifier of the user profile to recover.
     */
    @Override
    public void getProfile(UUID userId) {
        clientController.sendMessage(new GetProfileMessage(userId));
    }

    @Override
    public void rejectPlayers(UUID gameId) {
        clientController.sendMessage(new RejectPlayerMessage(gameId));
    }
}
