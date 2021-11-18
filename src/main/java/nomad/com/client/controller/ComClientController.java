package nomad.com.client.controller;

import nomad.com.client.ComClient;
import nomad.com.client.ComClientSender;
import nomad.com.server.message.ComServerMessage;
import nomad.common.interfaces.data.DataToComInterface;

import java.io.Serializable;

public class ComClientController implements Serializable {
    public ComClient client;
    private final DataToComInterface dataToCom;

    public ComClientController(ComClient client, DataToComInterface dataToCom) {
        this.client = client;
        this.dataToCom = dataToCom;
    }

    public DataToComInterface getDataToCom() {
        return dataToCom;
    }

    public void SendServerMessage(ComServerMessage message) {
        ComClientSender sender = new ComClientSender(client.socket, message);
        sender.start();
    }
}
