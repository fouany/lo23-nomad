package nomad.game.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

import java.io.IOException;
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

    private boolean popUp= false;

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

    public void showPopUpEnd() throws IOException {
        if(!popUp){
            popUp = true;
            Platform.runLater(() -> {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(screenControl.getStage());
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Fin de la partie"));
                Button button = new Button("Revenir au menu");
                dialogVbox.getChildren().add(button);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            screenControl.changeModule();
                            dialog.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            });
        }
    }
}
