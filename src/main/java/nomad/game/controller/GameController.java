package nomad.game.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
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

    public void instanciateControllers(){
        currentGame = gameScreen.getLinkedGame();
        currentGame.addObserver(this);

        currentGame.getMoves().addListener(
                (ListChangeListener<Move>) c -> this.update(null,null)
        );

        currentGame.getSpect().addListener(
                (ListChangeListener<UserLight>) c -> System.out.println("Changement sur les spects")
        );

        currentGame.getChat().addListener(
                (ListChangeListener<Message>) c -> System.out.println("Changement sur le chat")
        );

        playerInfoController = (PlayerInfoController) gameScreen.getController(PlayerInfoController.class);
        playerInfoController.setParentController(this);

        boardController = (BoardController) gameScreen.getController(BoardController.class);
        boardController.setParentController(this);

        logController = (LogController) gameScreen.getController(LogController.class);
        logController.setParentController(this);

        skipController = (SkipController) gameScreen.getController(SkipController.class);
        skipController.setParentController(this);

        chatController = (ChatController) gameScreen.getController(ChatController.class);
        chatController.setParentController(this);
    }

    public void initControllers()
    {
        playerInfoController.init();
        boardController.init();
        logController.init();
        skipController.init();
        chatController.init();
    }

    public void update(Observable o, Object arg) {
        System.out.println("Update on game");
        //TODO handle different type of updates => call update on the controller needed
        boardController.update();
        playerInfoController.update();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public IhmGameScreenController getGameScreen() {
        return gameScreen;
    }
}
