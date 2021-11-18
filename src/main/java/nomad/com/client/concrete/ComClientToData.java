package nomad.com.client.concrete;

import nomad.com.client.ComClient;
import nomad.com.client.controller.ComClientController;
import nomad.com.server.controller.ComServerController;
import nomad.com.server.message.ConnectionRequestMessage;
import nomad.com.server.message.DisconnectionRequestMessage;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataInterface;

import java.util.UUID;

public class ComClientToData implements ComToDataInterface {
    public ComServerController serverController;
    public ComClientController clientController;

    public ComClientToData(ComServerController serverController, ComClientController clientController) {
        this.serverController = serverController;
        this.clientController = clientController;
    }

    @Override
    public void addConnectedUser(User user) {
        new ComClient(user);
        clientController.SendServerMessage(new ConnectionRequestMessage(serverController, user));
    }

    @Override
    public void logout(User user) {
        clientController.SendServerMessage(new DisconnectionRequestMessage(serverController, user.getUserId()));
    }

    @Override
    public void requestHost(Player player) {
        //TODO
    }

    @Override
    public void askForSave(UUID game) {
        //TODO
    }


    @Override
    public void getProfile(UUID idUser) {
        //TODO
    }
}
