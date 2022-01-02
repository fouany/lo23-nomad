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
import java.util.logging.Level;
import java.util.logging.Logger;

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
                (ListChangeListener<Move>) c -> {
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Au tour de"+currentGame.getCurrentPlayerUUID());
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Je suis "+gameScreen.getDataInterface().getUser().getUserId());
                    logController.update(currentGame.getMoves().get(currentGame.getMoves().size()-1));
                    this.update(null,null);
                }
        );

        currentGame.getSpect().addListener(
                (ListChangeListener<UserLight>) c -> System.out.println("Changement sur les spects")
        );

        currentGame.getChat().addListener(
                (ListChangeListener<Message>) c -> chatController.updateChat(currentGame.getChat().get(currentGame.getChat().size()-1))
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
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Au tour de"+currentGame.getCurrentPlayerUUID());
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Je suis "+gameScreen.getDataInterface().getUser().getUserId());
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
