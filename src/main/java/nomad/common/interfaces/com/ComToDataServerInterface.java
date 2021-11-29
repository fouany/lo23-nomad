package nomad.common.interfaces.com;

import nomad.common.data_structure.Player;
import nomad.common.data_structure.Skip;
import nomad.common.data_structure.Tile;
import nomad.common.data_structure.Tower;

public interface ComToDataServerInterface {
    void requestHost(Player player);

    void towerValid(Tower t);

    void tileValid(Tile t);

    void skipValid (Skip s);
}
