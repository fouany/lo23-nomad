package nomad.com.client.concrete;

import nomad.com.client.ClientController;
import nomad.com.common.message.Message;
import nomad.com.common.message.server_message.game.RejectPlayerMessage;
import nomad.com.common.message.server_message.game.EnoughPlayerMessage;
import nomad.com.common.message.server_message.game.GetSavedGameMessage;
import nomad.com.common.message.server_message.information.GetProfileGameMessage;
import nomad.com.common.message.server_message.information.GetProfileMainMessage;
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
    public void addConnectedUser(User user) throws IOException {
        if (user == null) {
            throw new NullPointerException();
        }

        clientController.connectClient(user.getLastServer().getIpAddress(), user.getLastServer().getPort());

        sendMessage(new LocalUserConnectionMessage(user), "Failed to register the user to the remote server !");
    }

    /**
     * enoughPlayers send a message to the client to determine if there is enough
     * players to start a game
     * @param gameId is the id of the game
     * @param opponentId is the id of the opponent player in the game
     */
    @Override
    public void enoughPlayers(UUID gameId, UUID opponentId) {
        sendMessage(new EnoughPlayerMessage(gameId, opponentId), "Failed to send EnoughPlayerMessage to the remote server");
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
        sendMessage(new GetSavedGameMessage(gameId), "Failed to send GetSavedGameMessage to the remote server");
    }

    /**
     * Retrieve the profile of a given remote user from the server and send it to main.
     *
     * @param userId the unique identifier of the user profile to recover.
     */
    @Override
    public void getProfileMain(UUID userId) {
        sendMessage(new GetProfileMainMessage(userId), "Failed to send GetProfileMainMessage to the remote server");
    }

    /**
     * Retrieve the profile of a given remote user from the server and send it to game.
     *
     * @param userId the unique identifier of the user profile to recover.
     */
    @Override
    public void getProfileGame(UUID userId) {
        sendMessage(new GetProfileGameMessage(userId), "Failed to send GetProfileGameMessage to the remote server");
    }

    /**
     * rejectPlayer send a message to refuse a player to join a game
     *
     * @param gameId is the id of the game we want to reject a player
     */
    @Override
    public void rejectPlayers(UUID gameId) {
        sendMessage(new RejectPlayerMessage(gameId), "Failed to send RejectPlayerMessage to the remote server");
    }

    /**
     * sendMessage is a private function to send a message to the server
     *
     * @param message is the message to sent
     * @param errorMessage is the error message to sent
     */
    private void sendMessage(Message message, String errorMessage) {
        if (!clientController.sendMessage(message)) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, errorMessage);
        }
    }
}
