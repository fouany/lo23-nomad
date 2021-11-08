package nomad.common.interfaces.com;

import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.Message;

public interface ComToIhmGameInterface {
    void checkGameEnd(GameLight game);
    void transmissionCom(Message msg);
}
