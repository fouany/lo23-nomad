package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;

public class PlayerInfoController extends GameControllerAbstract {

    private GameController gameController;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */

    @FXML
    public Label playerOneName;

    @FXML
    public Label playerTwoName;

    @FXML
    public Label numberTower;

    @FXML
    public Label numberTiles;

    private IhmScreenController gameScreen;


    public PlayerInfoController(IhmScreenController screen) {
        super(screen);
        gameScreen = screen;
    }

    @Override
    public void init() {
        Game game = getGameController().getCurrentGame();
        System.out.println(game.getHost().getLogin());
        playerOneName.setText(game.getHost().getLogin());
        playerTwoName.setText("Player: " + game.getOpponent().getLogin());
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
        numberTiles.setText("Tiles : "+game.getNbOfTilesPlayed()+"/5");
    }

    public void update(String type) {
        Game game = getGameController().getCurrentGame();
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
        numberTiles.setText("Tiles : "+game.getNbOfTilesPlayed()+"/5");
    }

}
