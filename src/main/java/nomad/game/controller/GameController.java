package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

import java.util.Observable;
import java.util.Observer;

public class GameController extends IhmControllerComponent implements Observer {

    /**
     * Current game
     */
    private Game currentGame;

    /**
     * Controlleur of the players
     */
    @FXML
    private PlayerInfoController player_info_controller;

    public GameController(IhmScreenController screen) {
        super(screen);
        currentGame = ((IhmGameScreenController) super.screenControl).getLinkedGame();
        currentGame.addObserver(this);
        player_info_controller = new PlayerInfoController(screen);
        player_info_controller.setParentController(this);
        player_info_controller.init(currentGame);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update on game");
        //TODO handle different type of updates => call update on the controller needed
    }
}
