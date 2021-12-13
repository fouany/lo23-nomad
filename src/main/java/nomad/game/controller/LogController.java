package nomad.game.controller;

import javafx.scene.control.TextArea;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmScreenController;

import java.sql.Timestamp;
import java.util.UUID;

public class LogController extends GameComponentsAbstract{
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected LogController(IhmScreenController screen) {
        super(screen);
    }

    public TextArea logs;

    @Override
    public void init() {

    }

    @Override
    public void update(String type, Object value) {

    }

    public void updateLogs(Move move) {
        UUID sender = move.getUserId();

        //logs.appendText("");
    }
}
