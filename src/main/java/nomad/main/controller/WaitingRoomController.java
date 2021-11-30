package nomad.main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.main.IhmMainScreenController;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitingRoomController extends IhmControllerComponent implements Initializable, Observer {

    public Label gameName;
    public Label gameId;
    public Label towersNumber;
    public Label viewersNumber;
    public Label hostName;
    public Label hostId;
    public Button hostReady;
    public HBox opponent_wait;
    public HBox opponent_container;
    public Label opName;
    public Label opId;
    public Button opReady;
    private IhmMainScreenController controller;
    private Boolean passModule  = false;
    private Boolean viewInitialized = false;

    private Game testgame;

    public WaitingRoomController(IhmMainScreenController screen) {
        super(screen);
        controller = screen;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opponent_container.setVisible(false);
        opponent_container.setManaged(false);

    }

    public void onClickReady()
    {

    }

    public void gameUpdate(Game g) throws GameException {

        if(!passModule && g.isGameLaunched())
        {
            //controller.changeModule("GAME", g);
            passModule = true;
        }

        if(!viewInitialized)
        {
            gameName.setText(g.getName());
            gameId.setText("#" + g.getGameId().toString());
            towersNumber.setText(String.valueOf(g.getNbOfTowers()));
            viewersNumber.setText("0");
            hostName.setText(g.getHost().getLogin());
            hostId.setText("#" + g.getHost().getId());
            viewInitialized = true;
        }
        //viewersNumber.setText(String.valueOf(testgame.getSpect().size()));
        Player opponent = g.getOpponent();
        if(opponent != null)
        {
            opponent_wait.setVisible(false);
            opponent_wait.setManaged(false);
            opponent_container.setManaged(true);
            opponent_container.setVisible(true);
            hostReady.setDisable(false);
            opReady.setDisable(false);
            opName.setText(opponent.getLogin());
            opId.setText(String.valueOf(opponent.getId()));
            DialogController.display("Todo", "Modal accepter/refuser", DialogController.DialogStatus.WARNING, controller);
            if(controller.getDataI().getUser().getUserId() == g.getHost().getId())
            {
                controller.getDataI().enoughPlayers(controller.getDataI().getGameLight());
                controller.getComI().launchGame(controller.getDataI().getGame());

            }

        }
        else
        {
            opponent_container.setVisible(false);
            opponent_container.setManaged(false);
            opponent_wait.setManaged(true);
            opponent_wait.setVisible(true);
            hostReady.setDisable(true);
            opReady.setDisable(true);
        }

    }

    @Override
    public void update(Observable observable, Object o) {

        Game g = (Game)o;
        try
        {
            gameUpdate(g);
        }
        catch (GameException err)
        {
            Logger.getLogger(WaitingRoomController.class.getName()).log(Level.SEVERE, err.getMessage());
        }
    }
}
