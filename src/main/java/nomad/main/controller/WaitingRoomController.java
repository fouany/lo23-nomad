package nomad.main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.Player;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.ModuleMode;
import nomad.main.IhmMainScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitingRoomController extends IhmControllerComponent implements Initializable, Observer {
    @FXML
    public Label gameName;
    @FXML
    public Label gameId;
    @FXML
    public Label towersNumber;
    @FXML
    public Label viewersNumber;
    @FXML
    public Label hostName;
    @FXML
    public Label hostId;
    @FXML
    public Button hostReady;
    @FXML
    public HBox opponentWait;
    @FXML
    public HBox opponentContainer;
    @FXML
    public Label opName;
    @FXML
    public Label opId;
    @FXML
    public Button opReady;
    @FXML
    public HBox hostContainer;


    private IhmMainScreenController controller;
    private Boolean passModule = false;
    private Boolean viewInitialized = false;
    private static final String WHITE_BG = "main-bg-white";
    private static final String RED_BG = "main-bg-red";
    private Player opponent = null;

    public WaitingRoomController(IhmMainScreenController screen) {
        super(screen);
        controller = screen;
    }

    /**
     * initialize javafx component (used to ensure we are in the good thread)
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opponentContainer.setVisible(false);
        opponentContainer.setManaged(false);

    }

    public void quitGame(ActionEvent e)
    {
       /*todo add interface to quit the game = abandonner la partie*/
    }

    public void opponentLeftTheGame()
    {
        //todo implementation
    }

    public void hostLeftTheGame()
    {
        //todo implementation
    }

    /**
     * Handle acceptation or rejection of opponent NOTE : yet it just accept by default
     * @param g Game listened by main
     * @throws GameException
     */



    public void acceptOrRejectOpponent(Game g) throws GameException {
        if(g.getHost().getId().equals(controller.getDataI().getPlayer().getId()))
        {
            String opponentName = g.getOpponent().getLogin();
            Boolean accept = DialogController.display("Nouvel opposant", "Le joueur " + opponentName + " à rejoint la partie, voulez vous l'accepter", DialogController.DialogStatus.QUESTION, controller);
            if(Boolean.TRUE.equals(accept)) {
                handleOpponent(opponent);
                controller.getDataI().enoughPlayers(controller.getDataI().getGameLight());
                DialogController.display("Todo", "Click dimiss to start the game (wait opponent click on the dialog 'Partie rejoint')", DialogController.DialogStatus.WARNING, controller);
                controller.getComI().launchGame(controller.getDataI().getGame());
            }
            else controller.getDataI().rejectPlayers(controller.getDataI().getGameLight());

        }
    }

    /**
     * function called when game is started, used to pass module to GAME
     *
     */
    private void startTheGame() {
        Logger.getAnonymousLogger().log(Level.INFO, "Try to launch the game");

        try {
            controller.changeModule(ModuleMode.GAME_START);
            passModule = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleColor(Game g)
    {
        //TRUE == RED
        if(g.getGameParameters().isHostColor())
        {
            hostContainer.getStyleClass().set(1, RED_BG);
            opponentWait.getStyleClass().set(1, WHITE_BG);
            opponentContainer.getStyleClass().set(1, WHITE_BG);
            return;
        }
        hostContainer.getStyleClass().set(1, WHITE_BG);
        opponentWait.getStyleClass().set(1, RED_BG);
        opponentContainer.getStyleClass().set(1, RED_BG);

    }

    /**
     * Initialize view after game creation or game join
     * @param g
     */

    private void initializeView(Game g)
    {
        gameName.setText(g.getName());
        gameId.setText("#" + g.getGameId().toString());
        towersNumber.setText(String.valueOf(g.getNbOfTowers()));
        viewersNumber.setText("0");
        hostName.setText(g.getHost().getLogin());
        hostId.setText("#" + g.getHost().getId());
        handleColor(g);

        // Reset fields to allow replay
        handleOpponent(null);
        viewInitialized = true;
        passModule = false;
    }

    /**
     * Handle opponent game (if opponent is null, show waiting container)
     * @param opponent the opponent
     */
    private void handleOpponent(Player opponent)
    {
        if(opponent == null)
        {
            opponentContainer.setVisible(false);
            opponentContainer.setManaged(false);
            opponentWait.setManaged(true);
            opponentWait.setVisible(true);
            hostReady.setDisable(true);
            opReady.setDisable(true);
            return;
        }

        opponentWait.setVisible(false);
        opponentWait.setManaged(false);
        opponentContainer.setManaged(true);
        opponentContainer.setVisible(true);
        hostReady.setDisable(false);
        opReady.setDisable(false);
        opName.setText(opponent.getLogin());
        opId.setText(String.valueOf(opponent.getId()));
    }

    /**
     * function called on observable object update
     * @param g Game listened by main
     * @throws GameException
     */

    public void gameUpdate(Game g) throws GameException {
        Logger.getAnonymousLogger().log(Level.INFO, "Game Update");

        if (g.isGameLaunched()){
            Logger.getAnonymousLogger().log(Level.INFO, "Game is launch");
        }

        if (!passModule && g.isGameLaunched()) {
            startTheGame();
            viewInitialized = false;
            return;
        }

        if (!viewInitialized) {
            initializeView(g);
        }

        opponent = g.getOpponent();
        if (opponent != null) {
            acceptOrRejectOpponent(g);
        }

    }

    /**
     * Function called on game modification
     * @param observable in this case, a game object observed by WaintingRoomController
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {

        Logger.getAnonymousLogger().log(Level.INFO, "First Update");
        try {
            Game g = (Game) observable;
            if (g.isGameLaunched() && !passModule) { // Execution happen in game module
                gameUpdate(g);
            }
        } catch (GameException err) {
            Logger.getLogger(WaitingRoomController.class.getName()).log(Level.SEVERE, err.getMessage());
        }
    }
}
