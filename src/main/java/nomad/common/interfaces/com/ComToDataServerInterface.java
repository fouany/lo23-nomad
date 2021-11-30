package nomad.common.interfaces.com;

import nomad.common.data_structure.*;

import java.util.UUID;

public interface ComToDataServerInterface {
    void requestHost(GameLight game, Player opponent);

    void towerValid(Tower t);

    void tileValid(Tile t);

    void skipValid (Skip s);
}
