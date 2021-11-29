package nomad.com.common.message.serverMessage;

import nomad.com.common.message.clientMessage.UserChangedMessageBase;
import nomad.com.common.message.clientMessage.LobbyInformationMessageBase;
import nomad.com.server.ServerController;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Message sent to connect local user on the server
 */
public class LocalUserConnectionMessage extends BaseServerMessage {
    public final User user;

    public LocalUserConnectionMessage(User user) {
        this.user = user;
    }

    @Override
    public void process(Socket socket, ServerController controller) {

        controller.broadcast(new UserChangedMessageBase(user, true));

        //Send information on games and users to newly connected client
        LobbyInformationMessageBase informationMessage = new LobbyInformationMessageBase(
                (ArrayList<Player>) controller.getDataToCom().requestConnectedUserList(),
                (ArrayList<GameLight>) controller.getDataToCom().requestGameListInLobby(),
                (ArrayList<GameLight>) controller.getDataToCom().requestGameListInPlay()
        );
        controller.sendMessage(socket, informationMessage);


        //Register a new connected client on the server
        controller.getDataToCom().updateUserListAdd(user);
        controller.registerUser(socket, user);
    }
}
