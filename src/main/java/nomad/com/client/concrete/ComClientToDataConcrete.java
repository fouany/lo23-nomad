package nomad.com.client.concrete;

import nomad.com.client.controller.ComClientController;
import nomad.com.client.message.UserConnectedMessage;
import nomad.common.data_structure.User;
import nomad.common.interfaces.com.ComToDataInterface;

import java.util.UUID;

public class ComClientToDataConcrete implements ComToDataInterface {
    public ComClientController clientController;

    public ComClientToDataConcrete(ComClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void addConnectedUser(User user) {
        clientController.setCurrentUser(user);
        clientController.initSocket();
        clientController.sendServerMessage(
                new UserConnectedMessage(user)
        );
    }

    @Override
    public void logout(User user) {
        //envoie (via le controller) un message de deconnexion au serveur
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
