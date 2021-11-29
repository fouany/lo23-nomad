package nomad.game.controller;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ChatController extends GameComponentsAbstract{
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected ChatController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    public TextArea chat;

    @Override
    public void init() {

    }

    @Override
    public void update(String type) {

    }

    public void OnClickSend() {
        
    }


}
