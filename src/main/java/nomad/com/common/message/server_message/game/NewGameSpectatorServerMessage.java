package nomad.com.common.message.server_message.game;

import nomad.com.common.message.client_message.game.HandleNewSpectatorInGameClientMessage;
import nomad.com.common.message.client_message.game.NewSpectorInGameClientMessage;
import nomad.com.common.message.server_message.BaseServerMessage;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.UserLight;

import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGameSpectatorServerMessage extends BaseServerMessage {
    private final UserLight userLight;
    private final GameLight game;

    public NewGameSpectatorServerMessage(UserLight userLight, GameLight game) {
        this.userLight = userLight;
        this.game = game;
    }

    @Override
    public void process(Socket socket, ServerController controller) {
        // Add new spectator in server
        controller.sendMessage(socket, new AddingSpectatorInServerMessage(userLight, game));

        // Handing new spectator
        controller.sendMessage(socket, new HandleNewSpectatorInGameClientMessage(userLight));

        // Add new spectator to other client
        List<UserLight> userList = controller.getDataToCom().getUserList(game);
        for (UserLight user : userList) {
            Socket client = controller.getClientSocket(user.getId());
            if (socket != null) {
                controller.sendMessage(client, new NewSpectorInGameClientMessage(game.getGameId(), user));
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Not able to find user socket");
            }
        }
    }
}
