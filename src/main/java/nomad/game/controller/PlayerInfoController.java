package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;

public class PlayerInfoController extends GameComponentsAbstract {

    private GameController gameController;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */

    @FXML
    public TextField playerOneName;

    @FXML
    public TextField playerTwoName;

    public TextField numberTower;



    protected PlayerInfoController(IhmScreenController screen) {
        super(screen);
    }

    @Override
    public void init(Game game) {
        playerOneName.setText(game.getHost().getLogin());
        playerTwoName.setText(game.getOpponent().getLogin());
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
    }


    @Override
    public void update() {

    }


}
