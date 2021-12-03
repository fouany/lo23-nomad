package nomad.main.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import nomad.common.data_structure.GameLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGameController extends IhmControllerComponent implements Initializable {
    @FXML
    public CheckBox stateCheckBox;
    @FXML
    public ListView<GameLight> gamesViewAsViewer;
    @FXML
    public ListView<GameLight> gamesViewAsPlayer;
    @FXML
    public ListChangeListener<GameLight> gamesAsViewer;
    @FXML
    public ListChangeListener<GameLight> gamesAsPlayer;


    private IhmMainScreenController ihmController;

    public ViewGameController(IhmMainScreenController ihmMainScreenController){
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;

    }
    /**
     * This function is called in the class constructor.
     * In order to notice the changes for both gamesInLobby and gamesInPlay
     * two different ListChangeListeners are created : gameAsViewer and
     * gameAsPlayer. When a change is detected the listeners react : they
     * either add a game or delete it.
     */


    public void onClickReturnMenu() {
        this.ihmController.changeScreen(2);
    }
    /**
     * Both onClickAddGame and onClickRemoveGame are created for test
     * purposes. When the add button is clicked a game is added in both
     * the gamer listview and the viewer listview. The same goes for remove.
     * */
    public void onClickAddGame() {
        // gamesViewAsViewer.getItems().add("je suis game Viewer");
        // gamesViewAsPlayer.getItems().add("je suis game Player ");
    }

    public void onClickDeleteGame() {
        //was used to tests
    }
    /**
     * handleGame functions get the information of
     * the game selected.
     * */
    public void handleGameAsViewerListClick() {
        ihmController.getComI().addSpecInGame(ihmController.getDataI().getUserLight(), gamesViewAsViewer.getSelectionModel().getSelectedItem());


    }
    public void handleGameAsPlayerListClick() {
        ihmController.getComI().addPlayerInGame(ihmController.getDataI().getPlayer(), gamesViewAsViewer.getSelectionModel().getSelectedItem());

    }

    public GameLight getGameLight(String uuid, Boolean viewer)
    {
        return null;
    }

    /**
     * When a user checks the box "Viewer" the viewerlist
     * is made visible and the gamerList is made unvisible.
     */
    public void changeGamesView() {
        if (stateCheckBox.isSelected()){
            gamesViewAsPlayer.setVisible(false);
            gamesViewAsPlayer.setManaged(false);
            gamesViewAsViewer.setVisible(true);
            gamesViewAsViewer.setManaged(true);
        }else{
            gamesViewAsPlayer.setVisible(true);
            gamesViewAsPlayer.setManaged(true);
            gamesViewAsViewer.setVisible(false);
            gamesViewAsViewer.setManaged(false);
        }
    }

    private ListChangeListener<GameLight> processLisetener(ListChangeListener.Change<? extends GameLight> change) {
        change.next();
        if (change.wasAdded()) {
            for (GameLight game : change.getAddedSubList()) {
                Platform.runLater(() ->
                        gamesViewAsViewer.getItems().add(game) //add a new game
                );

            }
        } else if (change.wasRemoved()) {
            for (GameLight game : change.getRemoved()) {
                Platform.runLater(() ->
                        gamesViewAsViewer.getItems().remove(game)  //remove a game
                );
            }
        }
        return (ListChangeListener<GameLight>) change;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamesViewAsViewer.setVisible(false);
        gamesViewAsViewer.setManaged(false);
        gamesAsViewer = change -> {
            change.next();
            if (change.wasAdded()) {
                for (GameLight game : change.getAddedSubList()) {
                    Platform.runLater(() ->
                            gamesViewAsViewer.getItems().add(game) //add a new game
                    );

                }
            } else if (change.wasRemoved()) {
                for (GameLight game : change.getRemoved()) {
                    Platform.runLater(() ->
                            gamesViewAsViewer.getItems().remove(game)  //remove a game
                    );
                }
            }
        };
        gamesAsPlayer = change -> {
            change.next();
            if (change.wasAdded()) {
                for (GameLight game : change.getAddedSubList()) {
                    Platform.runLater(() ->
                            gamesViewAsPlayer.getItems().add(game) //add a new game
                    );

                }
            } else if (change.wasRemoved()) {
                for (GameLight game : change.getRemoved()) {
                    Platform.runLater(() ->
                            gamesViewAsPlayer.getItems().remove(game)  //remove a game
                    );
                }
            }
        };
    }
}
