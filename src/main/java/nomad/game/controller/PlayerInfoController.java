package nomad.game.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;


public class PlayerInfoController extends GameControllerAbstract {

    @FXML
    public Label playerOneName;

    @FXML
    public Label playerTwoName;

    @FXML
    public Label numberTower;

    @FXML
    public Pane paneUser1;

    @FXML
    public Pane paneUser2;

    @FXML
    public Label numberTiles;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen Parent screen controller
     */
    public PlayerInfoController(IhmScreenController screen) {
        super(screen);
    }

    @Override
    public void init() {
        Game game = getGameController().getCurrentGame();
        playerOneName.setText("Player: " + game.getHost().getLogin());
        playerTwoName.setText("Player: " + game.getOpponent().getLogin());
        numberTower.setText("Towers : " + game.getNbOfTowersPlayed() + "/"+game.getNbOfTowers());
        numberTiles.setText("Tiles : " + game.getNbOfTilesPlayed() + "/165");
        updatePane(game);
    }

    public void update() {
        Game game = getGameController().getCurrentGame();
        updatePane(game);
        Platform.runLater(() -> {
            numberTower.setText("Towers : " + game.getNbOfTowersPlayed() + "/"+game.getNbOfTowers());
            numberTiles.setText("Tiles : " + game.getNbOfTilesPlayed() + "/165");
        });
    }

    public void updatePane(Game game) {
        if (game.getCurrentPlayerUUID().equals(game.getHost().getId())) {
            paneUser1.setStyle("-fx-background-color: #134900;");
            paneUser2.setStyle("-fx-background-color: #003049;");
        } else {
            paneUser1.setStyle("-fx-background-color: #003049;");
            paneUser2.setStyle("-fx-background-color: #134900;");
        }
    }
}
