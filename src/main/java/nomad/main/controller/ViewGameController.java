package nomad.main.controller;

import javafx.application.Platform;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import javafx.scene.control.ListView;

import javafx.scene.layout.AnchorPane;
import nomad.common.data_structure.GameLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;
import nomad.main.utils.GameCell;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @FXML
    public AnchorPane waitingPane;


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
        //was used to tests
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
        //ihmController.getComI().addPlayerInGame(ihmController.getDataI().getPlayer(), gamesViewAsPlayer.getSelectionModel().getSelectedItem());

    }

    public void acceptedInGame()
    {
        freeIhm();
        DialogController.display("Match accepté", "Vous avez été accepté comme opposant", DialogController.DialogStatus.SUCCESS, ihmController);
        ihmController.changeScreen(6);
    }

    public void blockIhm()
    {
        waitingPane.setVisible(true);
    }

    public void freeIhm()
    {
        waitingPane.setVisible(false);
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

    public ListChangeListener<GameLight> getListChangeListener(ListView<GameLight> viewList)
    {
        return change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (GameLight game : change.getAddedSubList()) {
                        Logger.getAnonymousLogger().log(Level.INFO, "Game added event");
                        Platform.runLater(() ->
                                viewList.getItems().add(game) //add a new game
                        );

                    }
                } else if (change.wasRemoved()) {
                    for (GameLight game : change.getRemoved()) {
                        Platform.runLater(() ->
                                viewList.getItems().remove(game)  //remove a game
                        );
                    }
                }
            }
        };
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamesViewAsViewer.setVisible(false);
        gamesViewAsViewer.setManaged(false);

        gamesViewAsViewer.setCellFactory(gameLightListView -> new GameCell(ihmController));
        gamesViewAsPlayer.setCellFactory(gameLightListView -> new GameCell(ihmController));




        gamesAsViewer = getListChangeListener(gamesViewAsViewer);


        gamesAsPlayer = getListChangeListener(gamesViewAsPlayer);
    }
}
