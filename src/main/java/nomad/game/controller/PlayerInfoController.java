package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

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

    @FXML
    public TextField numberTower;

    @FXML
    public TextField numberTiles;

    @FXML
    public TextField conectedTowerOne;

    @FXML
    public TextField connectedTowerTwo;


    protected PlayerInfoController(IhmScreenController screen) {
        super(screen);
    }

    @Override
    public void init() {
        Game game = super.getGameController().getCurrentGame();
        playerOneName.setText(game.getHost().getLogin());
        playerTwoName.setText(game.getOpponent().getLogin());
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
        //TODO update for connect / nb tiles when com implements the methods
    }

    @Override
    public void update(String type, Object value) {

    }

}
