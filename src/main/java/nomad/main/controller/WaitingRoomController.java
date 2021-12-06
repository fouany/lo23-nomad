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


    private IhmMainScreenController controller;
    private Boolean passModule  = false;
    private Boolean viewInitialized = false;

    public WaitingRoomController(IhmMainScreenController screen) {
        super(screen);
        controller = screen;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opponentContainer.setVisible(false);
        opponentContainer.setManaged(false);

    }

    public void quitGame(ActionEvent e)
    {
       /*todo add interface to quit the game*/
    }

    public void acceptOrRejectOpponent(Game game)
    {

    }


    public void gameUpdate(Game g) throws GameException {
        Logger.getAnonymousLogger().log(Level.INFO, "Game Update");

        if (g.isGameLaunched()){
            Logger.getAnonymousLogger().log(Level.INFO, "Game is launch");
        }

        if(passModule!= null && !passModule && g.isGameLaunched())
        {
            Logger.getAnonymousLogger().log(Level.INFO, "Try to launch the game");
            //TODO change module with controller.changeModule("GAME", g)
           try {
               controller.changeModule();
               return;
           }
           catch (IOException e) {
               e.printStackTrace();
           }
            passModule = true;
        }

        if(viewInitialized!=null && !viewInitialized)
        {
            gameName.setText(g.getName());
            gameId.setText("#" + g.getGameId().toString());
            towersNumber.setText(String.valueOf(g.getNbOfTowers()));
            viewersNumber.setText("0");
            hostName.setText(g.getHost().getLogin());
            hostId.setText("#" + g.getHost().getId());
            viewInitialized = true;
           // viewersNumber.setText(String.valueOf(g.getSpect().size()));
        }
        Player opponent = g.getOpponent();
        if(opponent != null)
        {

            opponentWait.setVisible(false);
            opponentWait.setManaged(false);
            opponentContainer.setManaged(true);
            opponentContainer.setVisible(true);
            hostReady.setDisable(false);
            opReady.setDisable(false);
            opName.setText(opponent.getLogin());
            opId.setText(String.valueOf(opponent.getId()));

           if(g.getHost().getId().equals(controller.getDataI().getPlayer().getId()))
           {
               DialogController.display("Todo", "Modal accepter/refuser", DialogController.DialogStatus.WARNING, controller);
               if(controller.getDataI().getUser().getUserId().equals(g.getHost().getId()))
               {
                   controller.getDataI().enoughPlayers(controller.getDataI().getGameLight());
                   Logger.getAnonymousLogger().log(Level.INFO, "coucou");
                   DialogController.display("Todo", "Wait", DialogController.DialogStatus.WARNING, controller);
                   controller.getComI().launchGame(controller.getDataI().getGame());
               }
           }

        }
        else
        {
            opponentContainer.setVisible(false);
            opponentContainer.setManaged(false);
            opponentWait.setManaged(true);
            opponentWait.setVisible(true);
            hostReady.setDisable(true);
            opReady.setDisable(true);
        }

    }

    @Override
    public void update(Observable observable, Object o) {

        Logger.getAnonymousLogger().log(Level.INFO, "First Update");
        try
        {
            Game g = (Game)observable;
            gameUpdate(g);
        }
        catch (GameException err)
        {
            Logger.getLogger(WaitingRoomController.class.getName()).log(Level.SEVERE, err.getMessage());
        }
    }
}
