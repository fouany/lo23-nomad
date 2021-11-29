package nomad.com.server;

import nomad.com.common.message.clientMessage.NewGameUserMessage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.interfaces.com.ComToDataServerInterface;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComToDataServerConcrete implements ComToDataServerInterface {
    ServerController serverController;

    @Override
    public void requestHost(Player player) {
        HashMap<Socket, IdentifiedClient> clients = serverController.getClientList();
        IdentifiedClient client = null;
        for (IdentifiedClient cli : clients.values()) {
            if (cli.getUID() == player.getId()) {
                client = cli;
                break;
            }
        }
        if (client == null) {
            throw new NullPointerException();
        }
        // TODO : comment mieux récupérer le GameLight ici ?
        List<GameLight> games = serverController.getDataToCom().requestGameListInLobby();
        GameLight game = null;
        for (GameLight g : games) {
            if (g.getHost().getId() == player.getId()) {
                game = g;
            }
        }

        if (game == null) {
            throw new NullPointerException();
        }
        serverController.sendMessage(client.getSocket(), new NewGameUserMessage(game, player, true));
    }
}
