package nomad.com.common;

import nomad.common.data_structure.User;

/**
 * Message sent to connect local user on the server
 */
public class LocalUserConnectionMessage extends ComMessage {
    public final User user;

    public LocalUserConnectionMessage(User user) {
        this.user = user;
    }
}
