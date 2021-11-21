package nomad.com.client.message;

import nomad.com.common.ComMessage;
import nomad.common.data_structure.User;

public class UserConnectedMessage extends ComMessage {
    User user;

    public UserConnectedMessage(User user) {
        this.user = user;
    }
    //todo what do we need there ?
}
