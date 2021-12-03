package nomad.common.interfaces.com;

import nomad.common.data_structure.*;

import java.util.UUID;

import java.util.List;

public interface ComToDataServerInterface {
    void requestHost(GameLight game, Player opponent);

    void towerValid(Tower t, List<UUID> listOther);

    void tileValid(Tile t, List<UUID> listOther);

    void skipValid (Skip s, List<UUID> listOther);
}
