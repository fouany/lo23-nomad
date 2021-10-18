package nomad.common.interfaces.com;

import nomad.common.data_structure.Message;
import nomad.common.data_structure.Skip;
import nomad.common.data_structure.Tile;

import java.util.UUID;

public interface ComToIhmGameInterface {
    void sendNewPlayer(UUID userId);
    void endGame(UUID idGame, boolean gameEnded);
    void sendMessage(Message msg);
    void isValid(Tile m, boolean valid);
    void isValid(Skip s, boolean valid);
}
