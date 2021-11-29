package nomad.com.common.message.clientMessage;

import nomad.com.client.ClientController;
import nomad.com.common.message.Message;

public abstract class BaseClientMessage extends Message {
    public void process(ClientController controller) {}
}
