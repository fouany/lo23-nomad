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

    /**
     * Constructor of the main game controller
     * @param screen main screen controller
     */
    public GameController(IhmScreenController screen) {
        super(screen);
    }

    public void initListener(){
        currentGame = ((IhmGameScreenController) super.screenControl).getLinkedGame();
        currentGame.addObserver(this);

        currentGame.getMoves().addListener(new ListChangeListener<Move>() {
            @Override
            public void onChanged(Change<? extends Move> c) {
                System.out.println("Changement sur les mooves à gérer coté IHM");
            }
        });

        currentGame.getSpect().addListener(new ListChangeListener<UserLight>() {
            @Override
            public void onChanged(Change<? extends UserLight> c) {
                System.out.println("Changement sur les spects");
            }
        });

        currentGame.getChat().addListener(new ListChangeListener<Message>() {
            @Override
            public void onChanged(Change<? extends Message> c) {
                System.out.println("Changement sur le chat");
            }
        });

        playerInfoController = new PlayerInfoController(super.screenControl);
        playerInfoController.setParentController(this);
        playerInfoController.init();

        boardController = new BoardController(super.screenControl);
        boardController.setParentController(this);
        boardController.init();

        logController = new LogController(super.screenControl);
        logController.setParentController(this);
        logController.init();

        skipController = new SkipController(super.screenControl);
        skipController.setParentController(this);
        skipController.init();

        chatController = new ChatController(super.screenControl);
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
