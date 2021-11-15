package nomad.com.client.concrete;

import nomad.com.client.ComClient;
import nomad.com.client.controller.ComClientController;
import nomad.com.server.controller.ComServerController;
import nomad.com.server.message.ConnectionRequestMessage;
import nomad.com.server.message.DisconnectionRequestMessage;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataInterface;
import nomad.common.interfaces.data.DataToComServeurInterface;

import java.util.UUID;

public class ComClientToData implements ComToDataInterface {
    public ComServerController serverController;

    @Override
    public void addConnectedUser(User user) {
        ComClient client = new ComClient(user);
        ComClientController.SendServerMessage(new ConnectionRequestMessage(serverController, user));
    }

    @Override
    public void logout(User user) {
        ComClientController.SendServerMessage(new DisconnectionRequestMessage(serverController, user));
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
