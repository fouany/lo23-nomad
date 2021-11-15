package nomad.com.client.controller;

import nomad.com.client.ComClient;
import nomad.com.client.ComClientSender;
import nomad.com.server.message.ComServerMessage;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.Serializable;

public class ComClientController implements Serializable {
    public static ComClient client;

    public DataToComInterface getDataToCom() {
        return dataToCom;
    }

    private DataToComInterface dataToCom;

    ComClientController(DataToComInterface dataToCom, ComClient client) {
        this.dataToCom = dataToCom;
        ComClientController.client = client;
    }

    public static void SendServerMessage(ComServerMessage message) {
        ComClientSender sender = new ComClientSender(client.socket, message);
        sender.start();
    }

    public static void startClient() {
        client.start();
    }

}
