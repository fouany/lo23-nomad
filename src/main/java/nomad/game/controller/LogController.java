package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nomad.common.data_structure.Move;
import nomad.common.ihm.IhmScreenController;

public class LogController extends GameControllerAbstract {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public LogController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    public TextArea logs_container;

    @Override
    public void init() {

    }

    public void update(Move m) {
        logs_container.appendText(m.toString()+"\n");
    }
}
