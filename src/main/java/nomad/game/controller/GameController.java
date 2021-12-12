package nomad.game.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.game.ControllerIndex;
import nomad.game.IhmGameScreenController;

import java.util.Observable;
import java.util.Observer;

public class GameController extends IhmControllerComponent implements Observer {

    /**
     * Current game
     */
    private Game currentGame ;

    /**
     * Controller of the players
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

    private IhmGameScreenController gameScreen;

    /**
     * Constructor of the main game controller
     * @param screen main screen controller
     */
    public GameController(IhmScreenController screen) {
        super(screen);
        gameScreen = (IhmGameScreenController) screen;
    }

    public void initListener(){
        currentGame = gameScreen.getLinkedGame();
        currentGame.addObserver(this);

        currentGame.getMoves().addListener(
                (ListChangeListener<Move>) c -> System.out.println("Changement sur les mooves à gérer coté IHM")
        );

        currentGame.getSpect().addListener(
                (ListChangeListener<UserLight>) c -> System.out.println("Changement sur les spects")
        );

        currentGame.getChat().addListener(
                (ListChangeListener<Message>) c -> System.out.println("Changement sur le chat")
        );

        playerInfoController = (PlayerInfoController) gameScreen.getController(ControllerIndex.PLAYER_INFO.index);
        playerInfoController.setParentController(this);
        playerInfoController.init();

        boardController = (BoardController) gameScreen.getController(ControllerIndex.BOARD.index);
        boardController.setParentController(this);
        boardController.init();

        logController = (LogController) gameScreen.getController(ControllerIndex.LOG.index);
        logController.setParentController(this);
        logController.init();

        skipController = (SkipController) gameScreen.getController(ControllerIndex.SKIP.index);
        skipController.setParentController(this);
        skipController.init();

        chatController = (ChatController) gameScreen.getController(ControllerIndex.CHAT.index);
        chatController.setParentController(this);
        chatController.init();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update on game");
        //TODO handle different type of updates => call update on the controller needed
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
