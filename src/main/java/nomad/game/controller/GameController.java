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
    private PlayerInfoController playerInfoController;

    @FXML
    private ChatController chatController;

    @FXML
    private SkipController skipController;

    @FXML
    private LogController logController;

    @FXML
    private BoardController boardController;


    public GameController(IhmScreenController screen) {
        super(screen);
        currentGame = ((IhmGameScreenController) super.screenControl).getLinkedGame();
        currentGame.addObserver(this);

        playerInfoController = new PlayerInfoController(screen);
        playerInfoController.setParentController(this);
        playerInfoController.init(currentGame);

        boardController = new BoardController(screen);
        boardController.setParentController(this);
        boardController.init(currentGame);

        logController = new LogController(screen);
        logController.setParentController(this);
        logController.init(currentGame);

        skipController = new SkipController(screen);
        skipController.setParentController(this);
        skipController.init(currentGame);

        chatController = new ChatController(screen);
        chatController.setParentController(this);
        chatController.init(currentGame);

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update on game");
        //TODO handle different type of updates => call update on the controller needed
    }
}
