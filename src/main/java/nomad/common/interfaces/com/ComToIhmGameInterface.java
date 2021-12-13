package nomad.common.interfaces.com;

import nomad.common.data_structure.Message;
import nomad.common.data_structure.Move;

public interface ComToIhmGameInterface {
    void transmissionCom(Message msg);

    void playMove(Move move);
}
