package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;

import java.util.UUID;

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
    public Pane paneUser1;

    @FXML
    public Pane paneUser2;

    @FXML
    public Label numberTiles;

    private UUID currentUser;

    private IhmScreenController gameScreen;


    public PlayerInfoController(IhmScreenController screen) {
        super(screen);
        gameScreen = screen;
    }

    @Override
    public void init() {
        Game game = getGameController().getCurrentGame();
        currentUser = getGameController().getGameScreen().getDataInterface().getUser().getUserId();
        playerOneName.setText("Player: "+game.getHost().getLogin());
        playerTwoName.setText("Player: " + game.getOpponent().getLogin());
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
        numberTiles.setText("Tiles : "+game.getNbOfTilesPlayed()+"/5");
        updatePane(game);
    }

    public void update() {
        Game game = getGameController().getCurrentGame();
//      True if it's your turn game.getCurrentPlayerUUID().equals(currentUser);
        updatePane(game);
        numberTower.setText("Towers : "+game.getNbOfTowers()+"/5");
        numberTiles.setText("Tiles : "+game.getNbOfTilesPlayed()+"/5");
    }

    public void updatePane(Game game){
        System.out.println(game.getCurrentPlayerUUID());
        System.out.println(game.getHost().getId());
        if(game.getCurrentPlayerUUID().equals(game.getHost().getId())){
            paneUser1.setStyle("-fx-background-color: #134900;");
            paneUser2.setStyle("-fx-background-color: #003049;");
        }else{
            paneUser2.setStyle("-fx-background-color: #134900;");
            paneUser1.setStyle("-fx-background-color: #003049;");
        }
    }

}
